// Class induk (TIDAK memakai keyword public)
class PegawaiTetap {
    private String nip;
    private String nama;
    private double gajiPokok;
    private int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
        this.nip = nip;
        this.nama = nama;
        this.gajiPokok = gajiPokok;
        this.masaKerjaTahun = masaKerjaTahun;
    }

    public double hitungGaji() {
        return this.gajiPokok;
    }

    public String jenis() {
        return "PEGAWAI TETAP";
    }

    public String getNip() { return nip; }
    public String getNama() { return nama; }
    public double getGajiPokok() { return gajiPokok; }
    public int getMasaKerjaTahun() { return masaKerjaTahun; }
}

// Class turunan Utama (Harus match dengan nama file: Dosen.java)
public class dosen extends PegawaiTetap {

    private final double tunjanganFungsional;

    public dosen(String nip, String nama, double gajiPokok, int masaKerjaTahun, double tunjanganFungsional) {
        super(nip, nama, gajiPokok, masaKerjaTahun);
        this.tunjanganFungsional = tunjanganFungsional;
    }

    @Override
    public double hitungGaji() {
        return super.hitungGaji() + this.tunjanganFungsional;
    }

    @Override
    public String jenis() {
        return "DOSEN";
    }

    public double getTunjanganFungsional() {
        return tunjanganFungsional;
    }

    public static void main(String[] args) {
        dosen dosen = new dosen("19920115", "Budi, M.Kom", 4500000, 4, 1500000);
        
        System.out.println("Nama         : " + dosen.getNama());
        System.out.println("Jenis        : " + dosen.jenis());
        System.out.println("Gaji Total   : Rp " + dosen.hitungGaji());
    }
}