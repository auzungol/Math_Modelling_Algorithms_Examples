// ==================== ARRAY BASED QUEUE ====================

class ArrayQueue {
    private int[] queueArray;  // Queue elemanlarını tutan dizi
    private int front;         // Queue'nun ön tarafını gösteren indeks
    private int rear;          // Queue'nun arka tarafını gösteren indeks
    private int capacity;      // Queue'nun maksimum kapasitesi
    private int size;          // Queue'daki mevcut eleman sayısı

    // Constructor - Queue'yu başlatır
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queueArray = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    /**
     * ENQUEUE - Queue'ya eleman ekler
     * Time Complexity: O(1)
     * Elemanı queue'nun arkasına ekler (FIFO - First In First Out)
     */
    public void enqueue(int value) {
        // Queue dolu mu kontrolü
        if (isFull()) {
            System.out.println("Queue Overflow! Queue dolu.");
            return;
        }

        // Circular array mantığı: rear'ı döngüsel olarak artır
        rear = (rear + 1) % capacity;
        queueArray[rear] = value;
        size++;

        System.out.println(value + " queue'ya eklendi.");
    }

    /**
     * DEQUEUE - Queue'dan eleman çıkarır
     * Time Complexity: O(1)
     * En öndeki elemanı kaldırır ve değerini döndürür
     */
    public int dequeue() {
        // Queue boş mu kontrolü
        if (isEmpty()) {
            System.out.println("Queue Underflow! Queue boş.");
            return -1;
        }

        // Ön taraftaki elemanı al
        int dequeuedValue = queueArray[front];

        // front'u döngüsel olarak artır
        front = (front + 1) % capacity;
        size--;

        return dequeuedValue;
    }

    /**
     * PEEK (FRONT) - Queue'nun önündeki elemanı gösterir
     * Time Complexity: O(1)
     * Elemanı silmeden sadece görüntüler
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue boş!");
            return -1;
        }
        // Ön taraftaki elemanı döndür ama silme
        return queueArray[front];
    }

    /**
     * REAR - Queue'nun arkasındaki elemanı gösterir
     * Time Complexity: O(1)
     */
    public int rear() {
        if (isEmpty()) {
            System.out.println("Queue boş!");
            return -1;
        }
        return queueArray[rear];
    }

    // Queue boş mu kontrolü
    public boolean isEmpty() {
        return size == 0;
    }

    // Queue dolu mu kontrolü
    public boolean isFull() {
        return size == capacity;
    }

    // Queue boyutunu döndürür
    public int size() {
        return size;
    }
}


// ==================== LINKED LIST BASED QUEUE ====================

class QueueNode {
    int data;          // Node'un değeri
    QueueNode next;    // Bir sonraki node'u gösteren referans

    // Constructor
    public QueueNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListQueue {
    private QueueNode front;  // Queue'nun ön tarafını gösteren node
    private QueueNode rear;   // Queue'nun arka tarafını gösteren node
    private int size;         // Queue'daki eleman sayısı

    // Constructor - Boş queue oluşturur
    public LinkedListQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * ENQUEUE - Queue'ya eleman ekler
     * Time Complexity: O(1)
     * Yeni node'u linked list'in sonuna ekler
     */
    public void enqueue(int value) {
        // Yeni node oluştur
        QueueNode newNode = new QueueNode(value);

        // Queue boşsa, front ve rear aynı node'u gösterir
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            // Mevcut rear'ın next'i yeni node'u göstersin
            rear.next = newNode;
            // rear'ı yeni node olarak güncelle
            rear = newNode;
        }

        size++;
        System.out.println(value + " queue'ya eklendi.");
    }

    /**
     * DEQUEUE - Queue'dan eleman çıkarır
     * Time Complexity: O(1)
     * En öndeki node'u kaldırır ve değerini döndürür
     */
    public int dequeue() {
        // Queue boş mu kontrolü
        if (isEmpty()) {
            System.out.println("Queue Underflow! Queue boş.");
            return -1;
        }

        // Ön taraftaki node'un değerini al
        int dequeuedValue = front.data;

        // front'u bir sonraki node'a kaydır
        front = front.next;

        // Eğer queue boşaldıysa rear'ı da null yap
        if (front == null) {
            rear = null;
        }

        size--;
        return dequeuedValue;
    }

