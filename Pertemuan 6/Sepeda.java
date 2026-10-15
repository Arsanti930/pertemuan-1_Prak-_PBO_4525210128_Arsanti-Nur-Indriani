public class Sepeda extends Kendaraan implements Movable {

    public Sepeda(String merek, int tahun) {
        super(merek, tahun);
    }

    @Override
    public int jumlahRoda() {
        return 2;
    }

    @Override
    public void bergerak() {
        System.out.printf("Sepeda %s dikayuh di jalanan.%n", merek);
    }

    @Override
    public String ringkasanGerak() {
        return "Bergerak menggunakan tenaga manusia (dikayuh).";
    }

    @Override
    public double kecepatanMaksimum() {
        return 30.0; // Menggunakan double (misal: 30.0 km/jam)
    }
}
