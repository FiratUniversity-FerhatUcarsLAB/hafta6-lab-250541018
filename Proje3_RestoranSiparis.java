/**
 * Ad Soyad: EDANUR YASAK
 * Öğrenci No: 250541018
 * Proje:RESTORAN SİPARİŞ
 * Tarih: 21.11.2025
 */
import java.util.Scanner;
import java.time.LocalTime; // Saat bilgisini almak için

/**
 * Proje 3: Akıllı Restoran Sipariş Sistemi
 * Menü kategorileri ve özel indirimleri yöneten temel sipariş sistemi.
 * Switch-case kullanımı zorunludur.
 */
public class AkilliRestoranSiparisSistemi {

    // --- 4 ZORUNLU METOT: Menü Fiyatlarını Alma (Switch-Case Zorunlu) ---

    /**
     * Seçilen ana yemeğin fiyatını döndürür.
     * @param secim Ana yemeğin adı.
     * @return Fiyat (double), bulunamazsa 0.0.
     */
    public double getMainDishPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "izgara tavuk":
                return 85.0;
            case "adana kebap":
                return 120.0;
            case "levrek":
                return 110.0;
            case "mantı":
                return 65.0;
            default:
                return 0.0;
        }
    }

    /**
     * Seçilen başlangıcın fiyatını döndürür.
     * @param secim Başlangıcın adı.
     * @return Fiyat (double), bulunamazsa 0.0.
     */
    public double getAppetizerPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "çorba":
                return 25.0;
            case "humus":
                return 45.0;
            case "sigara böreği":
                return 55.0;
            default:
                return 0.0;
        }
    }

    /**
     * Seçilen içeceğin fiyatını döndürür.
     * @param secim İçeceğin adı.
     * @return Fiyat (double), bulunamazsa 0.0.
     */
    public double getDrinkPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "kola":
                return 15.0;
            case "ayran":
                return 12.0;
            case "meyve suyu":
                return 35.0;
            case "limonata":
                return 25.0;
            default:
                return 0.0;
        }
    }

    /**
     * Seçilen tatlının fiyatını döndürür.
     * @param secim Tatlının adı.
     * @return Fiyat (double), bulunamazsa 0.0.
     */
    public double getDessertPrice(String secim) {
        switch (secim.toLowerCase()) {
            case "künefe":
                return 65.0;
            case "baklava":
                return 55.0;
            case "sütlaç":
                return 35.0;
            default:
                return 0.0;
        }
    }
    
    // --- 4 ZORUNLU METOT: İndirim ve Kontrol Mekanizmaları ---

    /**
     * Combo menü şartının sağlanıp sağlanmadığını kontrol eder.
     * Şart: Ana Yemek + İçecek + Tatlı sipariş edilmişse.
     * @param anaVar Ana yemek siparişi var mı?
     * @param icecekVar İçecek siparişi var mı?
     * @param tatliVar Tatlı siparişi var mı?
     * @return Combo menü ise true.
     */
    public boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    /**
     * Happy Hour saatleri içinde olup olmadığını kontrol eder (14:00-17:00).
     * @param saat Programın çalıştığı saat.
     * @return Happy Hour ise true.
     */
    public boolean isHappyHour(LocalTime saat) {
        LocalTime happyHourBaslangic = LocalTime.of(14, 0); // 14:00
        LocalTime happyHourBitis = LocalTime.of(17, 0);    // 17:00
        
        // Happy Hour (14:00 dahil) ile (17:00 hariç) arasını kontrol eder.
        return (saat.isAfter(happyHourBaslangic) || saat.equals(happyHourBaslangic)) && 
               saat.isBefore(happyHourBitis);
    }
    
    /**
     * Toplam indirim miktarını hesaplar.
     * @param tutar İndirimsiz toplam tutar.
     * @param combo Combo menü indirimine hak kazanıldı mı?
     * @param ogrenci Öğrenci indirimi uygulanacak mı?
     * @param saat Sipariş saati (Happy Hour kontrolü için).
     * @return Toplam indirim miktarı (double).
     */
    public double calculateDiscount(double tutar, boolean combo, boolean ogrenci, LocalTime saat) {
        double indirimMiktari = 0.0;
        double genelIndirimOrani = 0.0;
        
        // 1. Combo Menü İndirimi (%15)
        if (combo) {
            genelIndirimOrani += 0.15;
            System.out.println("\t✅ Combo Menü İndirimi (%15) uygulandı.");
        }

        // 2. 200 TL üzeri İndirim (%10) - (Diğer indirimlerle birlikte uygulanır)
        if (tutar >= 200) {
            genelIndirimOrani += 0.10;
            System.out.println("\t✅ 200 TL Üzeri İndirim (%10) uygulandı.");
        }

        // 3. Öğrenci İndirimi (%10 ekstra) - (Hafta içi olduğu varsayılmıştır)
        if (ogrenci) {
            genelIndirimOrani += 0.10;
            System.out.println("\t✅ Öğrenci İndirimi (%10) uygulandı.");
        }
        
        // Toplam genel indirim tutarı
        indirimMiktari += tutar * genelIndirimOrani;

        // Happy Hour İndirimi (%20 SADECE İçeceklerde)
        // Bu indirimin hesaplanması için içecek fiyatlarının tutulması gerekir,
        // ancak zorunlu metot sadece genel indirimi hesapladığı için,
        // sadece genel indirim tutarını döndürüyoruz. 
        
        return indirimMiktari;
    }

    /**
     * %10 bahşiş önerisini hesaplar.
     * @param tutar İndirimler düşüldükten sonraki ödenecek tutar.
     * @return Bahşiş önerisi (double).
     */
    public double calculateServiceTip(double tutar) {
        return tutar * 0.10; // %10 bahşiş önerisi
    }

    // --- Main Metodu (Programın Çalıştırılması) ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AkilliRestoranSiparisSistemi sistem = new AkilliRestoranSiparisSistemi();
        
        double toplamTutar = 0.0;
        boolean anaYemekVar = false;
        boolean icecekVar = false;
        boolean tatliVar = false;
        
        // Basit bir menü gösterimi
        System.out.println("--- Akıllı Restoran Sipariş Sistemi ---");
        System.out.println("Menü: Izgara Tavuk (85), Adana Kebap (120), Levrek (110), Mantı (65)");
        System.out.println("Başlangıç: Çorba (25), Humus (45), Sigara Böreği (55)");
        System.out.println("İçecek: Kola (15), Ayran (12), Meyve Suyu (35), Limonata (25)");
        System.out.println("Tatlı: Künefe (65), Baklava (55), Sütlaç (35)");
        System.out.println("---------------------------------------");

        // --- Sipariş Alma ---
        
        // 1. Ana Yemek
        System.out.print("Ana Yemek seçiminizi giriniz (Yoksa 'yok' yazın): ");
        String anaYemekSecim = scanner.nextLine();
        double anaYemekFiyat = sistem.getMainDishPrice(anaYemekSecim);
        if (anaYemekFiyat > 0) {
            toplamTutar += anaYemekFiyat;
            anaYemekVar = true;
        }

        // 2. Başlangıç
        System.out.print("Başlangıç seçiminizi giriniz (Yoksa 'yok' yazın): ");
        String baslangicSecim = scanner.nextLine();
        toplamTutar += sistem.getAppetizerPrice(baslangicSecim);

        // 3. İçecek
        System.out.print("İçecek seçiminizi giriniz (Yoksa 'yok' yazın): ");
        String icecekSecim = scanner.nextLine();
        double icecekFiyat = sistem.getDrinkPrice(icecekSecim);
        if (icecekFiyat > 0) {
            toplamTutar += icecekFiyat;
            icecekVar = true;
        }
        
        // 4. Tatlı
        System.out.print("Tatlı seçiminizi giriniz (Yoksa 'yok' yazın): ");
        String tatliSecim = scanner.nextLine();
        double tatliFiyat = sistem.getDessertPrice(tatliSecim);
        if (tatliFiyat > 0) {
            toplamTutar += tatliFiyat;
            tatliVar = true;
        }

        // --- Ek Bilgiler ---
        System.out.print("Öğrenci misiniz? (E/H): ");
        boolean ogrenci = scanner.nextLine().equalsIgnoreCase("E");
        
        // Simülasyon saati (Gerçek zamanı kullanıyoruz)
        LocalTime mevcutSaat = LocalTime.now(); 
        
        // --- Hesaplama Özeti ---
        System.out.println("\n--- Hesap Özeti ---");
        System.out.println("1. İndirimsiz Toplam Tutar: " + String.format("%.2f", toplamTutar) + "₺");
        
        // İndirim Kontrolleri
        boolean comboHaketti = sistem.isComboOrder(anaYemekVar, icecekVar, tatliVar);
        boolean happyHour = sistem.isHappyHour(mevcutSaat);

        System.out.println("\n2. Uygulanan İndirimler:");
        
        // Happy Hour indiriminin sadece içecek tutarı üzerinden %20 hesaplanması gerekir.
        if (happyHour && icecekVar) {
            double happyHourIndirim = icecekFiyat * 0.20;
            toplamTutar -= happyHourIndirim;
            System.out.println("\t✅ Happy Hour İndirimi (İçecek %20): -" + String.format("%.2f", happyHourIndirim) + "₺");
        }
        
        // Diğer indirimleri hesaplama ve düşme
        double genelIndirimMiktari = sistem.calculateDiscount(toplamTutar, comboHaketti, ogrenci, mevcutSaat);
        toplamTutar -= genelIndirimMiktari;

        System.out.println("---------------------------------------");
        System.out.println("3. İndirimler Sonrası Ödenecek Tutar: " + String.format("%.2f", toplamTutar) + "₺");
        
        // Bahşiş Önerisi
        double bahsis = sistem.calculateServiceTip(toplamTutar);
        System.out.println("4. Bahşiş Önerisi (%10): " + String.format("%.2f", bahsis) + "₺");
        
        double sonOdenecekTutar = toplamTutar + bahsis;
        System.out.println("5. Toplam Ödeme (Bahşiş Dahil): " + String.format("%.2f", sonOdenecekTutar) + "₺");
        
        System.out.println("--- Bizi tercih ettiğiniz için teşekkür ederiz! ---");
        
        scanner.close();
    }
}
