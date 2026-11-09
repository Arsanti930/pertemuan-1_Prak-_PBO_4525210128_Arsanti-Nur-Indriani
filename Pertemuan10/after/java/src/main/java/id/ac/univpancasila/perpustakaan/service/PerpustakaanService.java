package id.ac.univpancasila.perpustakaan.service;
import id.ac.univpancasila.perpustakaan.model.*;
import java.util.ArrayList; import java.util.List;
// Service memisahkan aturan pengelolaan data dari antarmuka console.
public class PerpustakaanService {
 private final List<Buku> daftarBuku=new ArrayList<>(); private final List<Anggota> daftarAnggota=new ArrayList<>();
 public void tambahBuku(Buku b){if(b==null)throw new IllegalArgumentException("Buku null.");if(cariBuku(b.getIsbn())!=null)throw new IllegalArgumentException("ISBN sudah terdaftar.");daftarBuku.add(b);}
 public void tambahAnggota(Anggota a){if(a==null)throw new IllegalArgumentException("Anggota null.");if(cariAnggota(a.getId())!=null)throw new IllegalArgumentException("ID anggota sudah terdaftar.");daftarAnggota.add(a);}
 public Buku cariBuku(String isbn){for(Buku b:daftarBuku)if(b.getIsbn().equals(isbn))return b;return null;}
 public Anggota cariAnggota(String id){for(Anggota a:daftarAnggota)if(a.getId().equals(id))return a;return null;}
 public List<Buku> getDaftarBuku(){return List.copyOf(daftarBuku);}
 public List<Anggota> getDaftarAnggota(){return List.copyOf(daftarAnggota);}
}