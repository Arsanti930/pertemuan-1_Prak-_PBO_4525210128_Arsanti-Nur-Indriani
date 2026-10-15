/**
 * BAHAN LANGKAH 5 — versi yang MEMAKAI polimorfisme.
 */
public class AntiPatternRefaktor {

    // 1. Definisikan interface umum untuk semua bangun datar
    interface BangunDatar {
        double hitungLuas();
    }

    // 2. Setiap record mengimplementasikan interface dan mengenkapsulasi logikanya sendiri
    record Lingkaran(double r) implements BangunDatar {
        @Override
        public double hitungLuas() {
            return Math.PI * r * r;
        }
    }

    record Persegi(double sisi) implements BangunDatar {
        @Override
        public double hitungLuas() {
            return sisi * sisi;
        }
    }

    record Segitiga(double alas, double tinggi) implements BangunDatar {
        @Override
        public double hitungLuas() {
            return 0.5 * alas * tinggi;
        }
    }

    public static void main(String[] args) {
        // Menggunakan tipe polimorfik BangunDatar[]
        BangunDatar[] daftar = {
            new Lingkaran(7),
            new Persegi(5),
            new Segitiga(4, 3)
        };

        double total = 0;
        for (BangunDatar b : daftar) {
            total += b.hitungLuas(); // Pemanggilan polimorfik tanpa if-else/instanceof
        }
        
        System.out.printf("Total luas (cara polimorfik): %.2f%n", total);
    }
}