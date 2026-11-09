package id.ac.univpancasila.perpustakaan;
import id.ac.univpancasila.perpustakaan.model.*;
import id.ac.univpancasila.perpustakaan.service.PerpustakaanService;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
// Main menjadi titik masuk dan mengelola interaksi pengguna.
public class Main {
 public static void main(String[] args){
  Scanner in=new Scanner(System.in);PerpustakaanService s=new PerpustakaanService();boolean jalan=true;
  while(jalan){
   System.out.println("\\n=== SISTEM PERPUSTAKAAN ===\\n1. Tambah buku\\n2. Tambah anggota\\n3. Daftar buku\\n4. Daftar anggota\\n0. Keluar");
   System.out.print("Pilih: ");String p=in.nextLine().trim();
   try{switch(p){
    case "1"->{System.out.print("ISBN: ");String isbn=in.nextLine().trim();System.out.print("Judul: ");String judul=in.nextLine().trim();System.out.print("Penulis: ");String penulis=in.nextLine().trim();System.out.print("Stok: ");int stok=Integer.parseInt(in.nextLine().trim());s.tambahBuku(new Buku(isbn,StringUtils.capitalize(judul),penulis,stok));System.out.println("Buku ditambahkan.");}
    case "2"->{System.out.print("ID anggota: ");String id=in.nextLine().trim();System.out.print("Nama: ");String n=in.nextLine().trim();System.out.print("Program studi: ");String prodi=in.nextLine().trim();s.tambahAnggota(new Anggota(id,n,prodi));System.out.println("Anggota ditambahkan.");}
    case "3"->{for(Buku b:s.getDaftarBuku())System.out.println(b);if(s.getDaftarBuku().isEmpty())System.out.println("Belum ada buku.");}
    case "4"->{for(Anggota a:s.getDaftarAnggota())System.out.println(a);if(s.getDaftarAnggota().isEmpty())System.out.println("Belum ada anggota.");}
    case "0"->jalan=false;default->System.out.println("Pilihan tidak tersedia.");
   }}catch(NumberFormatException e){System.out.println("Stok harus berupa angka.");}catch(IllegalArgumentException e){System.out.println("Validasi gagal: "+e.getMessage());}
  }in.close();System.out.println("Program selesai.");
 }
}