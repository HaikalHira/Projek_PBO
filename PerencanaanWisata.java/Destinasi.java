class Destinasi {
    private String nama;
    private String lokasi;
    private int harga;
    private String deskripsi;

    public Destinasi(String nama, String lokasi, int harga, String deskripsi) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Lokasi: " + lokasi);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Deskripsi: " + deskripsi);
    }

    public int getHarga() {
        return harga;
    }

    public String getNama() {
        return nama;
    }
}
