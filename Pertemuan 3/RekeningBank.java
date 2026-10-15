/**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
public class RekeningBank {

    // TODO 1: ganti tiga angka ajaib berikut menjadi konstanta bernama
    //         (public static final). Setelah itu, tidak boleh ada lagi
    //         angka literal di dalam badan method.
    //   - bunga tahunan          : 0.025
    //   - biaya administrasi     : 5000
    //   - batas penarikan sekali : 5000000
    public static final double BUNGA_TAHUNAN = 0.025;
    public static final double BIAYA_ADMINISTRASI = 5000.0;
    public static final double BATAS_PENARIKAN_SEKALI = 5000000.0;

    // TODO 2: deklarasikan field statis penghitung jumlah rekening.
    //         Perhatikan: static, privat, dan bernilai awal 0.
    private static int jumlahRekening = 0;

    private final String nomor;
    private final String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     * TODO 3: DELEGASIKAN ke constructor lengkap dengan this(...).
     *         Jangan menyalin validasi ke sini.
     */
    public RekeningBank(String nomor, String pemilik) {
        // TODO 3 — ganti baris di bawah dengan delegasi
        this(nomor, pemilik, 0.0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        // TODO 4: tolak nomor kosong dan saldo awal negatif.
        if (nomor == null || nomor.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor rekening tidak boleh kosong.");
        }
        if (saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif.");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        // TODO 5: naikkan penghitung jumlah rekening DI SINI SAJA.
        //         Pikirkan mengapa bukan di kedua constructor.
        // (Alasan: Karena constructor ringkas mendelegasikan pembuatannya ke constructor lengkap ini, 
        //  sehingga jika ditaruh di keduanya, pembuatan objek lewat constructor ringkas akan menaikkan counter dua kali.)
        jumlahRekening++;
    }

    public void setor(double jumlah) {
        // TODO 6: tolak jumlah <= 0, lalu tambahkan ke saldo.
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah setoran harus bernilai positif.");
        }
        this.saldo += jumlah;
    }

    public void tarik(double jumlah) {
        // TODO 7: tolak jumlah <= 0, tolak jika melebihi saldo,
        //         dan tolak jika melebihi batas penarikan sekali transaksi.
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penarikan harus bernilai positif.");
        }
        if (jumlah > this.saldo) {
            throw new IllegalArgumentException("Saldo tidak mencukupi untuk penarikan.");
        }
        if (jumlah > BATAS_PENARIKAN_SEKALI) {
            throw new IllegalArgumentException("Penarikan melebihi batas maksimal sekali transaksi.");
        }
        this.saldo -= jumlah;
    }

    /** TODO 8: kurangi saldo sebesar biaya administrasi, tetapi jangan sampai negatif. */
    public void potongBiayaAdmin() {
        this.saldo -= BIAYA_ADMINISTRASI;
        if (this.saldo < 0) {
            this.saldo = 0;
        }
    }

    /** TODO 9: method statis — kembalikan jumlah rekening yang pernah dibuat. */
    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    /**
     * TODO 10: method statis utilitas — hitung bunga setahun dari pokok.
     *          Perhatikan: method ini tidak membaca keadaan objek mana pun.
     *          Itulah alasan ia pantas menjadi static.
     */
    public static double bungaSetahun(double pokok) {
        return pokok * BUNGA_TAHUNAN;
    }

    public double getSaldo()  { return saldo; }
    public String getNomor()  { return nomor; }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}
    
