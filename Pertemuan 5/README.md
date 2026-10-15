# Praktikum Sesi 5 — Polimorfisme

**Sub-CPMK-P3 — Mahasiswa mampu mengimplementasikan hierarki kelas dengan pewarisan dan polimorfisme yang benar. (P3)**

**Keterkaitan teori:** Pertemuan 5 — Polimorfisme  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 5% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-05-polimorfisme.docx`](Modul-Praktikum-05-polimorfisme.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Menyimpan objek turunan dalam koleksi bertipe induk (upcasting) dan memprosesnya seragam.
2. Membuktikan dynamic dispatch melalui penelusuran alur eksekusi.
3. Menambah tipe baru tanpa menyunting satu baris pun kode pemanggil.
4. Mengubah kode berbasis rantai instanceof menjadi kode polimorfik.

## B. Alat dan bahan

- Lingkungan hasil sesi 1.
- Hierarki dari sesi 4 (untuk pembanding).

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/java/BangunDatar.java` | Kelas abstrak dengan TODO. |
| `starter/java/Lingkaran.java` | Turunan dengan TODO luas dan keliling. |
| `starter/java/Persegi.java` | Turunan dengan TODO. |
| `starter/java/Main.java` | Program uji dengan array polimorfik — TIDAK BOLEH DIUBAH. |
| `starter/java/AntiPattern.java` | Versi rantai instanceof yang harus Anda refaktor. |
| `starter/php/BangunDatar.php` | Padanan PHP dengan TODO. |
| `starter/php/main.php` | Program uji PHP. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Melengkapi hierarki BangunDatar

- Salin starter ke sesi-05/. Lengkapi TODO pada Lingkaran dan Persegi: method luas() dan keliling().
- Perhatikan bahwa Main sudah menyimpan ketiganya dalam satu array bertipe BangunDatar[]. Anda TIDAK BOLEH mengubah Main pada langkah ini.

**Terminal**

```bash
cd sesi-05/java
javac -d out *.java
java -cp out Main
```

> **Checkpoint —** Main mencetak luas dan keliling ketiga bangun tanpa satu pun pemeriksaan tipe.

### Langkah 2 — Membuat kelas Segitiga secara mandiri

- Buat Segitiga dengan tiga sisi. Gunakan rumus Heron untuk luas.
- Tolak konstruksi bila ketiga sisi tidak membentuk segitiga (jumlah dua sisi harus lebih besar dari sisi ketiga).
- Tambahkan objeknya ke array di Main — hanya menambah satu baris, tanpa menyunting logika perulangan.

> **Checkpoint —** Segitiga(3,4,5) menghasilkan luas 6,00. Segitiga(1,2,10) ditolak saat pembuatan objek.

### Langkah 3 — Menelusuri dynamic dispatch

- Buat sesi-05/penelusuran.md.
- Untuk baris System.out.println(b) di dalam perulangan Main, tuliskan urutan pemanggilan yang sesungguhnya terjadi: method mana milik kelas mana, dipanggil dalam urutan apa.
- Petunjuk: toString() ada di kelas induk, tetapi ia memanggil luas() yang ada di turunan. Jelaskan bagaimana induk bisa memanggil method turunan.

> **Checkpoint —** penelusuran.md menjelaskan bahwa yang menentukan method mana yang dijalankan adalah TIPE OBJEK, bukan tipe variabel.

### Langkah 4 — Membuktikan Open-Closed Principle

- Buat kelas Trapesium. Tambahkan satu objeknya ke array.
- Catat di penelusuran.md: berapa berkas LAMA yang isinya harus Anda ubah? (Jawaban yang benar adalah nol; Main hanya BERTAMBAH satu baris, tidak berubah logikanya.)

> **Checkpoint —** Program berjalan dengan empat bangun datar, dan logika perulangan di Main tidak pernah disentuh.

### Langkah 5 — Merefaktor AntiPattern menjadi polimorfik

- Buka AntiPattern.java. Di dalamnya ada method hitungLuas(Object) berisi rantai if-instanceof.
- Jalankan dulu, amati keluarannya.
- Refaktor menjadi polimorfik. Simpan hasilnya sebagai AntiPatternRefaktor.java — JANGAN menghapus berkas aslinya, keduanya diperlukan untuk demo.
- Catat di penelusuran.md: berapa baris yang harus disunting untuk menambah bangun datar baru pada masing-masing versi?

