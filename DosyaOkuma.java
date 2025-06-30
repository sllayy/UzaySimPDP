/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/


package simulasyon;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DosyaOkuma {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    // Bir dosyadaki tüm dolu satırları okur
    private static List<String> satirOku(String dosyaYolu) throws IOException {
        List<String> satirlar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                if (!satir.trim().isEmpty()) {
                    satirlar.add(satir);
                }
            }
        }
        return satirlar;
    }

    // Kisiler.txtyi okuyup kisi nesnelerine çevirir
    public static List<Kisi> kisileriOku(String dosyaYolu) {
        List<Kisi> kisiler = new ArrayList<>();
        try {
            for (String satir : satirOku(dosyaYolu)) {
                try {
                    String[] veri = satir.split("#");
                    if (veri.length == 4) {
                        String isim = veri[0];
                        int yas = Integer.parseInt(veri[1]);
                        int kalanOmur = Integer.parseInt(veri[2]);
                        String uzayAraci = veri[3];
                        kisiler.add(new Kisi(isim, yas, kalanOmur, uzayAraci));
                    } else {
                        System.out.println("[HATA] Kisiler.txt format hatası: " + satir);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[HATA] Kisiler.txt sayısal veri hatası: " + satir);
                }
            }
        } catch (IOException e) {
            System.out.println("[HATA] Kisiler.txt okunamadı: " + e.getMessage());
        }
        return kisiler;
    }

    // Araclar.txtyi okuyup uzayAraci nesnelerine çevirir
    public static List<UzayAraci> araclariOku(String dosyaYolu) {
        List<UzayAraci> araclar = new ArrayList<>();
        try {
            for (String satir : satirOku(dosyaYolu)) {
                try {
                    String[] veri = satir.split("#");
                    if (veri.length == 5) {
                        String isim = veri[0];
                        String cikis = veri[1];
                        String varis = veri[2];
                        LocalDate tarih = LocalDate.parse(veri[3], formatter);
                        int mesafe = Integer.parseInt(veri[4]);
                        araclar.add(new UzayAraci(isim, cikis, varis, new Zaman(tarih, 0), mesafe));
                    } else {
                        System.out.println("[HATA] Araclar.txt format hatası: " + satir);
                    }
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("[HATA] Araclar.txt veri hatası: " + satir);
                }
            }
        } catch (IOException e) {
            System.out.println("[HATA] Araclar.txt okunamadı: " + e.getMessage());
        }
        return araclar;
    }

    // Gezegenler.txtyi okuyup gezegen nesnelerine çevirir
    public static List<Gezegen> gezegenleriOku(String dosyaYolu) {
        List<Gezegen> gezegenler = new ArrayList<>();
        try {
            for (String satir : satirOku(dosyaYolu)) {
                try {
                    String[] veri = satir.split("#");
                    if (veri.length == 3) {
                        String ad = veri[0];
                        int saatSayisi = Integer.parseInt(veri[1]);
                        LocalDate tarih = LocalDate.parse(veri[2], formatter);
                        gezegenler.add(new Gezegen(ad, saatSayisi, new Zaman(tarih, 0)));
                    } else {
                        System.out.println("[HATA] Gezegenler.txt format hatası: " + satir);
                    }
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("[HATA] Gezegenler.txt veri hatası: " + satir);
                }
            }
        } catch (IOException e) {
            System.out.println("[HATA] Gezegenler.txt okunamadı: " + e.getMessage());
        }
        return gezegenler;
    }
}
