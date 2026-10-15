public class Trapesium extends BangunDatar {
    

    private final double sisiAtas, sisiBawah, sisiKiri, sisiKanan, tinggi;

    public Trapesium(double sisiAtas, double sisiBawah, double sisiKiri, double sisiKanan, double tinggi) {
        super("Trapesium");
        if (sisiAtas <= 0 || sisiBawah <= 0 || sisiKiri <= 0 || sisiKanan <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Semua sisi dan tinggi trapesium harus lebih besar dari 0");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
        this.tinggi = tinggi;
    }

  
    public double luas() {
        // Luas = 0.5 * (sisiAtas + sisiBawah) * tinggi
        return 0.5 * (sisiAtas + sisiBawah) * tinggi;
    }

    public double keliling() {
        // Keliling = penjumlahan seluruh sisi luar
        return sisiAtas + sisiBawah + sisiKiri + sisiKanan;
    }
}
