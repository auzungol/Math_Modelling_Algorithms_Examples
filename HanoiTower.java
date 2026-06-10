public class TowerOfHanoi { // [cite: 97]

    public static void solveHanoi(int n, char fromRod, char toRod, char auxRod) { // [cite: 98]
        if (n == 1) { // [cite: 98]
            System.out.println("Move disk 1 from " + fromRod + " to " + toRod); // [cite: 99, 100]
            return; // [cite: 100]
        }

        solveHanoi(n - 1, fromRod, auxRod, toRod); // [cite: 101]
        System.out.println("Move disk " + n + " from " + fromRod + " to " + toRod); // [cite: 102]
        solveHanoi(n - 1, auxRod, toRod, fromRod); // [cite: 103]
    }

    public static void main(String[] args) { // [cite: 105]
        int n = 3; // Disk sayısı [cite: 107]
        solveHanoi(n, 'A', 'C', 'B'); // [cite: 108]
    }
}