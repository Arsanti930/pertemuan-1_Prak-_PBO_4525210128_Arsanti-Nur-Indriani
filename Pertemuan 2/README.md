# Praktikum Sesi 2 — Kelas, Objek, dan Enkapsulasi

**Sub-CPMK-P2 — Mahasiswa mampu mengimplementasikan kelas berenkapsulasi dan menguji perilakunya secara manual. (P3)**

**Keterkaitan teori:** Pertemuan 2 — Kelas, Objek, dan Enkapsulasi  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 4% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-02-kelas-objek-dan-enkapsulasi.docx`](Modul-Praktikum-02-kelas-objek-dan-enkapsulasi.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Menurunkan atribut dan perilaku dari deskripsi masalah berbahasa manusia.
2. Mengimplementasikan kelas dengan atribut privat dan validasi di dalam objek.
3. Menulis method yang menamai peristiwa bisnis, bukan sekadar setter.
4. Membuktikan bahwa objek menolak operasi yang melanggar aturannya sendiri.

## B. Alat dan bahan

- Lingkungan hasil sesi 1.
- Repositori pbo-<NIM> milik Anda.

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/java/Mahasiswa.java` | Kerangka kelas dengan TODO pada validasi dan perhitungan nilai. |
| `starter/java/Main.java` | Program uji yang sudah lengkap — jangan diubah pada Langkah 1–4. |
| `starter/php/Mahasiswa.php` | Kerangka PHP dengan TODO yang sama. |
| `starter/php/main.php` | Program uji PHP. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Membaca deskripsi masalah dan menuliskan invariant-nya

- Baca deskripsi berikut dengan saksama:
- "Sistem akademik mencatat mahasiswa dengan NIM, nama, dan tiga komponen nilai: tugas, UTS, dan UAS. NIM tidak pernah berubah setelah mahasiswa terdaftar. Setiap komponen nilai berada dalam rentang 0 sampai 100. Nilai akhir dihitung 30% tugas, 30% UTS, 40% UAS."
- SEBELUM menulis satu baris kode pun, buat berkas sesi-02/analisis.md dan tuliskan: (a) nama kelas, (b) daftar atribut, (c) daftar perilaku, (d) daftar invariant. Ini dinilai.

> **Checkpoint —** analisis.md berisi minimal tiga invariant yang Anda temukan dari kalimat "tidak pernah berubah" dan "berada dalam rentang".

### Langkah 2 — Melengkapi kelas Mahasiswa versi Java

- Salin starter java/ ke sesi-02/java/. Buka Mahasiswa.java.
- Lengkapi setiap TODO. Perhatikan: constructor harus MENOLAK data yang melanggar invariant, bukan menerimanya lalu memperbaikinya diam-diam.
- Jangan menambahkan setter untuk nim. Jika Anda tergoda menambahkannya, baca ulang invariant Anda sendiri.

**Terminal**

```bash
cd sesi-02/java
javac -d out *.java
java -cp out Main
```

> **Checkpoint —** Main mencetak rekap nilai tiga mahasiswa, dan menolak dua percobaan pembuatan objek yang melanggar aturan.

### Langkah 3 — Melengkapi kelas Mahasiswa versi PHP

- Salin starter php/ ke sesi-02/php/. Lengkapi TODO yang sama.
- Gunakan constructor property promotion dan readonly untuk atribut yang tidak boleh berubah. Bandingkan dengan kata kunci final pada versi Java.
- Jangan lupa declare(strict_types=1) di baris pertama — tanpa itu, PHP akan diam-diam mengubah string "abc" menjadi angka.

**Terminal**

```bash
cd ../php
php main.php
```

> **Checkpoint —** Keluaran PHP identik dengan keluaran Java, termasuk pesan penolakannya.

### Langkah 4 — Membuktikan enkapsulasi bekerja

- Buat berkas sesi-02/bukti.md. Di dalamnya, tuliskan tiga percobaan yang Anda lakukan untuk MERUSAK objek dari luar, beserta hasilnya:
- 1. Mencoba mengakses atribut privat secara langsung dari Main.
- 2. Mencoba membuat objek dengan nilai 150.
- 3. Mencoba mengubah NIM setelah objek dibuat.
- Untuk setiap percobaan, catat: apakah gagal saat KOMPILASI atau saat PROGRAM BERJALAN? Perbedaan ini penting dan akan ditanyakan saat demo.

> **Checkpoint —** bukti.md berisi tiga percobaan lengkap dengan pesan kesalahan yang sesungguhnya, disalin apa adanya.

### Langkah 5 — Membuat kelas Buku secara mandiri

