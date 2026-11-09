<?php
declare(strict_types=1);

abstract class PerpustakaanException extends RuntimeException
{
}

final class BukuTidakDitemukanException extends PerpustakaanException
{
    public function __construct(private readonly string $isbn)
    {
        parent::__construct("Buku dengan ISBN {$isbn} tidak ditemukan.");
    }

    public function getIsbn(): string
    {
        return $this->isbn;
    }
}

final class AnggotaTidakDitemukanException extends PerpustakaanException
{
    public function __construct(private readonly string $idAnggota)
    {
        parent::__construct("Anggota dengan ID {$idAnggota} tidak ditemukan.");
    }

    public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }
}

final class BukuSedangDipinjamException extends PerpustakaanException
{
    public function __construct(
        private readonly string $isbn,
        private readonly string $judul
    ) {
        parent::__construct("Buku {$judul} dengan ISBN {$isbn} sedang tidak tersedia.");
    }

    public function getIsbn(): string
    {
        return $this->isbn;
    }

    public function getJudul(): string
    {
        return $this->judul;
    }
}

final class BatasPinjamTerlampauiException extends PerpustakaanException
{
    public function __construct(
        private readonly string $idAnggota,
        private readonly int $batas
    ) {
        parent::__construct(
            "Anggota dengan ID {$idAnggota} telah mencapai batas pinjam {$batas} buku."
        );
    }

    public function getIdAnggota(): string
    {
        return $this->idAnggota;
    }

    public function getBatas(): int
    {
        return $this->batas;
    }
}