    /**
     * PEEK (FRONT) - Queue'nun önündeki elemanı gösterir
     * Time Complexity: O(1)
     * Node'u silmeden sadece değerini döndürür
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue boş!");
            return -1;
        }
        // Ön taraftaki node'un değerini döndür
        return front.data;
    }

    /**
     * REAR - Queue'nun arkasındaki elemanı gösterir
     * Time Complexity: O(1)
     */
    public int rear() {
        if (isEmpty()) {
            System.out.println("Queue boş!");
            return -1;
        }
        return rear.data;
    }

    // Queue boş mu kontrolü
    public boolean isEmpty() {
        return front == null;
    }

    // Queue boyutunu döndürür
    public int size() {
        return size;
    }
}


// ==================== TEST SINIFI ====================

public class QueueDemo {
    public static void main(String[] args) {
        System.out.println("===== ARRAY BASED QUEUE (Circular) =====");
        ArrayQueue arrayQueue = new ArrayQueue(5);

        // Enqueue işlemleri
        arrayQueue.enqueue(10);
        arrayQueue.enqueue(20);
        arrayQueue.enqueue(30);
        arrayQueue.enqueue(40);

        // Peek işlemi - sadece görüntüler
        System.out.println("Öndeki eleman: " + arrayQueue.peek());
        System.out.println("Arkadaki eleman: " + arrayQueue.rear());

        // Dequeue işlemleri
        System.out.println("Çıkarılan: " + arrayQueue.dequeue());
        System.out.println("Çıkarılan: " + arrayQueue.dequeue());

        // Circular özelliği test et - yeni eklemeler
        arrayQueue.enqueue(50);
        arrayQueue.enqueue(60);

        System.out.println("Queue boyutu: " + arrayQueue.size());

        System.out.println("\n===== LINKED LIST BASED QUEUE =====");
        LinkedListQueue linkedQueue = new LinkedListQueue();

        // Enqueue işlemleri
        linkedQueue.enqueue(100);
        linkedQueue.enqueue(200);
        linkedQueue.enqueue(300);
        linkedQueue.enqueue(400);

        // Peek işlemi
        System.out.println("Öndeki eleman: " + linkedQueue.peek());
        System.out.println("Arkadaki eleman: " + linkedQueue.rear());

        // Dequeue işlemleri
        System.out.println("Çıkarılan: " + linkedQueue.dequeue());
        System.out.println("Çıkarılan: " + linkedQueue.dequeue());

        System.out.println("Queue boyutu: " + linkedQueue.size());

        System.out.println("\n===== QUEUE FIFO PRENSİBİ =====");
        LinkedListQueue testQueue = new LinkedListQueue();
        testQueue.enqueue(1);
        testQueue.enqueue(2);
        testQueue.enqueue(3);

        System.out.println("Sırayla çıkan: " + testQueue.dequeue()); // 1
        System.out.println("Sırayla çıkan: " + testQueue.dequeue()); // 2
        System.out.println("Sırayla çıkan: " + testQueue.dequeue()); // 3
    }
}

/*
ARRAY vs LINKED LIST QUEUE KARŞILAŞTIRMASI:

ARRAY BASED QUEUE (Circular):
✓ Artı: Cache-friendly, hızlı erişim
✓ Artı: Circular kullanımla alan verimliliği
✗ Eksi: Sabit boyut, overflow riski
✓ Kullanım: Maksimum boyut biliniyorsa

LINKED LIST BASED QUEUE:
✓ Artı: Dinamik boyut, sınırsız büyüme
✓ Artı: Overflow riski yok
✗ Eksi: Her node için ekstra pointer memory
✗ Eksi: Cache performansı daha düşük
✓ Kullanım: Boyut önceden bilinmiyorsa

QUEUE vs STACK FARKI:
- Queue: FIFO (First In First Out) - İlk giren ilk çıkar
- Stack: LIFO (Last In First Out) - Son giren ilk çıkar

Her iki implementasyonda da:
- Enqueue: O(1)
- Dequeue: O(1)
- Peek/Front: O(1)
- Rear: O(1)

Circular Array: front ve rear indeksleri modulo operatörü 
ile döngüsel olarak hareket eder, böylece alan verimli kullanılır.
*/