- Sekarang tanpa starter. Rancang dan implementasikan kelas Buku di Java DAN PHP dari deskripsi berikut:
- "Perpustakaan mencatat buku dengan ISBN, judul, penulis, dan jumlah eksemplar. ISBN tidak pernah berubah setelah buku terdaftar. Jumlah eksemplar tidak boleh negatif. Buku dapat dipinjam bila masih ada eksemplar tersedia, dan dapat dikembalikan."
- Kelas ini akan Anda pakai lagi pada sesi 7 sampai 14. Rancang dengan serius.
- Tuliskan lebih dulu daftar invariant-nya di analisis.md, baru tulis kodenya.

> **Checkpoint —** Buku.java dan Buku.php berjalan; method pinjam() menolak ketika eksemplar habis.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Tambahkan method predikat lulus() pada Mahasiswa yang mengembalikan true bila nilai akhir minimal 60. Jelaskan mengapa method ini lebih baik ditaruh di dalam kelas Mahasiswa daripada di Main.
2. Ubah salah satu atribut privat menjadi public, lalu jalankan ulang bukti.md percobaan nomor 1. Catat apa yang berubah, lalu KEMBALIKAN menjadi private.

## F. Tugas rumah

1. Untuk setiap atribut pada kelas Mahasiswa dan Buku, tuliskan satu kalimat alasan mengapa Anda memilih private, protected, atau public. Simpan di sesi-02/access-modifier.md.
2. Jawab dalam 150 kata: mengapa method bernama tarik() lebih baik daripada setSaldo()? Gunakan contoh dari kelas Buku Anda sendiri.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-02/analisis.md` | Analisis atribut, perilaku, dan invariant untuk Mahasiswa dan Buku. |
| `sesi-02/java/` | Mahasiswa.java, Buku.java, Main.java yang berjalan. |
| `sesi-02/php/` | Mahasiswa.php, Buku.php, main.php yang berjalan. |
| `sesi-02/bukti.md` | Tiga percobaan merusak objek beserta hasilnya. |
| `sesi-02/access-modifier.md` | Alasan pemilihan access modifier per atribut. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Tunjukkan satu invariant pada kelas Buku Anda, lalu tunjuk baris kode yang menjaganya.
2. Mengapa NIM Anda buat final (Java) atau readonly (PHP)? Apa yang bisa terjadi kalau tidak?
3. Pada bukti.md percobaan nomor 1, apakah kegagalannya terjadi saat kompilasi atau saat program berjalan? Mengapa itu lebih baik?
4. Saya ingin menambah aturan: jumlah eksemplar maksimum 50. Di berkas mana dan di baris mana Anda akan menambahkannya? Lakukan sekarang.
5. Kalau saya menghapus method pinjam() dan menggantinya dengan setJumlahEksemplar() publik, apa yang hilang?
6. Apa beda constructor property promotion di PHP dengan constructor biasa? Tunjukkan di kode Anda.
7. Mengapa declare(strict_types=1) penting? Hapus baris itu, jalankan ulang, dan tunjukkan perbedaannya.

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| Validasi ditulis di Main, bukan di dalam kelas | Belum terbiasa menganggap objek bertanggung jawab atas dirinya sendiri. | Pindahkan seluruh pemeriksaan ke constructor dan method kelas. Main hanya boleh memanggil dan menampilkan. |
| Setter disediakan untuk semua atribut, termasuk yang final | Kebiasaan dari generator IDE yang membuat getter/setter otomatis. | Hapus setter untuk atribut yang punya invariant. Ganti dengan method bernama peristiwa bisnis. |
| TypeError di PHP setelah menambahkan strict_types | Ada argumen yang tipenya tidak cocok dengan deklarasi. | Perbaiki tipe argumen di pemanggil. Jangan menghapus strict_types — itu justru menyembunyikan bug. |
| Kompilasi Java gagal: cannot assign a value to final variable | Ada kode yang mencoba mengubah atribut final di luar constructor. | Hapus penugasan tersebut. Jika atribut memang harus berubah, ia tidak seharusnya final. |
| Keluaran Java dan PHP berbeda formatnya | Format angka desimal dan pemisah ribuan berbeda antar bahasa. | Samakan dengan String.format (Java) dan sprintf/number_format (PHP). Ini bagian dari penilaian. |

## Lembar verifikasi demo

Diisi oleh dosen atau asisten pada saat demo. Tugas tanpa lembar terverifikasi tidak dinilai.

|  |  |
|---|---|
| Nama / NIM |  |
| Tanggal demo |  |
| Nilai sesi ini |  |
| Catatan penguji |  |
| Paraf penguji |  |

---

_Modul ini disusun 10 September 2026. Versi teknologi yang dirujuk (Java 25 LTS, PHP 8.4/8.5, Laravel 13, Spring Boot 4.1) diverifikasi pada tanggal tersebut dan perlu diperiksa ulang sebelum semester berjalan._