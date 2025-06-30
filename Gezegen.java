/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/


package simulasyon;

public class Gezegen {
    private String ad;
    private int gunSaat;
    private Zaman zaman;
    private int nufus;

    public Gezegen(String ad, int gunSaat, Zaman zaman) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.zaman = zaman;
        this.nufus = 0;
    }

    public String getAd() {
        return ad;
    }

    public int getGunSaat() {
        return gunSaat;
    }

    public Zaman getZaman() {
        return zaman;
    }

    public int getNufus() {
        return nufus;
    }

    public void saatIlerle() {
        zaman.birSaatIlerle(gunSaat);
    }

    public void nufusArttir() {
        nufus++;
    }

    public void nufusAzalt() {
        if (nufus > 0) {
            nufus--;
        }
    }

    @Override
    public String toString() {
        return String.format("--- %s ---\nTarih: %s\nNüfus: %d", ad, zaman.getFormattedDate(), nufus);
    }
}
