/**
 * Ad Soyad: EDANUR YASAK
 * Öğrenci No: 250541018
 * Proje:NOT SİSTEMİ
 * Tarih: 21.11.2025
 */
import java.util.Scanner;

public class StudentEvaluation {

    private int vize;
    private int finalNotu;
    private int odev;
    private double ortalama;

    public StudentEvaluation(int vize, int finalNotu, int odev) {
        this.vize = vize;
        this.finalNotu = finalNotu;
        this.odev = odev;
        this.ortalama = calculateAverage(vize, finalNotu, odev);
    }

    // --- ZORUNLU METOT 1: Ortalama Hesaplama ---
    public double calculateAverage(int vize, int finalNotu, int odev) {
        double avg = (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
        return Math.round(avg * 100.0) / 100.0;
    }

    // --- ZORUNLU METOT 2: Geçme/Kalma Kontrolü ---
    public boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // --- ZORUNLU METOT 3: Harf Notu Belirleme ---
    public String getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return "AA";
        } else if (ortalama >= 85) {
            return "BA";
        } else if (ortalama >= 80) {
            return "BB";
        } else if (ortalama >= 70) {
            return "CB";
        } else if (ortalama >= 60) {
            return "CC";
        } else if (ortalama >= 50) {
            return "DD";
        } else if (ortalama >= 40) {
            return "FD";
        } else {
            return "FF";
        }
    }

    // --- ZORUNLU METOT 4: Onur Listesi Kontrolü ---
    public boolean isHonorList(double ortalama, int vize, int finalNotu, int odev) {
        return (ortalama >= 85) && (vize >= 70) && (finalNotu >= 70) && (odev >= 70);
    }

    // --- ZORUNLU METOT 5: Bütünleme Hakkı Kontrolü ---
    public boolean hasRetakeRight(double ortalama) {
        return ortalama >= 40 && ortalama < 50;
    }

    // --- Rapor Metodu ---
    public void printReport() {
        System.out.println("\n--- Not Değerlendirme Raporu ---");
        System.out.println("Vize: " + vize + ", Final: " + finalNotu + ", Ödev: " + odev);
        System.out.println("Ortalama: " + **ortalama**);
        System.out.println("Harf Notu: " + **getLetterGrade(ortalama)**);

        String gecmeDurumu = isPassingGrade(ortalama) ? "GEÇTİ" : "KALDI";
        System.out.println("Durum: " + **gecmeDurumu**);

        if (isHonorList(ortalama, vize, finalNotu, odev)) {
            System.out.println("Ek Bilgi: Onur Listesinde");
        }

        // Bütünleme hakkı sadece KALAN ve ortalaması 40-50 arasında olanlar içindir.
        if (!isPassingGrade(ortalama) && hasRetakeRight(ortalama)) {
            System.out.println("Ek Bilgi: Bütünleme Hakkı VAR");
        } else if (!isPassingGrade(ortalama)) {
            System.out.println("Ek Bilgi: Bütünleme Hakkı Yok (Ortalama < 40)");
        }
        
        System.out.println("----------------------------------------");
    }

    // --- Main Metodu ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Vize Notu (0-100): ");
            int vizeNotu = scanner.nextInt();

            System.out.print("Final Notu (0-100): ");
            int finalNotu = scanner.nextInt();

            System.out.print("Ödev Notu (0-100): ");
            int odevNotu = scanner.nextInt();

            if (vizeNotu < 0 || vizeNotu > 100 || finalNotu < 0 || finalNotu > 100 || odevNotu < 0 || odevNotu > 100) {
                System.out.println("Hata: Notlar 0 ile 100 arasında olmalıdır.");
                return;
            }

            StudentEvaluation ogrenci = new StudentEvaluation(vizeNotu, finalNotu, odevNotu);
            ogrenci.printReport();

        } catch (java.util.InputMismatchException e) {
            System.out.println("Hata: Lütfen geçerli sayısal değerler giriniz.");
        } finally {
            scanner.close();
        }
    }
}


