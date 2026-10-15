public class PegawaiTetap extends PegawaiKontrak {

    /** Tunjangan masa kerja: 2% gaji pokok per tahun, maksimum 40%. */
    protected static final double TUNJANGAN_PER_TAHUN = 0.02;
    protected static final double TUNJANGAN_MAKSIMUM  = 0.40;
    private static final String Super = null;

    private final int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
        // Baris berikut WAJIB dan harus menjadi pernyataan pertama.
        super(nip, nama, gajiPokok);

        this.masaKerjaTahun = masaKerjaTahun;
    }

    /**
     * TODO 2: hitung gaji = gaji dasar induk + tunjangan masa kerja.
     *
     * PENTING: panggil super.hitungGaji() untuk memperoleh gaji dasar.
     *          JANGAN menyalin rumus induk ke sini — itu yang dinilai.
     */
    public double HitungGaji() {
        double gajiDasar = Super.hitungGaji();
        
        // Hitung persentase tunjangan (maksimum 40% atau 0.40)
        double persentaseTunjangan = Math.min(masaKerjaTahun * TUNJANGAN_PER_TAHUN, TUNJANGAN_MAKSIMUM);
        
        // Gaji total = gaji dasar + (gaji dasar * persentase tunjangan)
        return gajiDasar + (gajiDasar * persentaseTunjangan);
    }

    @Override
    public String jenis() { return "TETAP"; }

    protected int getMasaKerjaTahun() { return masaKerjaTahun; }
}