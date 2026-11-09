package id.ac.univpancasila.perpustakaan.model;
import java.util.ArrayList;
import java.util.List;
// Model Buku menerapkan enkapsulasi dan komposisi melalui objek Bab.
public class Buku {
 private final String isbn, judul, penulis; private int stok;
 private final List<Bab> bab = new ArrayList<>();
 public Buku(String isbn,String judul,String penulis,int stok) {
  if(isbn==null||isbn.isBlank()||judul==null||judul.isBlank()||penulis==null||penulis.isBlank()) throw new IllegalArgumentException("Data buku wajib diisi.");
  if(stok<0) throw new IllegalArgumentException("Stok tidak boleh negatif.");
  this.isbn=isbn; this.judul=judul; this.penulis=penulis; this.stok=stok;
 }
 public void tambahBab(int nomor,String judulBab) {
  if(nomor<=0||judulBab==null||judulBab.isBlank()) throw new IllegalArgumentException("Data bab tidak valid.");
  bab.add(new Bab(nomor,judulBab));
 }
 public List<Bab> getBab(){return List.copyOf(bab);}
 public void kurangiStok(){if(stok<=0)throw new IllegalStateException("Stok buku habis.");stok--;}
 public void tambahStok(){stok++;}
 public boolean tersedia(){return stok>0;}
 public String getIsbn(){return isbn;} public String getJudul(){return judul;}
 public String getPenulis(){return penulis;} public int getStok(){return stok;}
 public String toString(){return String.format("%-14s | %-28s | %-20s | stok: %d",isbn,judul,penulis,stok);}
 public static final class Bab {
  private final int nomor; private final String judul;
  private Bab(int nomor,String judul){this.nomor=nomor;this.judul=judul;}
  public int getNomor(){return nomor;} public String getJudul(){return judul;}
  public String toString(){return "Bab "+nomor+": "+judul;}
 }
}