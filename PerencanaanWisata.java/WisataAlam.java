class WisataAlam extends Destinasi {
    private String jenisHabitat;

    public WisataAlam(String nama, String lokasi, int harga, String deskripsi, String jenisHabitat) {
        super(nama, lokasi, harga, deskripsi);
        this.jenisHabitat = jenisHabitat;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Habitat: " + jenisHabitat);
    }
}
