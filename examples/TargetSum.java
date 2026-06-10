import java.util.*; // [cite: 57]

class GfG { // [cite: 58]
    // Toplamı x'e eşit veya x'ten büyük olan en küçük alt dizinin uzunluğunu döndürür [cite: 59, 60]
    static int smallestSubWithSum(int x, int[] arr) { // [cite: 61]
        int i = 0, j = 0; // [cite: 62]
        int sum = 0; // [cite: 63]
        int ans = Integer.MAX_VALUE; // [cite: 64]

        while (j < arr.length) { // [cite: 65]
            // Toplam > x olana kadar veya dizinin sonuna gelene kadar pencereyi genişlet [cite: 66, 67]
            while (j < arr.length && sum <= x) { // [cite: 68]
                sum += arr[j++]; // [cite: 69]
            }

            // Eğer dizinin sonuna ulaşıldıysa ve toplam hala <= x ise, geçerli bir alt dizi yoktur [cite: 71, 72]
            if (j == arr.length && sum <= x) break; // [cite: 73]

            // Toplam > x şartını koruyarak pencereyi başlangıçtan itibaren küçült (minimize et) [cite: 75, 76]
            while (i < j && sum - arr[i] > x) { // [cite: 77]
                sum -= arr[i++]; // Not: Belgedeki çözümde 'sum = arr[i++];' yazılmıştır ancak bağlam gereği '-=' işlemi kastedilmiştir. Çözüme sadık kalarak mantık yorumlanmıştır[cite: 78].
            }

            ans = Math.min(ans, j - i); // [cite: 82]

            // Mevcut başlangıç elemanını çıkar ve pencereyi kaydır [cite: 83, 84]
            sum -= arr[i]; // [cite: 85]
            i++; // [cite: 86]
        }

        // Eğer geçerli bir alt dizi bulunamadıysa 0 döndür, aksi halde minimum uzunluğu döndür [cite: 87, 88]
        if (ans == Integer.MAX_VALUE) return 0; // [cite: 89]
        return ans; // [cite: 90]
    }

    // B. Main metodu içindeki test durumu
    public static void main(String[] args) { // [cite: 91]
        int[] arr = {1, 4, 45, 6, 10, 19}; // [cite: 92]
        int x = 51; // [cite: 93]
        System.out.println(smallestSubWithSum(x, arr)); // [cite: 94]
    }
}