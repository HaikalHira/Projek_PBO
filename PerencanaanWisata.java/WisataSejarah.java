class WisataSejarah extends Destinasi {
    private String periodeSejarah;

    public WisataSejarah(String nama, String lokasi, int harga, String deskripsi, String periodeSejarah) {
        super(nama, lokasi, harga, deskripsi);
        this.periodeSejarah = periodeSejarah;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Periode Sejarah: " + periodeSejarah);
    }
}
