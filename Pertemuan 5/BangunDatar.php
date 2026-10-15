<?php
declare(strict_types=1);

abstract class BangunDatar
{
    public function __construct(private readonly string $nama) {}

    abstract public function luas(): float;
    abstract public function keliling(): float;

    public function getNama(): string { return $this->nama; }

    public function __toString(): string
    {
        return sprintf('%-12s luas=%10.2f  keliling=%10.2f',
            $this->nama, $this->luas(), $this->keliling());
    }
}

class Lingkaran extends BangunDatar
{
    public function __construct(private readonly float $jariJari)
    {
        parent::__construct('Lingkaran');
        // TODO 1: tolak jari-jari <= 0.
        if ($this->jariJari <= 0) {
            throw new InvalidArgumentException('Jari-jari harus lebih besar dari 0');
        }
    }

    // TODO 2: lengkapi. Gunakan M_PI, bukan 3.14.
    public function luas(): float    { return M_PI * $this->jariJari ** 2; }
    public function keliling(): float { return 2 * M_PI * $this->jariJari; }

    public function getJariJari(): float { return $this->jariJari; }
}

class Persegi extends BangunDatar
{
    public function __construct(private readonly float $sisi)
    {
        parent::__construct('Persegi');
        // TODO 1: tolak sisi <= 0.
        if ($this->sisi <= 0) {
            throw new InvalidArgumentException('Sisi harus lebih besar dari 0');
        }
    }

    // TODO 2: lengkapi.
    public function luas(): float    { return $this->sisi ** 2; }
    public function keliling(): float { return 4 * $this->sisi; }
}

// TODO Langkah 2: buat kelas Segitiga (tiga sisi, rumus Heron).
//               Tolak konstruksi bila ketiga sisi tidak membentuk segitiga.
class Segitiga extends BangunDatar
{
    public function __construct(
        private readonly float $sisiA,
        private readonly float $sisiB,
        private readonly float$sisiC
    ) {
        parent::__construct('Segitiga');
        
        if ($this->sisiA <= 0 || $this->sisiB <= 0 || $this->sisiC <= 0) {
            throw new InvalidArgumentException('Sisi-sisi segitiga harus lebih besar dari 0');
        }

        // Validasi Ketidaksamaan Segitiga (Triangle Inequality Theorem)
        if (
            ($this->sisiA + $this->sisiB <= $this->sisiC) ||
            ($this->sisiA + $this->sisiC <= $this->sisiB) ||
            ($this->sisiB + $this->sisiC <= $this->sisiA)
        ) {
            throw new InvalidArgumentException('Panjang sisi tidak membentuk segitiga yang valid');
        }
    }

    public function luas(): float
    {
        // Rumus Heron: s = (a + b + c) / 2
        $s = ($this->sisiA + $this->sisiB + $this->sisiC) / 2;
        return sqrt($s * ($s - $this->sisiA) * ($s - $this->sisiB) * ($s - $this->sisiC));
    }

    public function keliling(): float
    {
        return $this->sisiA + $this->sisiB + $this->sisiC;
    }
}

// TODO Langkah 4: buat kelas Trapesium.
class Trapesium extends BangunDatar
{
    public function __construct(
        private readonly float $sisiAtas,
        private readonly float $sisiBawah,
        private readonly float $sisiKiri,
        private readonly float $sisiKanan,
        private readonly float $tinggi
    ) {
        parent::__construct('Trapesium');

        if ($this->sisiAtas <= 0 || $this->sisiBawah <= 0 || $this->sisiKiri <= 0 || $this->sisiKanan <= 0 || $this->tinggi <= 0) {
            throw new InvalidArgumentException('Semua sisi dan tinggi trapesium harus lebih besar dari 0');
        }
    }

    public function luas(): float
    {
        // Luas = 0.5 * (sisiAtas + sisiBawah) * tinggi
        return 0.5 * ($this->sisiAtas + $this->sisiBawah) * $this->tinggi;
    }

    public function keliling(): float
    {
        // Keliling = penjumlahan seluruh sisi luar
        return $this->sisiAtas + $this->sisiBawah + $this->sisiKiri + $this->sisiKanan;
    }
}