package id.ac.univpancasila.perpustakaan;

import id.ac.univpancasila.perpustakaan.exception.AnggotaTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.BukuTidakDitemukanException;
import id.ac.univpancasila.perpustakaan.exception.PerpustakaanException;
import id.ac.univpancasila.perpustakaan.model.Anggota;
import id.ac.univpancasila.perpustakaan.model.Buku;
import id.ac.univpancasila.perpustakaan.service.LibraryService;
import id.ac.univpancasila.perpustakaan.service.PerpustakaanService;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        PerpustakaanService katalog = new PerpustakaanService();
        LibraryService peminjaman = new LibraryService();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean berjalan = true;
            while (berjalan) {
                tampilkanMenu();
                String pilihan = baca(scanner, "Pilih menu: ");

                if (pilihan == null || pilihan.equals("0")) {
                    berjalan = false;
                    continue;
                }

                try {
                    switch (pilihan) {
                        case "1" -> tambahBuku(scanner, katalog, peminjaman);
                        case "2" -> tambahAnggota(scanner, katalog, peminjaman);
                        case "3" -> tampilkanBuku(katalog);
                        case "4" -> tampilkanAnggota(katalog);
                        case "5" -> pinjamBuku(scanner, katalog, peminjaman);
                        default -> System.out.println("Pilihan tidak tersedia.");
                    }
                } catch (PerpustakaanException e) {
                    System.out.println("Peminjaman gagal: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Input tidak valid: " + e.getMessage());
                }
            }
        }

        System.out.println("Program selesai.");
    }

    private static void tampilkanMenu() {
        System.out.println("""

                === SISTEM PERPUSTAKAAN ===
                1. Tambah buku
                2. Tambah anggota
                3. Daftar buku
                4. Daftar anggota
                5. Pinjam buku
                0. Keluar""");
    }

    private static void tambahBuku(
            Scanner scanner,
            PerpustakaanService katalog,
            LibraryService peminjaman
    ) {
        String isbn = bacaWajib(scanner, "ISBN");
        if (isbn == null) {
            return;
        }

        String judul = bacaWajib(scanner, "Judul");
        if (judul == null) {
            return;
        }

        String penulis = bacaWajib(scanner, "Penulis");
        if (penulis == null) {
            return;
        }

        Integer stok = bacaStok(scanner);
        if (stok == null) {
            return;
        }

        Buku buku = new Buku(isbn, judul, penulis, stok);
        katalog.tambahBuku(buku);
        peminjaman.tambahBuku(isbn, judul, stok);
        System.out.println("Buku berhasil ditambahkan.");
    }

    private static void tambahAnggota(
            Scanner scanner,
            PerpustakaanService katalog,
            LibraryService peminjaman
    ) {
        String id = bacaWajib(scanner, "ID anggota");
        if (id == null) {
            return;
        }

        String nama = bacaWajib(scanner, "Nama");
        if (nama == null) {
            return;
        }

        String programStudi = bacaWajib(scanner, "Program studi");
        if (programStudi == null) {
            return;
        }

        Anggota anggota = new Anggota(id, nama, programStudi);
        katalog.tambahAnggota(anggota);
        peminjaman.daftarkanAnggota(id);
        System.out.println("Anggota berhasil didaftarkan.");
    }

    private static void tampilkanBuku(PerpustakaanService katalog) {
        if (katalog.getDaftarBuku().isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }

        katalog.getDaftarBuku().forEach(System.out::println);
    }

    private static void tampilkanAnggota(PerpustakaanService katalog) {
        if (katalog.getDaftarAnggota().isEmpty()) {
            System.out.println("Belum ada anggota.");
            return;
        }

        katalog.getDaftarAnggota().forEach(System.out::println);
    }

    private static void pinjamBuku(
            Scanner scanner,
            PerpustakaanService katalog,
            LibraryService peminjaman
    ) {
        String idAnggota = bacaWajib(scanner, "ID anggota");
        if (idAnggota == null) {
            return;
        }

        String isbn = bacaWajib(scanner, "ISBN buku");
        if (isbn == null) {
            return;
        }

        if (katalog.cariAnggota(idAnggota) == null) {
            throw new AnggotaTidakDitemukanException(idAnggota);
        }

        Buku buku = katalog.cariBuku(isbn);
        if (buku == null) {
            throw new BukuTidakDitemukanException(isbn);
        }

        peminjaman.pinjam(idAnggota, isbn);
        buku.kurangiStok();
        System.out.println("Buku berhasil dipinjam: " + buku.getJudul());
        System.out.println("Sisa stok: " + buku.getStok());
    }

    private static String baca(Scanner scanner, String prompt) {
        System.out.println(prompt);
        System.out.flush();
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }

    private static String bacaWajib(Scanner scanner, String label) {
        while (true) {
            String nilai = baca(scanner, label + ": ");
            if (nilai == null) {
                return null;
            }
            if (!nilai.isBlank()) {
                return nilai;
            }

            System.out.println(label + " tidak boleh kosong. Silakan masukkan kembali.");
        }
    }

    private static Integer bacaStok(Scanner scanner) {
        while (true) {
            String masukan = baca(scanner, "Stok (bilangan bulat >= 0): ");
            if (masukan == null) {
                return null;
            }
            if (masukan.isBlank()) {
                System.out.println("Stok tidak boleh kosong. Silakan masukkan kembali.");
                continue;
            }

            try {
                int stok = Integer.parseInt(masukan);
                if (stok < 0) {
                    System.out.println("Stok tidak boleh negatif. Silakan masukkan kembali.");
                    continue;
                }
                return stok;
            } catch (NumberFormatException e) {
                System.out.println("Stok harus berupa bilangan bulat. Silakan masukkan kembali.");
            }
        }
    }
}
