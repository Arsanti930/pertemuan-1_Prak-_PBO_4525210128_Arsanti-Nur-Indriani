# Catatan Percobaan Package dan Build
- Deklarasi `package` harus sesuai dengan jalur direktori di bawah `src/main/java`.
- Mengubah nama package tanpa memindahkan file membuat lokasi dan deklarasi package tidak konsisten serta dapat memutus import.
- Perbaikan: samakan deklarasi package dengan direktori dan sesuaikan import, lalu jalankan `mvn clean compile`.
- Folder `target/` adalah artefak build yang dapat dibuat ulang sehingga diabaikan Git.
- `pom.xml` harus di-commit karena berisi identitas proyek, versi Java, dan dependensi.
- Apache Commons Lang dipakai pada `Main.java` melalui `StringUtils.capitalize`.
