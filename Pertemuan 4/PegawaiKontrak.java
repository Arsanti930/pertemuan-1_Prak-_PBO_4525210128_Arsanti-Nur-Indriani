public class PegawaiKontrak extends PegawaiTetap {

    private final int bulanKontrak;

    public PegawaiKontrak(String nip, String nama, double gajiPokok, int bulanKontrak) {
        super(nip, nama, gajiPokok, bulanKontrak);
        this.bulanKontrak = bulanKontrak;
    }


    public String jenis() { return "KONTRAK"; }

    public int getBulanKontrak() { return bulanKontrak; }
}