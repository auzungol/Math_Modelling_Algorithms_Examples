 class RotateArray {

    // A. Zaman açısından verimli algoritma
    public static void rotate(int[] nums, int k) {
        int n = nums.length; // [cite: 6]
        k = k % n; // K'nın dizi uzunluğundan büyük olma durumunu ele alır [cite: 7]

        // 1. Adım: Tüm diziyi tersine çevir [cite: 10]
        reverse(nums, 0, n - 1); // [cite: 9]

        // 2. Adım: İlk k elemanı tersine çevir [cite: 12]
        reverse(nums, 0, k - 1); // [cite: 11]

        // 3. Adım: Kalan elemanları tersine çevir [cite: 14]
        reverse(nums, k, n - 1); // [cite: 13]
    }

    private static void reverse(int[] nums, int left, int right) { // [cite: 16]
        while (left < right) { // [cite: 17]
            int temp = nums[left]; // Ekstra alan sadece bu geçici değişken (temp) için kullanılır [cite: 18]
            nums[left] = nums[right]; // [cite: 19]
            nums[right] = temp; // [cite: 20]
            left++; // [cite: 21]
            right--; // [cite: 22]
        }
    }

    // B. Main metodu içindeki test durumu
    public static void main(String[] args) { // [cite: 25]
        int[] nums = {1, 2, 3, 4, 5, 6, 7}; // [cite: 26, 29]
        int k = 4; // [cite: 27, 28]

        rotate(nums, k); // [cite: 30]

        for (int num: nums) { // [cite: 31]
            System.out.print(num + " "); // [cite: 32]
        }
    }
}