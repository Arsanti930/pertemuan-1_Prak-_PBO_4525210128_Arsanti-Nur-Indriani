# Praktikum Sesi 3 — Constructor, Anggota Statis, dan Konstanta

**Sub-CPMK-P2 — Mahasiswa mampu mengimplementasikan kelas berenkapsulasi dan menguji perilakunya secara manual. (P3)**

**Keterkaitan teori:** Pertemuan 3 — Constructor, Anggota Statis, dan Konstanta  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 4% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-03-constructor-anggota-statis-dan-konstanta.docx`](Modul-Praktikum-03-constructor-anggota-statis-dan-konstanta.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Menggunakan constructor overloading dengan delegasi agar validasi tidak terduplikasi.
2. Membuat named constructor di PHP sebagai padanan overloading.
3. Membedakan anggota statis dari anggota instance melalui percobaan langsung.
4. Mengganti angka ajaib dengan konstanta bernama.

## B. Alat dan bahan

- Lingkungan hasil sesi 1.
- Kode kelas Buku dari sesi 2 (akan dipakai pada latihan).

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/java/RekeningBank.java` | Kerangka dengan TODO pada delegasi constructor, static counter, dan konstanta. |
| `starter/java/Main.java` | Program uji. |
| `starter/php/RekeningBank.php` | Kerangka PHP dengan TODO named constructor. |
| `starter/php/main.php` | Program uji PHP. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Melengkapi constructor berdelegasi versi Java

- Salin starter ke sesi-03/. Buka RekeningBank.java.
- Ada dua constructor. Yang ringkas (dua argumen) harus MENDELEGASIKAN ke yang lengkap (tiga argumen) menggunakan this(...). Validasi hanya boleh ditulis SATU KALI, yaitu di constructor lengkap.
- Uji dengan sengaja: coba tulis validasi di kedua constructor, lalu ubah salah satu aturannya. Rasakan sendiri mengapa duplikasi berbahaya. Setelah itu kembalikan ke bentuk delegasi.

**Terminal**

```bash
cd sesi-03/java
javac -d out *.java
java -cp out Main
```

> **Checkpoint —** Kata kunci this( ) muncul tepat satu kali di berkas, dan blok validasi juga hanya ada di satu tempat.

### Langkah 2 — Membuat penghitung objek dengan anggota statis

- Lengkapi TODO untuk field static jumlahRekening dan method static getJumlahRekening().
- Naikkan penghitungnya di dalam constructor lengkap saja — bukan di kedua constructor. Pikirkan mengapa.

> **Checkpoint —** Membuat tiga rekening menghasilkan getJumlahRekening() bernilai 3, bukan 4 atau 6.

### Langkah 3 — Percobaan: membuktikan static milik kelas, bukan milik objek

- Buat berkas sesi-03/percobaan-static.md. Lakukan dan catat hasil dari:
- 1. Cetak jumlahRekening lewat nama kelas: RekeningBank.getJumlahRekening().
- 2. Coba akses this di dalam method static. Catat pesan kompilatornya.
- 3. Ubah salah satu atribut instance (mis. saldo) menjadi static. Buat dua rekening dengan saldo berbeda, lalu cetak keduanya. Catat apa yang terjadi, lalu KEMBALIKAN menjadi non-static.

> **Checkpoint —** Percobaan nomor 3 menunjukkan kedua rekening punya saldo yang sama — bukti bahwa static dibagi bersama.

### Langkah 4 — Named constructor di PHP

- PHP tidak mendukung constructor overloading. Lengkapi TODO untuk membuat named constructor RekeningBank::rekeningPelajar($nomor, $pemilik) yang membuat rekening bersaldo nol.
- Gunakan new static() bukan new self() di dalamnya. Bacalah berkas LateBinding.php pada modul teori pertemuan 3 untuk memahami mengapa.

**Terminal**

```bash
cd ../php
php main.php
```

