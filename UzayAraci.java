
/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/


package simulasyon;

import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    private String isim;
    private String cikisGezegeni;
    private String varisGezegeni;
    private Zaman cikisTarihi;
    private int mesafe;
    private int kalanMesafe;
    private List<Kisi> yolcular;
    private boolean imha;
    private boolean yolda;
    private boolean varis;

    public UzayAraci(String isim, String cikisGezegeni, String varisGezegeni, Zaman cikisTarihi, int mesafe) {
        this.isim = isim;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;    
        this.kalanMesafe = mesafe;
        this.yolcular = new ArrayList<>();
        this.imha = false;
        this.yolda = false;
        this.varis = false;
    }

    // Getter'lar
    public String getIsim() {
        return isim;
    }

    public String getCikisGezegeni() {
        return cikisGezegeni;
    }

    public String getVarisGezegeni() {
        return varisGezegeni;
    }

    public Zaman getCikisTarihi() {
        return cikisTarihi;
    }

    public int getMesafe() {
        return mesafe;
    }

    public int getKalanMesafe() {
        return kalanMesafe;
    }

    public List<Kisi> getYolcular() {
        return yolcular;
    }

    public boolean isImha() {
        return imha;
    }

    public boolean hedefeUlastiMi() {
        return kalanMesafe == 0;
    }

    // Yolcu ekler
    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    // Bir saat ilerletir
    public void birSaatIlerle() {
        if (!imha && kalanMesafe > 0) {
            kalanMesafe--;
            yolda = true;
            if (kalanMesafe == 0) {
                varis = true;
            }
        }
    }

    // Eğer araçtaki herkes öldüyse imha olur
    public void imhaKontrolEt() {
        boolean herkesOlmus = yolcular.stream().noneMatch(Kisi::hayattaMi);
        if (herkesOlmus) {
            imha = true;
        }
    }

    // Şu anki durumu döndürür
    public String getDurum() {
        if (imha) return "IMHA";
        if (varis) return "Vardı";
        if (yolda) return "Yolda";
        return "Bekliyor";
    }
}
