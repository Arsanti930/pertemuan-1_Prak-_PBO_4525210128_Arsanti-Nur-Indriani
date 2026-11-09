# Skenario Kegagalan — Sistem Perpustakaan

Skenario berikut dijalankan pada layanan contoh Java dan PHP di folder
[`after/`](../after/). Setiap kegagalan dibandingkan pada kedua implementasi.

| No | Masukan / tindakan | Kegagalan yang diharapkan | Jenis exception | Pesan yang ditampilkan |
|----|--------------------|---------------------------|-----------------|------------------------|
| 1 | Daftarkan anggota `A-01`, pinjam ISBN `978-404` yang belum terdaftar | Buku tidak ditemukan; ISBN ikut terbawa | `BukuTidakDitemukanException` | `Buku dengan ISBN 978-404 tidak ditemukan.` |
| 2 | Daftarkan buku `978-1`, pinjam oleh anggota `A-404` yang belum terdaftar | Anggota tidak ditemukan; ID ikut terbawa | `AnggotaTidakDitemukanException` | `Anggota dengan ID A-404 tidak ditemukan.` |
| 3 | Pinjam ISBN dan ID anggota yang sama-sama belum terdaftar | Pemeriksaan anggota dilakukan lebih dahulu | `AnggotaTidakDitemukanException` | `Anggota dengan ID A-404 tidak ditemukan.` |
| 4 | Daftarkan buku `978-2` dengan stok 0 lalu pinjam | Buku tidak tersedia; stok tetap 0 | `BukuSedangDipinjamException` | `Buku PBO Dasar dengan ISBN 978-2 sedang tidak tersedia.` |
| 5 | Stok `978-3` satu eksemplar; anggota pertama meminjam, anggota kedua mencoba meminjam | Eksemplar telah habis dipinjam anggota pertama | `BukuSedangDipinjamException` | `Buku OOP dengan ISBN 978-3 sedang tidak tersedia.` |
| 6 | Anggota `A-01` sudah meminjam tiga buku, lalu meminjam buku keempat | Batas tiga buku terlampaui; stok buku keempat tidak berubah | `BatasPinjamTerlampauiException` | `Anggota dengan ID A-01 telah mencapai batas pinjam 3 buku.` |
| 7 | Anggota meminjam dua buku, berhasil meminjam buku ketiga, lalu mencoba buku keempat | Batas berlaku setelah tiga peminjaman, bukan sebelumnya | `BatasPinjamTerlampauiException` | `Anggota dengan ID A-01 telah mencapai batas pinjam 3 buku.` |
| 8 | Anggota telah mencapai batas, lalu meminta ISBN yang tidak terdaftar | ISBN diperiksa sebelum batas pinjam | `BukuTidakDitemukanException` | `Buku dengan ISBN 978-404 tidak ditemukan.` |
| 9 | Anggota telah mencapai batas, lalu mencoba buku terdaftar dengan stok 0 | Stok diperiksa sebelum batas pinjam | `BukuSedangDipinjamException` | `Buku PBO Dasar dengan ISBN 978-2 sedang tidak tersedia.` |
| 10 | Anggota telah mencapai batas, lalu mencoba buku tersedia; periksa stoknya sesudah gagal | Peminjaman ditolak dan perubahan stok tidak terjadi | `BatasPinjamTerlampauiException` | `Anggota dengan ID A-01 telah mencapai batas pinjam 3 buku.` |

## Jawaban pertanyaan

1. Skenario 1–9 berasal dari permintaan atau keadaan data yang tidak memenuhi
   aturan layanan, sehingga dapat diperlakukan sebagai kegagalan domain yang
   dapat dijelaskan kepada pengguna. Skenario 10 juga menguji prasyarat domain,
   sekaligus memastikan tidak ada perubahan stok saat peminjaman ditolak.
   Kesalahan input mentah seperti huruf pada kolom angka perlu divalidasi di
   lapisan antarmuka sebelum memanggil layanan; kode starter sesi ini tidak
   memiliki antarmuka konsol maupun masukan angka. Exception domain dipakai
   untuk kegagalan aturan perpustakaan, bukan untuk menyamarkan bug pemrogram.
2. `PerpustakaanException` diturunkan dari `RuntimeException` (unchecked).
   Pemanggil bisa menangkap induk tersebut saat perlu menangani kegagalan domain,
   tanpa mewajibkan deklarasi `throws` di setiap method. Exception tetap spesifik
   dan membawa data melalui properti/getter.
3. Jika blok `catch` dibiarkan kosong, program tidak menampilkan alasan
   kegagalan. Pemanggil kehilangan pesan dan data exception, sehingga masalah
   tampak seolah-olah tidak terjadi. Exception harus ditangani secara eksplisit
   atau diteruskan ke lapisan yang tepat, bukan ditelan.

## Catatan batas pengujian

Layanan contoh tidak menyediakan method pengembalian atau ekspor berkas.
Antarmuka konsol pada `after/java` juga memvalidasi masukan stok, data kosong,
dan keberadaan anggota/buku sebelum menjalankan aturan peminjaman. Tabel di
atas berfokus pada sepuluh skenario exception domain layanan yang dapat
digunakan sebagai dasar pengujian unit.
