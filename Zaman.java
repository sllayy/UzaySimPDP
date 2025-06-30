/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/


package simulasyon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Zaman {
    private LocalDate tarih;
    private int saat;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Zaman(LocalDate tarih, int saat) {
        this.tarih = tarih;
        this.saat = saat;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public int getSaat() {
        return saat;
    }

    public void birSaatIlerle(int gunSaatSayisi) {
        saat++;
        if (saat >= gunSaatSayisi) {
            tarih = tarih.plusDays(1);
            saat = 0;
        }
    }

    public String getFormattedDate() {
        return tarih.format(formatter);
    }

    // Sadece tarihi kontrol eder (saat yok)
    public boolean esitMi(Zaman diger) {
        return this.tarih.equals(diger.tarih);
    }

    // Eğer bu zaman, diğer zamana eşit veya daha sonraysa true döner
    public boolean tarihEsitVeyaGectiMi(Zaman diger) {
        return this.tarih.isEqual(diger.tarih) || this.tarih.isAfter(diger.tarih);
    }

    // İki zaman arasındaki saat farkını döner
    public int tarihFarkiSaatCinsinden(Zaman hedef, int gunSaatSayisi) {
        int gunFarki = (int) java.time.temporal.ChronoUnit.DAYS.between(this.tarih, hedef.tarih);
        return gunFarki * gunSaatSayisi + (hedef.saat - this.saat);
    }

    @Override
    public String toString() {
        return getFormattedDate() + " - " + String.format("%02d:00", saat);
    }


    public Zaman arttirSaat(int saatEklenecek, int gunSaatSayisi) {
        Zaman yeni = new Zaman(this.tarih, this.saat);
        for (int i = 0; i < saatEklenecek; i++) {
            yeni.birSaatIlerle(gunSaatSayisi);
        }
        return yeni;
    }
}
