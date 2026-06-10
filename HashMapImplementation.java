import java.util.HashMap;
import java.util.Map;

public class HashMapKapsamliOrnek {

    public static void main(String[] args) {

        // 1. OLUŞTURMA
        // String tipinde anahtarlar (İsim) ve Integer tipinde değerler (Maaş) tutan bir HashMap
        Map<String, Integer> calisanMaaslari = new HashMap<>();

        System.out.println("--- 2. EKLEME İŞLEMLERİ ---");
        calisanMaaslari.put("Ahmet", 25000);
        calisanMaaslari.put("Ayşe", 30000);
        calisanMaaslari.put("Mehmet", 22000);

        // put() metodu: Eğer anahtar zaten varsa, değerini ezer ve eski değeri döndürür.
        Integer eskiMaas = calisanMaaslari.put("Ahmet", 28000);
        System.out.println("Ahmet'in eski maaşı: " + eskiMaas + ", Yeni maaşı: 28000");

        // putIfAbsent(): Sadece anahtar yoksa (veya değeri null ise) ekleme yapar.
        calisanMaaslari.putIfAbsent("Fatma", 35000);
        calisanMaaslari.putIfAbsent("Ayşe", 50000); // Ayşe zaten olduğu için bu işlem yok sayılır.


        System.out.println("\n--- 3. OKUMA VE KONTROL İŞLEMLERİ ---");
        // get(): Anahtara karşılık gelen değeri getirir. Yoksa null döner.
        System.out.println("Ayşe'nin maaşı: " + calisanMaaslari.get("Ayşe"));

        // getOrDefault(): Anahtar varsa değerini, yoksa belirlediğimiz varsayılan değeri döner.
        System.out.println("Ali'nin maaşı (Yoksa 0 dön): " + calisanMaaslari.getOrDefault("Ali", 0));

        // containsKey() ve containsValue(): Anahtar veya değerin varlığını kontrol eder (true/false)
        System.out.println("Haritada 'Fatma' var mı? " + calisanMaaslari.containsKey("Fatma"));
        System.out.println("Haritada '22000' maaş alan biri var mı? " + calisanMaaslari.containsValue(22000));

        // size() ve isEmpty(): Boyut ve boşluk kontrolü
        System.out.println("Toplam çalışan sayısı: " + calisanMaaslari.size());
        System.out.println("Liste boş mu? " + calisanMaaslari.isEmpty());


        System.out.println("\n--- 4. GÜNCELLEME VE SİLME İŞLEMLERİ ---");
        // replace(): Yalnızca anahtar haritada mevcutsa değeri günceller.
        calisanMaaslari.replace("Mehmet", 24000);

        // remove(): Anahtara göre elemanı siler.
        calisanMaaslari.remove("Mehmet");
        // İki parametreli remove: Sadece anahtar VE değer eşleşiyorsa siler.
        calisanMaaslari.remove("Ahmet", 10000); // Ahmet var ama maaşı 10000 olmadığı için silinmez.


        System.out.println("\n--- 5. İLERİ DÜZEY İŞLEMLER (Java 8+) ---");
        // computeIfAbsent(): Anahtar yoksa, verilen fonksiyonu çalıştırıp sonucu ekler.
        calisanMaaslari.computeIfAbsent("Zeynep", k -> 20000 + 5000);

        // computeIfPresent(): Anahtar varsa, mevcut değeri alıp yeni bir değer hesaplar.
        calisanMaaslari.computeIfPresent("Ayşe", (anahtar, mevcutDeger) -> mevcutDeger + 5000); // Ayşe'ye 5000 zam

        // merge(): Anahtar yoksa ekler, varsa mevcut değer ile yeni değeri birleştirir.
        calisanMaaslari.merge("Fatma", 3000, (eskiDeger, eklenenDeger) -> eskiDeger + eklenenDeger); // Fatma'ya 3000 bonus


        System.out.println("\n--- 6. İTERASYON (HARİTA ÜZERİNDE GEZİNME) ---");

        // Yöntem A: forEach ve Lambda (En modern ve kısa yol - Java 8+)
        System.out.println("Yöntem A (forEach):");
        calisanMaaslari.forEach((isim, maas) -> System.out.println(isim + ": " + maas + " TL"));

        // Yöntem B: entrySet() (Hem anahtar hem de değere aynı anda ihtiyaç varsa en performanslı yöntem)
        System.out.println("\nYöntem B (entrySet):");
        for (Map.Entry<String, Integer> entry : calisanMaaslari.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Yöntem C: Sadece anahtarlar (keySet) veya sadece değerler (values)
        System.out.println("\nSadece İsimler (keySet): " + calisanMaaslari.keySet());
        System.out.println("Sadece Maaşlar (values): " + calisanMaaslari.values());


        System.out.println("\n--- 7. TEMİZLEME ---");
        // clear(): Haritadaki tüm elemanları siler.
        calisanMaaslari.clear();
        System.out.println("Clear sonrası eleman sayısı: " + calisanMaaslari.size());
    }
}