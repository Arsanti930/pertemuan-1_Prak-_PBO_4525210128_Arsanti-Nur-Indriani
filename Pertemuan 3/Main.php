<?php
declare(strict_types=1);

class RekeningBank
{
    private string $nomorRekening;
    private string $namaPemilik;
    private float $saldo;

    // Menghitung total rekening yang dibuat
    private static int $jumlahRekening = 0;

    // Constructor Utama
    public function __construct(string $nomorRekening, string $namaPemilik, float $saldo = 0.0)
    {
        $this->nomorRekening = $nomorRekening;
        $this->namaPemilik = $namaPemilik;
        $this->saldo = $saldo;

        // Counter bertambah setiap kali instance baru dibuat
        self::$jumlahRekening++;
    }

    // Named Constructor untuk Rekening Pelajar
    public static function rekeningPelajar(string $nomorRekening, string $namaPemilik): self
    {
        // Memanggil constructor utama (saldo awal 0.0)
        return new self($nomorRekening, $namaPemilik, 0.0);
    }

    // Static Getter untuk jumlah rekening
    public static function getJumlahRekening(): int
    {
        return self::$jumlahRekening;
    }

    public function getSaldo(): float
    {
        return $this->saldo;
    }

    // Method Setor Saldo
    public function setor(float $jumlah): void
    {
        if ($jumlah > 0) {
            $this->saldo += $jumlah;
        }
    }

    // Method Tarik Saldo dengan Exception Handling
    public function tarik(float $jumlah): void
    {
        if ($jumlah > $this->saldo) {
            throw new RuntimeException("Saldo tidak mencukupi untuk penarikan Rp" . number_format($jumlah, 2, ',', '.'));
        }
        $this->saldo -= $jumlah;
    }

    // Method Potong Biaya Admin
    public function potongBiayaAdmin(): void
    {
        $biayaAdmin = 10_000.0;
        if ($this->saldo >= $biayaAdmin) {
            $this->saldo -= $biayaAdmin;
        } else {
            // Sesuai requirement: saldo tidak boleh negatif
            $this->saldo = 0.0;
        }
    }

    // Static Method untuk Perhitungan Bunga
    public static function bungaSetahun(float $saldo): float
    {
        $sukuBunga = 0.05; // Bunga 5% per tahun
        return $saldo * $sukuBunga;
    }

    // Magic Method __toString
    public function __toString(): string
    {
        return sprintf(
            "Rekening[%s - %s | Saldo: Rp%s]",
            $this->nomorRekening,
            $this->namaPemilik,
            number_format($this->saldo, 2, ',', '.')
        );
    }
}