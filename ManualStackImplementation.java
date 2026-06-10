// ==================== ARRAY BASED STACK ====================

class ArrayStack {
    private int[] stackArray;  // Stack elemanlarını tutan dizi
    private int top;           // Stack'in tepesini gösteren indeks
    private int capacity;      // Stack'in maksimum kapasitesi

    // Constructor - Stack'i başlatır
    public ArrayStack(int size) {
        capacity = size;
        stackArray = new int[capacity];
        top = -1;  // Boş stack için -1 değeri
    }

    /**
     * PUSH - Stack'e eleman ekler
     * Time Complexity: O(1)
     * Stack'in tepesine yeni eleman yerleştirir
     */
    public void push(int value) {
        // Stack dolu mu kontrolü
        if (isFull()) {
            System.out.println("Stack Overflow! Stack dolu.");
            return;
        }
        // top indeksini artır ve yeni elemanı ekle
        stackArray[++top] = value;
        System.out.println(value + " stack'e eklendi.");
    }

    /**
     * POP - Stack'ten eleman çıkarır
     * Time Complexity: O(1)
     * En üstteki elemanı kaldırır ve değerini döndürür
     */
    public int pop() {
        // Stack boş mu kontrolü
        if (isEmpty()) {
            System.out.println("Stack Underflow! Stack boş.");
            return -1;
        }
        // Tepedeki elemanı al ve top indeksini azalt
        int poppedValue = stackArray[top--];
        return poppedValue;
    }

    /**
     * PEEK - Stack'in tepesindeki elemanı gösterir
     * Time Complexity: O(1)
     * Elemanı silmeden sadece görüntüler
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack boş!");
            return -1;
        }
        // Tepedeki elemanı döndür ama silme
        return stackArray[top];
    }

    // Stack boş mu kontrolü
    public boolean isEmpty() {
        return top == -1;
    }

    // Stack dolu mu kontrolü
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Stack boyutunu döndürür
    public int size() {
        return top + 1;
    }
}


// ==================== LINKED LIST BASED STACK ====================

class Node {
    int data;      // Node'un değeri
    Node next;     // Bir sonraki node'u gösteren referans

    // Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListStack {
    private Node top;  // Stack'in tepesini gösteren node
    private int size;  // Stack'teki eleman sayısı

    // Constructor - Boş stack oluşturur
    public LinkedListStack() {
        top = null;
        size = 0;
    }

    /**
     * PUSH - Stack'e eleman ekler
     * Time Complexity: O(1)
     * Yeni node'u linked list'in başına ekler
     */
    public void push(int value) {
        // Yeni node oluştur
        Node newNode = new Node(value);

        // Yeni node'un next'i mevcut top'u göstersin
        newNode.next = top;

        // top'u yeni node olarak güncelle
        top = newNode;
        size++;

        System.out.println(value + " stack'e eklendi.");
    }

    /**
     * POP - Stack'ten eleman çıkarır
     * Time Complexity: O(1)
     * En üstteki node'u kaldırır ve değerini döndürür
     */
    public int pop() {
        // Stack boş mu kontrolü
        if (isEmpty()) {
            System.out.println("Stack Underflow! Stack boş.");
            return -1;
        }

        // Tepedeki node'un değerini al
        int poppedValue = top.data;

        // top'u bir sonraki node'a kaydır
        top = top.next;
        size--;

        return poppedValue;
    }

    /**
     * PEEK - Stack'in tepesindeki elemanı gösterir
     * Time Complexity: O(1)
     * Node'u silmeden sadece değerini döndürür
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack boş!");
            return -1;
        }
        // Tepedeki node'un değerini döndür
        return top.data;
    }

    // Stack boş mu kontrolü
    public boolean isEmpty() {
        return top == null;
    }

    // Stack boyutunu döndürür
    public int size() {
        return size;
    }
}


// ==================== TEST SINIFI ====================

public class StackDemo {
    public static void main(String[] args) {
        System.out.println("===== ARRAY BASED STACK =====");
        ArrayStack arrayStack = new ArrayStack(5);

        // Push işlemleri
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);

        // Peek işlemi - sadece görüntüler
        System.out.println("Tepedeki eleman: " + arrayStack.peek());

        // Pop işlemleri
        System.out.println("Çıkarılan: " + arrayStack.pop());
        System.out.println("Çıkarılan: " + arrayStack.pop());

        System.out.println("Stack boyutu: " + arrayStack.size());

        System.out.println("\n===== LINKED LIST BASED STACK =====");
        LinkedListStack linkedStack = new LinkedListStack();

        // Push işlemleri
        linkedStack.push(100);
        linkedStack.push(200);
        linkedStack.push(300);

        // Peek işlemi
        System.out.println("Tepedeki eleman: " + linkedStack.peek());

        // Pop işlemleri
        System.out.println("Çıkarılan: " + linkedStack.pop());
        System.out.println("Çıkarılan: " + linkedStack.pop());

        System.out.println("Stack boyutu: " + linkedStack.size());
    }
}

/*
ARRAY vs LINKED LIST STACK KARŞILAŞTIRMASI:

ARRAY BASED STACK:
: Daha hızlı erişim, cache-friendly
✗ Eksi: Sabit boyut, overflow riski
✓ Kullanım: Maksimum boyut biliniyorsa

LINKED LIST BASED STACK:
✓ Artı: Dinamik boyut, overflow yok
✗ Eksi: Her node için ekstra pointer memory
✓ Kullanım: Boyut önceden bilinmiyorsa

Her iki implementasyonda da:
- Push: O(1)
- Pop: O(1)
- Peek: O(1)
*/