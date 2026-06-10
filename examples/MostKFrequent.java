import java.util.*;

class TopKFrequent {
    // 1. O(n) zaman karmaşıklığıyla çalışan metodun uygulanması 
    public int[] topKFrequent(int[] nums, int k) {
        // Frekansları saymak için bir HashMap kullanıyoruz.
        Map<Integer, Integer> frequencyMap = new HashMapImplementation<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // Frekansı indeks olarak kullanacağımız bir "kova" (bucket) dizisi oluşturuyoruz.
        // Bir elemanın frekansı en fazla dizinin uzunluğu kadar olabilir.
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // Kovayı sondan başa (en yüksek frekanstan en düşüğe) doğru tarıyoruz.
        int[] result = new int[k];
        int counter = 0;

        for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {
            if (bucket[pos] != null) {
                for (int num : bucket[pos]) {
                    result[counter++] = num;
                    if (counter == k) {
                        return result; // İstenen k elemana ulaştığımızda döndürüyoruz.
                    }
                }
            }
        }
        return result;
    }

    // 3. Test senaryolarının (Main metodu) yazılması [cite: 775]
    public static void main(String[] args) {
        TopKFrequent solution = new TopKFrequent();

        // Örnek 1 [cite: 776, 777, 778, 780]
        int[] nums1 = {1, 2, 1, 2, 3, 1};
        int k1 = 2;
        System.out.println("Örnek 1 Sonucu: " + Arrays.toString(solution.topKFrequent(nums1, k1)));
        // Çıktı: [1, 2]

        // Örnek 2 [cite: 781, 782, 783]
        int[] nums2 = {6, 7, 4, 5, 6, 7, 3, 7, 5};
        int k2 = 3;
        System.out.println("Örnek 2 Sonucu: " + Arrays.toString(solution.topKFrequent(nums2, k2)));
        // Çıktı: [7, 6, 5] (Sıra önemli değildir [cite: 771])
    }
}