/**
*
* @author Sıla ÇANGA sila.canga1@ogr.sakarya.edu.tr
* @since 26.04.2025
* <p>
* 2A grubu
* </p>
*/



package simulasyon;

public class Main {
    public static void main(String[] args) {
        Simulasyon simulasyon = new Simulasyon();
        simulasyon.baslat();
    }

    // Ben mac kullanıyorum ama mümkün olduğunca windows uyumlu yapmaya çalıştım 
    // Zaten kendi bilgisayarımda problem yaşadığım tek bölüm burası
    
    
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Ekranı temizlerken hata oluştu.");
        }
    }
}
