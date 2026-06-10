import java.util.Stack;

class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    // A. Kuyruğu iki yığın kullanarak başlatma
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // x elemanını kuyruğun arkasına ekler
    public void enqueue(int x) {
        stack1.push(x); // 1 work
    }

    // Kuyruğun önündeki elemanı çıkarır ve döndürür
    public int dequeue() {
        if (stack2.isEmpty()) { // 1 work
            while (!stack1.isEmpty()) { // n work
                stack2.push(stack1.pop()); // 2 work
            }
        }
        return stack2.pop(); // 1 work
    }

    // Kuyruğun en önündeki elemanı getirir
    public int peek() {
        if (stack2.isEmpty()) { // 1 work
            while (!stack1.isEmpty()) { // n work
                stack2.push(stack1.pop()); // 2 work
            }
        }
        return stack2.peek(); // 1 work
    }

    // Kuyruğun boş olup olmadığını kontrol eder
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty(); // 3 work
    }

    // B. Main metodu içindeki test durumu
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.peek()); // Çıktı: 1
        System.out.println(queue.dequeue()); // Çıktı: 1
        System.out.println(queue.peek()); // Çıktı: 2
        System.out.println(queue.isEmpty()); // Çıktı: false

        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.isEmpty()); // Çıktı: true
    }
}