<?php
declare(strict_types=1);

namespace Perpustakaan\Service;

require_once dirname(__DIR__, 2) . '/exceptions.php';

use AnggotaTidakDitemukanException;
use BatasPinjamTerlampauiException;
use BukuSedangDipinjamException;
use BukuTidakDitemukanException;

final class LibraryService
{
    public const int BATAS_PINJAM = 3;

    /** @var array<string,int> */
    private array $stok = [];
    /** @var array<string,string> */
    private array $judul = [];
    /** @var array<string,int> */
    private array $pinjaman = [];

    public function tambahBuku(string $isbn, string $judulBuku, int $eksemplar): void
    {
        $this->stok[$isbn] = ($this->stok[$isbn] ?? 0) + $eksemplar;
        $this->judul[$isbn] = $judulBuku;
    }

    public function daftarkanAnggota(string $idAnggota): void
    {
        $this->pinjaman[$idAnggota] ??= 0;
    }

    public function pinjam(string $idAnggota, string $isbn): bool
    {
        if (!array_key_exists($idAnggota, $this->pinjaman)) {
            throw new AnggotaTidakDitemukanException($idAnggota);
        }
        if (!array_key_exists($isbn, $this->stok)) {
            throw new BukuTidakDitemukanException($isbn);
        }
        if ($this->stok[$isbn] <= 0) {
            throw new BukuSedangDipinjamException($isbn, $this->judul[$isbn]);
        }
        if ($this->pinjaman[$idAnggota] >= self::BATAS_PINJAM) {
            throw new BatasPinjamTerlampauiException($idAnggota, self::BATAS_PINJAM);
        }

        $this->stok[$isbn]--;
        $this->pinjaman[$idAnggota]++;
        return true;
    }

    public function sisaEksemplar(string $isbn): int
    {
        return $this->stok[$isbn] ?? 0;
    }

    public function judulDari(string $isbn): ?string
    {
        return $this->judul[$isbn] ?? null;
    }
}
