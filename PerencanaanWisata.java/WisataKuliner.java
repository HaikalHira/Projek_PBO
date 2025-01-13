class WisataKuliner extends Destinasi {
    private String spesialisasi;

    public WisataKuliner(String nama, String lokasi, int harga, String deskripsi, String spesialisasi) {
        super(nama, lokasi, harga, deskripsi);
        this.spesialisasi = spesialisasi;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Spesialisasi Kuliner: " + spesialisasi);
    }
}
