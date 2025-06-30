/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/


package simulasyon;

import java.util.List;

public class Simulasyon {

    private List<Gezegen> gezegenler;
    private List<Kisi> kisiler;
    private List<UzayAraci> araclar;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String WHITE = "\u001B[37m";

    public void baslat() {
        gezegenler = DosyaOkuma.gezegenleriOku("resources/gezegenler.txt");
        kisiler = DosyaOkuma.kisileriOku("resources/kisiler.txt");
        araclar = DosyaOkuma.araclariOku("resources/araclar.txt");

        kisileriAraclaraAta();
        imhaKontrolu(); // Başlangıçta boş veya herkes ölmüş araçları imha edecek

        while (!tumAraclarUlasti()) {
            Main.clearScreen();
            gezegenZamanlariniIlerle();
            araclariKontrolEtVeIlerle();
            yazdirDurum();
            bekle(500);
        }

        Main.clearScreen();
        System.out.println("🚀 Tüm araçlar hedefe ulaştı. Simülasyon tamamlandı!\n");
        yazdirDurum(); // En son bununla beraber son çıktıyı tekrar yazacak
    }

    private void kisileriAraclaraAta() {
        for (Kisi kisi : kisiler) {
            for (UzayAraci arac : araclar) {
                if (kisi.getBulunduguArac().equals(arac.getIsim())) {
                    arac.yolcuEkle(kisi);
                    break;
                }
            }
        }
    }

    private void gezegenZamanlariniIlerle() {
        for (Gezegen gezegen : gezegenler) {
            gezegen.saatIlerle();
        }
    }

    private void imhaKontrolu() {
        for (UzayAraci arac : araclar) {
            if (!arac.isImha()) {
                arac.imhaKontrolEt();
            }
        }
    }

    private void araclariKontrolEtVeIlerle() {
        for (UzayAraci arac : araclar) {
            if (arac.isImha() || arac.hedefeUlastiMi()) continue;

            for (Kisi yolcu : arac.getYolcular()) {
                yolcu.omurAzalt(1);
            }

            arac.imhaKontrolEt();
            if (arac.isImha()) continue;

            Gezegen cikisGezegen = gezegenBul(arac.getCikisGezegeni());
            if (cikisGezegen == null) continue;

            if (cikisGezegen.getZaman().tarihEsitVeyaGectiMi(arac.getCikisTarihi())) {
                arac.birSaatIlerle();
            }
        }
    }

    private boolean tumAraclarUlasti() {
        for (UzayAraci arac : araclar) {
            if (!arac.hedefeUlastiMi() && !arac.isImha()) {
                return false;
            }
        }
        return true;
    }

    private Gezegen gezegenBul(String ad) {
        for (Gezegen gezegen : gezegenler) {
            if (gezegen.getAd().equals(ad)) {
                return gezegen;
            }
        }
        return null;
    }

    private void bekle(int milisaniye) {
        try {
            Thread.sleep(milisaniye);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void yazdirDurum() {
        for (Gezegen gezegen : gezegenler) {
            while (gezegen.getNufus() > 0) {
                gezegen.nufusAzalt();
            }
        }

        for (UzayAraci arac : araclar) {
            for (Kisi yolcu : arac.getYolcular()) {
                if (yolcu.hayattaMi()) {
                    if (arac.hedefeUlastiMi() && !arac.isImha()) {
                        Gezegen varis = gezegenBul(arac.getVarisGezegeni());
                        if (varis != null) varis.nufusArttir();
                    } else if (!arac.isImha() && !arac.hedefeUlastiMi() && arac.getDurum().equals("Bekliyor")) {
                        Gezegen cikis = gezegenBul(arac.getCikisGezegeni());
                        if (cikis != null) cikis.nufusArttir();
                    }
                }
            }
        }
// İstenilene benzer çıktı
        System.out.println("Gezegenler:\n");

        System.out.printf("%-15s", " ");
        for (Gezegen g : gezegenler) {
            System.out.printf("| %-13s", g.getAd());
        }
        System.out.println("|");

        System.out.printf("%-15s", "Tarih");
        for (Gezegen g : gezegenler) {
            System.out.printf("| %-13s", g.getZaman().getFormattedDate());
        }
        System.out.println("|");

        System.out.printf("%-15s", "Nüfus");
        for (Gezegen g : gezegenler) {
            System.out.printf("| %-13d", g.getNufus());
        }
        System.out.println("|\n");

        System.out.println("\nUzay Araçları:\n");
        System.out.printf("%-12s%-15s%-12s%-12s%-22s%-20s\n",
                "Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Varış Tarihi");

        for (UzayAraci arac : araclar) {
            String hedefTarih = "--";
            if (!arac.isImha()) {
                Gezegen varisGezegen = gezegenBul(arac.getVarisGezegeni());
                if (varisGezegen != null) {
                    hedefTarih = arac.getCikisTarihi()
                            .arttirSaat(arac.getMesafe(), varisGezegen.getGunSaat())
                            .getFormattedDate();
                }
            }

            String renkKodu = durumRenkKodu(arac);

            System.out.printf("%s%-12s%-15s%-12s%-12s%-22s%-20s%s\n",
                    renkKodu,
                    arac.getIsim(),
                    arac.getDurum(),
                    arac.getCikisGezegeni(),
                    arac.getVarisGezegeni(),
                    arac.isImha() ? "--" : arac.getKalanMesafe(),
                    hedefTarih,
                    RESET);
        }
    }
// Daha kolay bir şekilde görebilmek için renklerle kodladım
    private String durumRenkKodu(UzayAraci arac) {
        if (arac.isImha()) {
            return RED;
        } else if (arac.hedefeUlastiMi()) {
            return GREEN;
        } else if (arac.getDurum().equals("Yolda")) {
            return YELLOW;
        } else {
            return WHITE;
        }
    }
}
