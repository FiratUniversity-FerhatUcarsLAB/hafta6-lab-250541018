/**
 * Ad Soyad: EDANUR YASAK
 * Öğrenci No: 250541018
 * Proje:SİNEMA BİLETİ
 * Tarih: 21.11.2025
 */
import java.lang.Math;
import java.util.Scanner; 

public class SinemaBiletSistemi {

    // --- TEMEL FİYAT VE ZAMAN KONTROLLERİ ---

    public static boolean isWeekend(int gun) {
        // 6 = Cumartesi, 7 = Pazar
        return gun == 6 || gun == 7;
    }

    public static boolean isMatinee(int saat) {
        // 12:00 öncesi matinedir
        return saat < 12;
    }

    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);
        double basePrice = 0.0;

        if (!weekend) { // Hafta İçi (Pzt-Prş)
            if (matinee) {
                basePrice = 45.0; // Hafta içi matine
            } else {
                basePrice = 65.0; // Hafta içi normal
            }
        } else { // Hafta Sonu (Cuma-Paz)
            if (matinee) {
                basePrice = 55.0; // Hafta sonu matine
            } else {
                basePrice = 85.0; // Hafta sonu normal
            }
        }
        return basePrice;
    }

    // --- İNDİRİM VE EK ÜCRET HESAPLAMALARI ---

    public static double calculateDiscount(int yas, int meslek, int gun) {
        double maxDiscount = 0.0;
        boolean isWeekend = isWeekend(gun);

        // 1. Yaşa Bağlı İndirimler
        if (yas >= 65) {
            maxDiscount = 0.30; // %30
        }
        if (yas < 12 && 0.25 > maxDiscount) {
            maxDiscount = 0.25; // %25
        }

        // 2. Mesleğe Bağlı İndirimler 
        switch (meslek) {
            case 1: // Öğrenci
                double ogrenciIndirimi = isWeekend ? 0.15 : 0.20; // H.Sonu %15, H.İçi %20
                if (ogrenciIndirimi > maxDiscount) {
                    maxDiscount = ogrenciIndirimi;
                }
                break;

            case 2: // Öğretmen
                if (gun == 3) { // Sadece Çarşamba %35
                    if (0.35 > maxDiscount) {
                        maxDiscount = 0.35;
                    }
                }
                break;

            case 3: // Diğer
                break;

            default:
                break;
        }

        return maxDiscount;
    }

    public static double getFormatExtra(int filmTuru) {
        double extra = 0.0;

      
        switch (filmTuru) {
            case 1: // 2D
                extra = 0.0;
                break;
            case 2: // 3D
                extra = 25.0; // +25 TL
                break;
            case 3: // IMAX
                extra = 35.0; // +35 TL
                break;
            case 4: // 4DX
                extra = 50.0; // +50 TL
                break;
            default:
                extra = 0.0;
                break;
        }
        return extra;
    }

    // --- SONUÇ METOTLARI ---

    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discountedPrice = basePrice * (1.0 - discountRate);
        double formatExtra = getFormatExtra(filmTuru);

        double finalPrice = discountedPrice + formatExtra;

        // Fiyatı 2 ondalık basamağa yuvarla
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        // Raporlama için ara değerleri hesapla
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double discountAmount = basePrice * discountRate;
        double formatExtra = getFormatExtra(filmTuru);

        // Girdi kodlarının metin karşılıkları
        String[] gunler = {"Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar"};
        String[] meslekler = {"Öğrenci", "Öğretmen", "Diğer"};
        String[] filmler = {"2D", "3D", "IMAX", "4DX"};

        System.out.println("\n---  SİNEMA BİLET DETAYI ️ ---");
        System.out.println("---------------------------------");
        System.out.println("  Seans: " + gunler[gun - 1] + " (" + saat + ":00)");
        System.out.println(" Yaş: " + yas + ", Meslek: " + meslekler[meslek - 1]);
        System.out.println(" Film Formatı: " + filmler[filmTuru - 1]);
        System.out.println("---------------------------------");
        System.out.printf("Temel Bilet Fiyatı: %.2f TL\n", basePrice);
        System.out.printf("Uygulanan İndirim Oranı: %.0f%%\n", discountRate * 100);
        System.out.printf("İndirim Miktarı: -%.2f TL\n", discountAmount);
        System.out.printf("Format Ek Ücreti (%s): +%.2f TL\n", filmler[filmTuru - 1], formatExtra);
        System.out.println("---------------------------------");
        System.out.printf("** Ödenecek Nihai Tutar: %.2f TL**\n", finalPrice);
        System.out.println("---------------------------------");
    }

    // --- ANA METOT (KULLANICIDAN GİRDİ ALMA) ---

    public static void main(String[] args) {
        // Scanner nesnesi oluşturulur, kullanıcıdan girdi almayı sağlar.
        Scanner scanner = new Scanner(System.in);

        int gun, saat, yas, meslek, filmTuru;

        System.out.println(" Sinema Bileti Fiyatlandırma Sistemine Hoş Geldiniz!");
        System.out.println("-----------------------------------------------------");

        // 1. GÜN GİRDİSİ
        System.out.println("1. Lütfen hangi gün bilet alacağınızı seçin:");
        System.out.println("   (1=Pazartesi, 2=Salı, 3=Çarşamba, ..., 7=Pazar)");
        System.out.print("Gün seçimi (1-7): ");
        gun = scanner.nextInt();

        // 2. SAAT GİRDİSİ
        System.out.println("\n2. Seans saatini giriniz (09:00 için 9, 15:00 için 15 gibi):");
        System.out.print("Saat: ");
        saat = scanner.nextInt();

        // 3. YAŞ GİRDİSİ
        System.out.println("\n3. Yaşınızı giriniz:");
        System.out.print("Yaş: ");
        yas = scanner.nextInt();

        // 4. MESLEK GİRDİSİ
        System.out.println("\n4. Mesleğinizi seçin:");
        System.out.println("   (1=Öğrenci, 2=Öğretmen, 3=Diğer)");
        System.out.print("Meslek seçimi (1-3): ");
        meslek = scanner.nextInt();

        // 5. FİLM TÜRÜ GİRDİSİ
        System.out.println("\n5. Film formatını seçin:");
        System.out.println("   (1=2D, 2=3D, 3=IMAX, 4=4DX)");
        System.out.print("Film türü seçimi (1-4): ");
        filmTuru = scanner.nextInt();

        scanner.close();

        // Hesaplamayı yapacak ve sonucu ekrana yazdıracak metodu çağırıyoruz.
        generateTicketInfo(gun, saat, yas, meslek, filmTuru);
    }
}

