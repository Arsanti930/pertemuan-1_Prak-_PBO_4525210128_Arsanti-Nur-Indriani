<?php
declare(strict_types=1);

/**
 * Sesi 1 — objek pertama Anda (PHP).
 *
 * Bandingkan baris demi baris dengan java/HaloObjek.java.
 * Konsepnya sama persis; hanya sintaksnya yang berbeda.
 *
 * Jalankan:
 *    php halo_objek.php
 */
class HaloObjek
{
    // TODO 1: Tambahkan kata kunci `private` pada kedua properti.
    private string $nama;
    private string $nim;

    public function __construct(string $nama, string$nim)
    {
        // TODO 2: Gunakan `$this` dan operator `->` untuk menugaskan parameter ke properti objek.
        $this->nama =$nama;
        $this->nim =$nim;
    }

    /**
     * TODO 3: Kembalikan satu baris string berisi nama dan NIM,
     * menggunakan properti objek.
     */
    public function sapa(): string
    {
        return "Halo, saya {$this->nama} ({$this->nim}).";
    }
}

// Ganti dua nilai di bawah dengan nama dan NIM Anda sendiri.
$saya = new HaloObjek('Arsanti Nur Indriani', '4525210128');
echo $saya->sapa(), PHP_EOL;

$temanSekelas = new HaloObjek('Budi Santoso', '2024002');
echo $temanSekelas->sapa(), PHP_EOL;

echo PHP_EOL;
echo 'Pertanyaan untuk direnungkan:', PHP_EOL;
echo '  Apa padanan kata kunci "this" Java di baris-baris di atas?', PHP_EOL;
echo '  Mengapa PHP memakai -> sedangkan Java memakai titik?', PHP_EOL;
echo '  Mengapa kedua objek di atas bisa menyapa dengan nama berbeda,', PHP_EOL;
echo '  padahal method sapa() hanya ditulis satu kali?', PHP_EOL;