> **Checkpoint —** main.php membuat rekening lewat dua cara: constructor biasa dan named constructor, keduanya berhasil.

### Langkah 5 — Mengganti angka ajaib dengan konstanta

- Di dalam starter ada tiga angka yang muncul begitu saja tanpa nama: bunga tahunan, biaya administrasi, dan batas penarikan.
- Ganti ketiganya dengan konstanta bernama (static final di Java, const di PHP).
- Tambahkan method potongBiayaAdmin() yang memakai konstanta tersebut dan menjaga saldo tidak menjadi negatif.

> **Checkpoint —** Tidak ada lagi angka literal di dalam badan method, kecuali 0 dan 1.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Tambahkan method static bungaSetahun(double pokok) yang menghitung bunga tanpa membutuhkan objek. Jelaskan mengapa method ini pantas menjadi static, sedangkan tarik() tidak.
2. Terapkan pola yang sama pada kelas Buku dari sesi 2: tambahkan penghitung static jumlah judul terdaftar, dan konstanta MASA_PINJAM_HARI bernilai 14.

## F. Tugas rumah

1. Buat diagram sekuens sederhana (boleh tulis tangan lalu difoto, atau PlantUML) yang menggambarkan apa yang terjadi saat new RekeningBank("111", "Ani") dipanggil — termasuk delegasi ke constructor lengkap. Simpan sebagai sesi-03/sekuens-constructor.puml atau .jpg.
2. Jawab dalam 150 kata: kapan penggunaan static membuat kode sulit diuji? Beri satu contoh dari kode Anda sendiri.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-03/java/` | RekeningBank.java dan Main.java yang berjalan. |
| `sesi-03/php/` | RekeningBank.php dan main.php yang berjalan. |
| `sesi-03/percobaan-static.md` | Tiga percobaan static beserta hasil dan pesan kesalahannya. |
| `sesi-03/sekuens-constructor.*` | Diagram sekuens pembuatan objek. |
| `sesi-02/java/Buku.java, sesi-02/php/Buku.php` | Versi terbaru dengan konstanta dan penghitung static. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Tunjuk baris delegasi constructor Anda. Apa yang terjadi kalau saya menghapusnya dan menyalin validasinya ke constructor kedua?
2. Mengapa penghitung jumlahRekening dinaikkan hanya di satu constructor?
3. Pada percobaan nomor 3, mengapa kedua rekening menampilkan saldo yang sama?
4. Apa beda self:: dan static:: di PHP? Tunjukkan di kode Anda dan jelaskan mengapa Anda memilih salah satunya.
5. Saya ingin mengubah bunga dari 2,5% menjadi 3%. Berapa tempat yang harus saya sunting di kode Anda? Lakukan sekarang.
6. Method mana di kelas Anda yang pantas static, dan mana yang tidak? Berikan alasannya untuk masing-masing.
7. Kalau saya membuat method tarik() menjadi static, apa yang rusak?

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| Kompilasi gagal: non-static variable this cannot be referenced from a static context | Method static mencoba mengakses atribut instance. | Jadikan method non-static, atau terima objeknya sebagai parameter. |
| Penghitung objek menunjukkan angka dua kali lipat | Penghitung dinaikkan di kedua constructor padahal ada delegasi. | Naikkan hanya di constructor lengkap. |
| this(...) harus menjadi pernyataan pertama | Ada baris kode sebelum pemanggilan this(...). | Pindahkan this(...) ke baris pertama badan constructor. Itu aturan bahasa, bukan gaya. |
| PHP: Cannot redeclare __construct() | Mencoba membuat dua constructor seperti di Java. | PHP tidak mendukung overloading. Gunakan default parameter atau named constructor static. |
| Konstanta PHP bertipe menolak dikompilasi | Typed class constant (const float X = ...) butuh PHP 8.3 ke atas. | Periksa php -v. Jika di bawah 8.3, tulis tanpa tipe: const X = 0.025; |

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