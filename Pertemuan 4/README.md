# Praktikum Sesi 4 — Pewarisan

**Sub-CPMK-P3 — Mahasiswa mampu mengimplementasikan hierarki kelas dengan pewarisan dan polimorfisme yang benar. (P3)**

**Keterkaitan teori:** Pertemuan 4 — Pewarisan (Inheritance)  
**Durasi:** 170 menit (1 SKS praktikum) · **Bobot:** 5% dari nilai praktikum  
**Bahasa:** Java 25 dan PHP 8.4+

> Versi cetak modul ini: [`Modul-Praktikum-04-pewarisan.docx`](Modul-Praktikum-04-pewarisan.docx). Kerangka kode: [`starter/`](starter/).

---

## A. Tujuan sesi

1. Membangun hierarki kelas dengan extends dan pemanggilan constructor induk.
2. Menggunakan super/parent untuk memakai ulang perhitungan induk, bukan menyalinnya.
3. Menguji setiap relasi pewarisan dengan pertanyaan "is-a".
4. Mengubah satu relasi pewarisan yang keliru menjadi komposisi.

## B. Alat dan bahan

- Lingkungan hasil sesi 1.
- PlantUML (ekstensi VS Code atau plantuml.jar) untuk membuat class diagram.

## C. Berkas starter

Salin ke folder tugas Anda; **jangan** menyunting berkas aslinya. Setiap komentar `TODO` harus Anda lengkapi sendiri — kode yang disalin dari sumber lain akan terlihat pada sesi demo.

| Berkas | Keterangan |
|---|---|
| `starter/java/Pegawai.java` | Kelas induk dengan TODO pada validasi dan method hitungGaji(). |
| `starter/java/PegawaiTetap.java` | Turunan dengan TODO tunjangan masa kerja. |
| `starter/java/PegawaiKontrak.java` | Turunan dengan TODO. |
| `starter/java/Main.java` | Program uji. |
| `starter/php/Pegawai.php` | Seluruh hierarki PHP dalam satu berkas, dengan TODO yang sama. |
| `starter/php/main.php` | Program uji PHP. |

## D. Langkah kerja

Kerjakan berurutan. Jangan melanjutkan ke langkah berikutnya sebelum checkpoint terpenuhi.

### Langkah 1 — Melengkapi kelas induk Pegawai

- Salin starter ke sesi-04/. Buka Pegawai.java.
- Lengkapi constructor dan method hitungGaji() dasar. Atribut gunakan protected, bukan public — turunan boleh membaca, dunia luar tidak.
- Perhatikan bahwa Pegawai bersifat abstract. Coba jalankan new Pegawai(...) di Main dan catat pesan kompilatornya di sesi-04/catatan.md.

> **Checkpoint —** Kompilasi gagal saat mencoba membuat objek Pegawai secara langsung — dan Anda sudah mencatat pesannya.

### Langkah 2 — Melengkapi PegawaiTetap dengan pemanggilan super

- Lengkapi constructor: baris pertama WAJIB memanggil super(nip, nama, gajiPokok).
- Lengkapi hitungGaji(): tunjangan masa kerja 2% gaji pokok per tahun, maksimum 40%.
- PENTING: hitung dengan memanggil super.hitungGaji(), jangan menyalin ulang rumus induk. Ini yang dinilai.

**Terminal**

```bash
cd sesi-04/java
javac -d out *.java
java -cp out Main
```

> **Checkpoint —** Pegawai tetap dengan masa kerja 15 tahun dan gaji pokok 6.000.000 menghasilkan gaji 7.800.000.

### Langkah 3 — Percobaan: menghapus panggilan super

- Hapus sementara baris super(...) pada constructor PegawaiTetap. Kompilasi.
- Catat pesan kesalahannya di catatan.md, lalu kembalikan barisnya.
- Selanjutnya, ubah super.hitungGaji() menjadi rumus yang disalin manual. Jalankan — hasilnya sama. Lalu ubah rumus di kelas induk. Jalankan lagi. Catat apa yang terjadi, dan mengapa versi super lebih baik.

> **Checkpoint —** catatan.md memuat dua pesan kesalahan dan satu penjelasan mengapa menyalin rumus induk berbahaya.

### Langkah 4 — Membuat subkelas Dosen secara mandiri

- Tanpa starter. Buat Dosen sebagai turunan PegawaiTetap, dengan tambahan jabatan fungsional dan tunjangan fungsional.
- Sebelum menulis kode, ucapkan kalimatnya: "Dosen adalah PegawaiTetap". Apakah masuk akal? Tuliskan jawabannya di catatan.md.
- Tambahkan juga PegawaiHarian dengan perhitungan gaji per hari kerja.

> **Checkpoint —** Main mencetak empat jenis pegawai dengan perhitungan gaji yang berbeda, dan total beban gaji.

### Langkah 5 — Membuat class diagram

- Buat sesi-04/uml/hierarki-pegawai.puml yang menggambarkan seluruh hierarki, lengkap dengan atribut, method, dan tanda abstract.
- Render dan pastikan diagramnya konsisten dengan kode — setiap kelas di diagram harus ada di kode, dan sebaliknya.

**Terminal**

```bash
plantuml -tsvg sesi-04/uml/hierarki-pegawai.puml
```

> **Checkpoint —** Diagram menampilkan minimal lima kelas dengan panah pewarisan yang benar arahnya.

### Langkah 6 — Menemukan pewarisan yang keliru dan memperbaikinya

