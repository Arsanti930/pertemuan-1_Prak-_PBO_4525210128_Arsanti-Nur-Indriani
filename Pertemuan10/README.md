# Laporan Praktikum Pertemuan 10

## Biodata

| Data | Keterangan |
|---|---|
| Nama | Arsanti Nur Indriani |
| NPM | 4525210128 |
| Mata Kuliah | Praktikum Pemrograman Berorientasi Objek A |

## Deskripsi

Praktikum Pertemuan 10 membahas *exception handling* dan pemrograman defensif.
Pada tugas ini dibuat hierarki exception domain perpustakaan yang membawa data
penyebab kesalahan, lalu method peminjaman yang sebelumnya menggunakan
percabangan bersarang diganti menjadi urutan *guard clause*. Model dan service
yang sudah dibuat pada Pertemuan 9 disertakan di folder `after` tanpa perubahan,
sedangkan contoh `LibraryService` sesi 10 tetap dipisahkan agar perbandingan
starter dan hasilnya jelas.

## Struktur hasil

```text
Pertemuan10/
├── starter/
│   ├── java/                  # sumber awal Java
│   ├── php/                   # sumber awal PHP
│   └── skenario-uji.md        # tabel skenario lengkap
├── after/
│   ├── java/src/main/java/id/ac/univpancasila/perpustakaan/
│   │   ├── exception/         # exception domain
│   │   ├── model/             # model Pertemuan 9
│   │   └── service/           # semua service Java
│   └── php/
│       ├── exceptions.php     # exception domain
│       └── src/
│           ├── Model/         # model Pertemuan 9
│           └── Service/       # semua service PHP
└── README.md
```

## Hasil output program

Entry point Java `id.ac.univpancasila.perpustakaan.Main` menyediakan menu
untuk menambah buku dan anggota, melihat daftar, serta meminjam buku. Data
katalog dikelola oleh model dan `PerpustakaanService` dari Pertemuan 9;
peminjaman menerapkan guard clause dan exception Pertemuan 10. Jalankan dari
proyek `after/java` dengan Maven Wrapper:

```powershell
.\mvnw.cmd compile
.\mvnw.cmd exec:java
```

Contoh sesi interaktif:

```text
=== SISTEM PERPUSTAKAAN ===
1. Tambah buku
2. Tambah anggota
3. Daftar buku
4. Daftar anggota
5. Pinjam buku
0. Keluar
Pilih menu: 5
ID anggota: A-404
ISBN buku: 978-1
Peminjaman gagal: Anggota dengan ID A-404 tidak ditemukan.
```

Kolom wajib yang kosong, stok bukan bilangan bulat, dan stok negatif langsung
memunculkan petunjuk lalu mengulang kolom yang sama. Input berikutnya tidak
tergeser menjadi pilihan menu atau data kolom lain. ID/ISBN yang belum
terdaftar menampilkan pesan domain tanpa stack trace. Prompt konsol di-flush
dan ditampilkan pada baris tersendiri supaya tetap terlihat saat dijalankan
melalui `mvn exec:java`.

Starter belum melempar exception untuk kegagalan domain. Method `pinjam`
mengembalikan `false`, sehingga pemanggil hanya mengetahui peminjaman gagal:

```text
Starter: pinjam("A-01", "978-404") -> false
Starter: alasan kegagalan tidak tersedia
```

Pada hasil `after`, penyebab kegagalan tampil sebagai pesan exception, dan data
spesifiknya tetap dapat diambil melalui getter:

```text
After: Buku dengan ISBN 978-404 tidak ditemukan.
After: getIsbn() -> 978-404

After: Anggota dengan ID A-404 tidak ditemukan.
After: getIdAnggota() -> A-404

After: Buku PBO Dasar dengan ISBN 978-2 sedang tidak tersedia.
After: getIsbn() -> 978-2, getJudul() -> PBO Dasar

After: Anggota dengan ID A-01 telah mencapai batas pinjam 3 buku.
After: getIdAnggota() -> A-01, getBatas() -> 3
```

Keluaran di atas menunjukkan kontrak service dan isi exception untuk
masing-masing kondisi. Daftar skenario dan pesannya terdapat di
[`starter/skenario-uji.md`](starter/skenario-uji.md).

## Code starter dan after

Starter dipertahankan agar dapat dibandingkan: [Java `LibraryServiceKotor`](starter/java/LibraryServiceKotor.java),
[Java exception](starter/java/exception/), [PHP `LibraryServiceKotor`](starter/php/LibraryServiceKotor.php),
dan [PHP exception](starter/php/exceptions.php).

Implementasi hasil:

- Java [LibraryService](after/java/src/main/java/id/ac/univpancasila/perpustakaan/service/LibraryService.java),
  [LibraryServiceKotor](after/java/src/main/java/id/ac/univpancasila/perpustakaan/service/LibraryServiceKotor.java),
  [exception domain](after/java/src/main/java/id/ac/univpancasila/perpustakaan/exception/).
- PHP [LibraryService](after/php/src/Service/LibraryService.php),
  [LibraryServiceKotor](after/php/src/Service/LibraryServiceKotor.php),
  [exception domain](after/php/exceptions.php).
- Model dan service Pertemuan 9: [Java](after/java/src/main/java/id/ac/univpancasila/perpustakaan/),
  [PHP Model](after/php/src/Model/), [PHP Service](after/php/src/Service/PerpustakaanService.php).

Contoh inti *guard clause* (berlaku sama pada Java dan PHP):

```text
jika anggota tidak terdaftar -> lempar AnggotaTidakDitemukanException
jika buku tidak terdaftar   -> lempar BukuTidakDitemukanException
jika stok habis             -> lempar BukuSedangDipinjamException
jika batas tercapai         -> lempar BatasPinjamTerlampauiException
kurangi stok dan jumlah pinjaman
```

Kode lengkap Java dan PHP tersedia melalui tautan file di atas. Exception
turunan menyimpan ISBN, ID anggota, judul, atau batas sebagai data tersendiri,
bukan hanya menyisipkannya ke kalimat pesan.

## Penjelasan

`PerpustakaanException` menjadi induk exception domain dan diturunkan dari
`RuntimeException`. Pilihan ini sesuai untuk permintaan peminjaman yang tidak
dapat dipenuhi karena keadaan domain; pemanggil dapat menangkap satu tipe induk
tanpa deklarasi checked exception berulang. Setiap kondisi gagal diperiksa di
awal `pinjam`, sehingga tidak perlu membuat blok `else` berlapis. Perubahan
stok dan jumlah pinjaman hanya dilakukan setelah semua pemeriksaan berhasil.

Exception PHP menggunakan *constructor property promotion* dan properti
`readonly`; implementasi Java menggunakan atribut `final`. Keduanya memiliki
getter agar data kegagalan dapat dipakai pemanggil tanpa mengurai pesan.

## Validasi

Skenario pada tabel diuji terhadap layanan Java dan PHP. Java tersedia melalui
Maven Wrapper dan PHP diperiksa dengan syntax lint. Detail seluruh sepuluh
skenario dan jawaban pertanyaan praktikum ada di
[`skenario-uji.md`](starter/skenario-uji.md).

## Hak cipta tugas

Hak cipta © 2026 Arsanti Nur Indriani untuk laporan dan hasil pengerjaan ini.
Disusun untuk keperluan akademik Praktikum Pemrograman Berorientasi Objek A.
Materi modul dan starter tetap merupakan bahan praktikum yang menjadi acuan
pengerjaan.
