 class RekeningBank {
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;

    // Attribute static untuk menghitung total rekening yang aktif dibuat
    private static int jumlahRekening = 0;

    // 1. Constructor Utama (3 Parameter)
    public RekeningBank(String nomorRekening, String namaPemilik, double saldo) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
        
        // Counter HANYA bertambah di sini
        jumlahRekening++;
    }

    // 2. Constructor Ringkas (2 Parameter)
    // Menggunakan 'this(...)' untuk memanggil constructor utama
    public RekeningBank(String nomorRekening, String namaPemilik) {
        this(nomorRekening, namaPemilik, 0.0); 
        // Jangan panggil jumlahRekening++ di sini agar tidak dihitung 2x!
    }

    // Getter untuk static variable
    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    public double getSaldo() {
        return saldo;
    }

    // Operasi Setor
    public void setor(double jumlah) {
        if (jumlah > 0) {
            this.saldo += jumlah;
        }
    }

    // Operasi Tarik dengan Exception handling
    public void tarik(double jumlah) {
        if (jumlah > this.saldo) {
            throw new RuntimeException("Saldo tidak mencukupi!");
        }
        this.saldo -= jumlah;
    }

    // Operasi Potong Admin
    public void potongBiayaAdmin() {
        double biayaAdmin = 10_000; // Contoh nominal biaya admin
        if (this.saldo >= biayaAdmin) {
            this.saldo -= biayaAdmin;
        } else {
            // Sesuai requirement: saldo tidak boleh negatif
            this.saldo = 0;
        }
    }

    // Method static perhitungan bunga
    public static double bungaSetahun(double saldo) {
        double persentaseBunga = 0.05; // Contoh bunga 5% per tahun
        return saldo * persentaseBunga;
    }

    @Override
    public String toString() {
        return String.format("Rekening[%s - %s | Saldo: Rp%,.2f]", nomorRekening, namaPemilik, saldo);
    }
}