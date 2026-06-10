import java.util.HashMap;

class Solution {
    // A. İlk tekrar etmeyen karakterin indeksini bulan algoritma
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>(); // 1 work

        // 1. Adım: Frekans haritasını doldur
        for (char c : s.toCharArray()) { // n work
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1); // 3 work
        }

        // 2. Adım: Frekansı 1 olan ilk karakteri bul
        for (int i = 0; i < s.length(); i++) { // n work
            if (frequencyMap.get(s.charAt(i)) == 1) { // 1 work
                return i; // 1 work
            }
        }
        return -1; // 1 work
    }

    // B. Main metodu içindeki test durumları
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Durumu 1
        System.out.println(solution.firstUniqChar("leetcode")); // Çıktı: 0

        // Test Durumu 2
        System.out.println(solution.firstUniqChar("loveleetcode")); // Çıktı: 2

        // Test Durumu 3: Benzersiz karakter yok
        System.out.println(solution.firstUniqChar("aabb")); // Çıktı: -1
    }
}