- Di starter tersedia berkas KomposisiVsWarisan yang memuat contoh "Mobil extends Mesin" — sebuah pewarisan yang keliru.
- Jalankan, amati, lalu TULIS ULANG menjadi komposisi: Mobil PUNYA SumberTenaga.
- Buktikan keunggulannya dengan mengganti sumber tenaga saat program berjalan — sesuatu yang tidak mungkin dilakukan dengan pewarisan.

> **Checkpoint —** Program menampilkan mobil yang berganti dari mesin bensin ke motor listrik tanpa membuat objek Mobil baru.

## E. Latihan mandiri di lab

_Dikerjakan bila langkah kerja selesai lebih awal. Tidak wajib, tetapi menambah nilai pada aspek penerapan konsep._

1. Tambahkan method final pada kelas induk, lalu coba override di turunan. Catat pesan kompilatornya. Kapan Anda akan menandai method sebagai final?
2. Terapkan pewarisan pada domain perpustakaan: buat kelas abstrak Koleksi dengan turunan Buku, Majalah, dan Skripsi. Simpan di sesi-04/latihan/.

## F. Tugas rumah

1. TUGAS 1 (mata kuliah teori) dikerjakan bersama sesi ini: rancang hierarki penggajian universitas minimal empat jenis pegawai, implementasikan di Java dan PHP, sertakan class diagram.
2. Justifikasi 300–400 kata: untuk setiap extends yang Anda buat, buktikan kalimat "X adalah Y" benar. Sertakan SATU relasi yang sengaja Anda buat sebagai komposisi beserta alasannya. Simpan di sesi-04/justifikasi.md.

## G. Luaran yang dikumpulkan

Seluruh luaran diserahkan melalui repositori Git pribadi Anda, dengan riwayat commit yang menunjukkan proses pengerjaan — bukan satu commit tunggal di akhir.

| Berkas / folder | Keterangan |
|---|---|
| `sesi-04/java/` | Hierarki Pegawai lengkap dengan Dosen dan PegawaiHarian. |
| `sesi-04/php/` | Padanan PHP-nya. |
| `sesi-04/uml/hierarki-pegawai.puml` | Class diagram yang konsisten dengan kode. |
| `sesi-04/catatan.md` | Pesan kesalahan percobaan dan penjelasan super vs salin rumus. |
| `sesi-04/justifikasi.md` | Justifikasi is-a untuk setiap pewarisan, plus satu contoh komposisi. |

## H. Rubrik penilaian sesi ini

| Aspek | Bobot | Kriteria |
|---|---|---|
| Kebenaran fungsional | 35% | Seluruh perintah pada langkah kerja berjalan dan menghasilkan keluaran yang diminta. |
| Penerapan konsep sesi ini | 30% | Konsep yang menjadi Sub-CPMK sesi ini diterapkan dengan tepat, bukan sekadar membuat program berjalan. |
| Keterbacaan dan konvensi | 10% | Penamaan bermakna, format konsisten, komentar seperlunya, riwayat commit wajar. |
| Demo dan pertanyaan lisan | 25% | Mampu menjelaskan setiap baris kode sendiri dan menjawab pertanyaan demo. MENGGUGURKAN: tanpa demo, tugas tidak dinilai. |

## I. Pertanyaan demo

> Pertanyaan berikut akan diajukan saat Anda mendemokan pekerjaan sesi ini. Daftar ini sengaja dibuka agar Anda mempersiapkan **pemahaman**, bukan hafalan. Anda boleh memakai alat bantu apa pun saat mengerjakan — tetapi kode yang tidak dapat Anda jelaskan sendiri tidak dinilai.

1. Ucapkan kalimat "is-a" untuk setiap extends di kode Anda. Adakah yang terasa dipaksakan?
2. Tunjuk baris super.hitungGaji() Anda. Apa yang terjadi kalau saya mengubah rumus di kelas induk sekarang?
3. Mengapa atribut di kelas induk Anda protected dan bukan public?
4. Saya ingin menambah jenis pegawai baru: PegawaiParuhWaktu. Berkas mana saja yang harus saya sentuh? Lakukan sekarang.
5. Pada bagian komposisi, mengapa mobil bisa berganti sumber tenaga saat program berjalan, sedangkan versi pewarisan tidak bisa?
6. Kalau saya minta Anda membuat kelas Rektor, apakah ia turunan Dosen? Argumentasikan.
7. Apa yang terjadi kalau constructor turunan tidak memanggil super? Tunjukkan pesannya di catatan.md Anda.

## J. Kesalahan yang sering terjadi

| Gejala | Penyebab yang lazim | Cara memperbaiki |
|---|---|---|
| cannot find symbol: constructor Pegawai() | Constructor turunan tidak memanggil super(...) dan induk tidak punya constructor tanpa argumen. | Tambahkan super(...) sebagai baris pertama constructor turunan. |
| Gaji turunan sama dengan gaji induk | Method hitungGaji() di turunan belum di-override, atau ditulis dengan nama/parameter berbeda. | Tambahkan anotasi @Override — kompilator akan menolak bila ternyata tidak meng-override apa pun. |
| Perubahan rumus di induk tidak terasa di turunan | Rumus induk disalin manual, bukan dipanggil lewat super. | Ganti dengan super.hitungGaji() / parent::hitungGaji(). |
| PHP: Cannot instantiate abstract class Pegawai | Mencoba new Pegawai(...). | Itu memang perilaku yang benar. Buat objek dari kelas turunannya. |
| Diagram PlantUML tidak sesuai dengan kode | Diagram dibuat lebih dulu lalu kode berubah, atau sebaliknya. | Perbarui keduanya. Diagram yang tidak tercermin di kode dianggap belum selesai. |

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