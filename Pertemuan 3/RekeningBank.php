<?php
declare(strict_types=1);

/**
 * Sesi 3 — PHP tidak punya constructor overloading.
 * Padanannya: default parameter + named constructor (static factory).
 */
class RekeningBank
{
    // TODO 1: ganti angka ajaib berikut menjadi konstanta bernama.
    public const  BUNGA_TAHUNAN = 0.025;
    public const  BIAYA_ADMINISTRASI = 5000.0;
    public const  BATAS_PENARIKAN_SEKALI = 5000000.0;

    // TODO 2: deklarasikan properti statis penghitung jumlah rekening.
    private static int $jumlahRekening = 0;

    private float $saldo;

    /**
     * Default parameter menggantikan constructor overloading.
     * TODO 3: lengkapi validasi nomor kosong dan saldo awal negatif.
     * TODO 4: naikkan penghitung jumlah rekening.
     */
    public function __construct(
        private readonly string $nomor,
        private readonly string $pemilik,
        float $saldoAwal = 0.0,
    ) {
        if (trim($nomor) === '') {
            throw new InvalidArgumentException('Nomor rekening tidak boleh kosong.');
        }
        if ($saldoAwal < 0) {
            throw new InvalidArgumentException('Saldo awal tidak boleh negatif.');
        }

        $this->saldo =$saldoAwal;
        self::$jumlahRekening++;
    }

    /**
     * TODO 5: named constructor — rekening pelajar, saldo awal nol.
     *         Gunakan `new static()`, BUKAN `new self()`.
     *         Alasannya ada di modul teori pertemuan 3 (LateBinding.php).
     */
    public static function rekeningPelajar(string $nomor, string$pemilik): static
    {
        return new static($nomor,$pemilik, 0.0);
    }

    public function setor(float $jumlah): void
    {
        // TODO 6
        if ($jumlah <= 0) {
            throw new InvalidArgumentException('Jumlah setoran harus bernilai positif.');
        }
        $this->saldo +=$jumlah;
    }

    public function tarik(float $jumlah): void
    {
        // TODO 7: tolak <= 0, tolak melebihi saldo, tolak melebihi batas sekali tarik.
        if ($jumlah <= 0) {
            throw new InvalidArgumentException('Jumlah penarikan harus bernilai positif.');
        }
        if ($jumlah >$this->saldo) {
            throw new InvalidArgumentException('Saldo tidak mencukupi untuk penarikan.');
        }
        if ($jumlah > self::BATAS_PENARIKAN_SEKALI) {
            throw new InvalidArgumentException('Penarikan melebihi batas maksimal sekali transaksi.');
        }
        $this->saldo -=$jumlah;
    }

    /** TODO 8 */
    public function potongBiayaAdmin(): void
    {
        $this->saldo = max(0.0,$this->saldo - self::BIAYA_ADMINISTRASI);
    }

    /** TODO 9 */
    public static function getJumlahRekening(): int
    {
        return self::$jumlahRekening;
    }

    /** TODO 10 */
    public static function bungaSetahun(float $pokok): float
    {
        return $pokok * self::BUNGA_TAHUNAN;
    }

    public function getSaldo(): float { return $this->saldo; }
    public function getNomor(): string { return $this->nomor; }

    public function __toString(): string
    {
        return sprintf('Rekening[%s] %-14s Rp%s',
            $this->nomor, $this->pemilik, number_format($this->saldo, 2, ',', '.'));
    }
}