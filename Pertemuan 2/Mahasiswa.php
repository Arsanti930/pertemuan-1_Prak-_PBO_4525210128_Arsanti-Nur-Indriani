<?php
declare(strict_types=1);

/**
 * Sesi 2 — enkapsulasi yang menjaga invariant (PHP).
 * Bandingkan baris demi baris dengan java/Mahasiswa.java.
 */
class Mahasiswa
{
    public const  BOBOT_TUGAS = 0.30;
    public const  BOBOT_UTS   = 0.30;
    public const  BOBOT_UAS   = 0.40;

    private const NILAI_MIN = 0;
    private const NILAI_MAX = 100;

    /**
     * Constructor property promotion (PHP 8):
     * readonly adalah padanan `final` pada atribut Java.
     *
     * TODO 1: lengkapi daftar parameter — tentukan mana yang readonly.
     */
    public function __construct(
        private readonly string $nim,
        private readonly string $nama,
        private float $nilaiTugas,
        private float $nilaiUts,
        private float $nilaiUas,
    ) {
        // TODO 2: tolak NIM & Nama yang kosong (setelah di-trim).
        if (trim($this->nim) === '') {
            throw new InvalidArgumentException("NIM tidak boleh kosong atau hanya berisi spasi.");
        }

        if (trim($this->nama) === '') {
            throw new InvalidArgumentException("Nama tidak boleh kosong atau hanya berisi spasi.");
        }

        // TODO 3: tolak setiap komponen nilai di luar rentang 0-100
        self::pastikanNilaiSah('Tugas', $this->nilaiTugas);
        self::pastikanNilaiSah('UTS', $this->nilaiUts);
        self::pastikanNilaiSah('UAS', $this->nilaiUas);
    }

    /**
     * TODO 4: lengkapi validasi satu komponen nilai.
     */
    private static function pastikanNilaiSah(string $namaKomponen, float$nilai): void
    {
        if ($nilai < self::NILAI_MIN || $nilai > self::NILAI_MAX) {
            throw new InvalidArgumentException(
                "Nilai {$namaKomponen} harus berada di antara " . self::NILAI_MIN . " dan " . self::NILAI_MAX . ". Nilai saat ini: {$nilai}"
            );
        }
    }

    /** TODO 5: hitung nilai akhir memakai konstanta bobot. */
    public function nilaiAkhir(): float
    {
        return ($this->nilaiTugas * self::BOBOT_TUGAS) 
             + ($this->nilaiUts * self::BOBOT_UTS) 
             + ($this->nilaiUas * self::BOBOT_UAS);
    }

    /** TODO 6: kembalikan huruf mutu. Petunjuk: match (true) { ... } */
    public function hurufMutu(): string
    {
        $akhir =$this->nilaiAkhir();

        return match (true) {
            $akhir >= 80 => 'A',$akhir >= 70 => 'B',
            $akhir >= 60 => 'C',$akhir >= 50 => 'D',
            default      => 'E',
        };
    }

    // TODO 7: sediakan getter seperlunya. JANGAN membuat setNim().
    public function getNim(): string  { return $this->nim; }
    public function getNama(): string { return $this->nama; }
    public function getNilaiAkhir(): float { return $this->nilaiAkhir(); }

    public function __toString(): string
    {
        return sprintf('%-10s %-18s akhir=%6.2f  mutu=%s',
            $this->nim,$this->nama, $this->nilaiAkhir(),$this->hurufMutu());
    }
}