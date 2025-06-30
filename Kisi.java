
/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/



package simulasyon;

public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String bulunduguArac;

    public Kisi(String isim, int yas, int kalanOmur, String bulunduguArac) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.bulunduguArac = bulunduguArac;
    }

    public String getIsim() {
        return isim;
    }

    public int getYas() {
        return yas;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String getBulunduguArac() {
        return bulunduguArac;
    }

    public void setBulunduguArac(String bulunduguArac) {
        this.bulunduguArac = bulunduguArac;
    }

    public void omurAzalt(int saat) {
        kalanOmur = Math.max(0, kalanOmur - saat);
    }

    public boolean hayattaMi() {
        return kalanOmur > 0;
    }

    @Override
    public String toString() {
        return isim + " (" + yas + " yaşında, kalan ömür: " + kalanOmur + " saat, araç: " + bulunduguArac + ")";
    }
}
