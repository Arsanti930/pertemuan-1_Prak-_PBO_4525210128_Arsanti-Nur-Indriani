package id.ac.univpancasila.perpustakaan.model;
// Model Anggota menyimpan identitas anggota perpustakaan.
public class Anggota {
 private final String id,nama,programStudi;
 public Anggota(String id,String nama,String programStudi){
  if(id==null||id.isBlank()||nama==null||nama.isBlank()||programStudi==null||programStudi.isBlank()) throw new IllegalArgumentException("Data anggota wajib diisi.");
  this.id=id;this.nama=nama;this.programStudi=programStudi;
 }
 public String getId(){return id;} public String getNama(){return nama;} public String getProgramStudi(){return programStudi;}
 public String toString(){return id+" | "+nama+" | "+programStudi;}
}