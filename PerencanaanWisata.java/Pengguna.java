import java.util.ArrayList;
import java.util.List;

public class Pengguna {
    private String nama;
    private int anggaran;
    private List<Destinasi> destinasiList;

    public Pengguna(String nama, int anggaran) {
        this.nama = nama;
        this.anggaran = anggaran;
        this.destinasiList = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public int getAnggaran() {
        return anggaran;
    }

    public void tambahDestinasi(Destinasi destinasi) {
        destinasiList.add(destinasi);
    }

    public void hapusDestinasi(int index) {
        if (index >= 0 && index < destinasiList.size()) {
            destinasiList.remove(index);
        }
    }

    public int getTotalBiaya() {
        return destinasiList.stream().mapToInt(Destinasi::getHarga).sum();
    }
}
