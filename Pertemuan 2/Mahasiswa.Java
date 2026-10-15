public class Mahasiswa {

    // Konstanta bobot — jangan menulis angka 0.30 dan 0.40 di dalam method.
    public static final double BOBOT_TUGAS = 0.30;
    public static final double BOBOT_UTS   = 0.30;
    public static final double BOBOT_UAS   = 0.40;

    private static final double NILAI_MIN = 0;
    private static final double NILAI_MAX = 100;

    // TODO 1: deklarasikan atribut. Perhatikan mana yang boleh berubah
    //         dan mana yang tidak. Gunakan final untuk yang tidak boleh berubah.
    private final String nim;
    private final String nama;
    private double nilaiTugas;
    private double nilaiUts;
    private double nilaiUas;

    public Mahasiswa(String nim, String nama, double nilaiTugas, double nilaiUts, double nilaiUas) {
        // TODO 2: tolak NIM yang kosong atau null.
        //         Lemparkan IllegalArgumentException dengan pesan yang menyebut
        //         APA yang salah — bukan sekadar "Error".
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh null atau kosong.");
        }
        
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh null atau kosong.");
        }

        // TODO 3: tolak setiap komponen nilai yang di luar rentang 0-100.
        //         Petunjuk: buat satu method privat pembantu agar tidak menulis
        //         pemeriksaan yang sama tiga kali.
        pastikanNilaiSah("Tugas", nilaiTugas);
        pastikanNilaiSah("UTS", nilaiUts);
        pastikanNilaiSah("UAS", nilaiUas);

        this.nim = nim;
        this.nama = nama;
        this.nilaiTugas = nilaiTugas;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
    }

    // TODO 4: buat method privat pembantu untuk memvalidasi satu komponen nilai.
    //         Tanda tangan yang disarankan:
    private static void pastikanNilaiSah(String namaKomponen, double nilai) {
        if (nilai < NILAI_MIN || nilai > NILAI_MAX) {
            throw new IllegalArgumentException(
                "Nilai " + namaKomponen + " harus berada di antara " + NILAI_MIN + " dan " + NILAI_MAX + ". Nilai saat ini: " + nilai
            );
        }
    }

    /**
     * TODO 5: hitung nilai akhir memakai konstanta bobot di atas.
     */
    public double nilaiAkhir() {
        return (nilaiTugas * BOBOT_TUGAS) + (nilaiUts * BOBOT_UTS) + (nilaiUas * BOBOT_UAS);
    }

    /**
     * TODO 6: kembalikan huruf mutu berdasarkan nilai akhir.
     *   >= 80 -> "A"   >= 70 -> "B"   >= 60 -> "C"   >= 50 -> "D"   selain itu "E"
     */
    public String hurufMutu() {
        double akhir = nilaiAkhir();
        if (akhir >= 80) {
            return "A";
        } else if (akhir >= 70) {
            return "B";
        } else if (akhir >= 60) {
            return "C";
        } else if (akhir >= 50) {
            return "D";
        } else {
            return "E";
        }
    }

    // ── Getter ────────────────────────────────────────────────
    // TODO 7: sediakan getter untuk nim, nama, dan nilaiAkhir.
    //         JANGAN membuat setNim(). Baca ulang invariant Anda kalau tergoda.

    public String getNim()  { return nim; }
    public String getNama() { return nama; }
    public double getNilaiAkhir() { return nilaiAkhir(); }

    @Override
    public String toString() {
        return String.format("%-10s %-18s akhir=%6.2f  mutu=%s",
                nim, nama, nilaiAkhir(), hurufMutu());
    }
}