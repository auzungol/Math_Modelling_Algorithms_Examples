import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    // A. Yığını iki kuyruk kullanarak başlatma
    public MyStack() {
        queue1 = new LinkedList<>(); // 1 work
        queue2 = new LinkedList<>(); // 1 work
    }

    // x elemanını yığının en üstüne ekler
    public void push(int x) {
        queue2.add(x); // 1 work

        while (!queue1.isEmpty()) { // n work
            queue2.add(queue1.remove()); // 2 work
        }

        // Kuyrukları takas et (Swap)
        Queue<Integer> temp = queue1; // 1 work
        queue1 = queue2; // 1 work
        queue2 = temp; // 1 work
    }

    // Yığının en üstündeki elemanı çıkarır ve döndürür
    public int pop() {
        return queue1.remove(); // 1 work
    }

    // En üstteki elemanı getirir
    public int top() {
        return queue1.peek(); // 1 work
    }

    // Yığının boş olup olmadığını kontrol eder
    public boolean isEmpty() {
        return queue1.isEmpty(); // 1 work
    }

    // B. Main metodu içindeki test durumu
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.top()); // Çıktı: 3
        System.out.println(stack.pop()); // Çıktı: 3
        System.out.println(stack.top()); // Çıktı: 2
        System.out.println(stack.isEmpty()); // Çıktı: false

        stack.pop();
        stack.pop();
        System.out.println(stack.isEmpty()); // Çıktı: true
    }
}