> **Checkpoint —** Kedua versi menghasilkan total luas yang sama, tetapi versi refaktor tidak punya satu pun instanceof.

### Langkah 6 — Versi PHP dan latihan Notifikasi

- Kerjakan padanan PHP-nya di sesi-05/php/.
- Lalu buat hierarki baru tanpa starter: Notifikasi (abstrak) dengan turunan Email, SMS, dan WhatsApp, masing-masing punya kirim(string $pesan) sendiri.
- Tulis satu fungsi kirimSemua(array $daftar, string $pesan) yang TIDAK MENGANDUNG satu pun pemeriksaan tipe.

**Terminal**

```bash
cd ../php
php main.php
php notifikasi.php
```

> **Checkpoint —** kirimSemua berjalan untuk ketiga jenis notifikasi tanpa instanceof.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Tambahkan method static hitungLuas() pada BangunDatar, lalu override di Lingkaran. Amati method mana yang terpanggil. Jelaskan mengapa hasilnya berbeda dari overriding biasa.
2. Ubah tipe array di Main dari BangunDatar[] menjadi Object[]. Catat apa yang rusak dan mengapa.

## F. Tugas rumah

1. Jawab dalam 150 kata: mengapa framework yang ditulis bertahun-tahun lalu bisa memanggil kode yang Anda tulis hari ini? Kaitkan dengan dynamic dispatch. Simpan di sesi-05/refleksi.md.
2. Tambahkan perhitungan luas ke hierarki Koleksi perpustakaan Anda dari sesi 4 — misalnya biayaKeterlambatan() yang berbeda untuk Buku, Majalah, dan Skripsi.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-05/java/` | BangunDatar dan empat turunannya, AntiPattern.java, AntiPatternRefaktor.java. |
| `sesi-05/php/` | Padanan PHP plus notifikasi.php. |
| `sesi-05/penelusuran.md` | Penelusuran dynamic dispatch, hitungan berkas yang disunting, perbandingan dua versi AntiPattern. |
| `sesi-05/refleksi.md` | Jawaban 150 kata tentang framework dan dynamic dispatch. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Pada baris perulangan di Main, tipe variabelnya apa? Tipe objeknya apa? Yang mana yang menentukan method mana yang dijalankan?
2. Bagaimana method toString() di kelas induk bisa memanggil luas() yang isinya hanya ada di turunan?
3. Saya ingin menambah BelahKetupat sekarang. Berkas mana yang isinya harus berubah? Lakukan.
4. Bandingkan AntiPattern.java dan AntiPatternRefaktor.java. Berapa baris yang harus disunting untuk menambah tipe baru pada masing-masing?
5. Hapus anotasi @Override pada salah satu method, lalu ubah nama method-nya menjadi Luas() dengan L besar. Apa yang terjadi? Kembalikan @Override dan ulangi.
6. Di kode PHP Anda, mengapa kirimSemua tidak perlu tahu jenis notifikasinya?
7. Kalau saya menyimpan objek Lingkaran ke variabel bertipe Object, method apa saja yang masih boleh saya panggil? Mengapa?

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| Semua bangun mencetak luas 0 | Method luas() di turunan tidak benar-benar meng-override (beda nama, beda parameter, atau beda huruf besar-kecil). | Tambahkan @Override pada setiap override. Kompilator akan menunjukkan yang salah. |
| Kompilasi gagal: BangunDatar is abstract; cannot be instantiated | Mencoba new BangunDatar(...). | Buat objek dari kelas turunannya. |
| Ingin memanggil getJariJari() dari variabel bertipe BangunDatar | Kompilator hanya melihat tipe variabel. | Gunakan pattern matching: if (b instanceof Lingkaran l) { l.getJariJari(); } — tetapi pertimbangkan dulu apakah perilakunya sebaiknya dipindah ke induk. |
| Rumus Heron menghasilkan NaN | Ketiga sisi tidak membentuk segitiga sehingga akar dari bilangan negatif. | Validasi di constructor — tolak sebelum objek terbentuk. |
| PHP: keluaran __toString tidak muncul | Objek dicetak dengan var_dump atau print_r, bukan echo. | Gunakan echo $objek atau (string) $objek. |

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