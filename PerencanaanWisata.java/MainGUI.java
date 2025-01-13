import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class MainGUI extends JFrame {
    private Pengguna pengguna;
    private DefaultListModel<String> rencanaModel;
    private JLabel labelAnggaran;
    private JLabel labelTotalBiaya;
    private static final Logger logger = Logger.getLogger(MainGUI.class.getName());

    public MainGUI() {
        // Setup frame
        setTitle("Aplikasi Perencanaan Wisata Digital");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom Font
        Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        // Panel Background with Gradient
        JPanel panelBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(58, 123, 213), getWidth(), getHeight(), new Color(0, 210, 255));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelBackground.setLayout(new BorderLayout());
        setContentPane(panelBackground);

        // Panel Header
        JPanel panelHeader = new JPanel();
        panelHeader.setOpaque(false);
        JLabel title = new JLabel("Aplikasi Perencanaan Wisata Digital");
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        panelHeader.add(title);
        panelHeader.setBorder(new EmptyBorder(10, 0, 10, 0));
        panelBackground.add(panelHeader, BorderLayout.NORTH);

        // Panel Input Pengguna
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setOpaque(false);
        panelInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Informasi Pengguna", 0, 0, labelFont, Color.WHITE));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField inputNama = new JTextField(15);
        JTextField inputAnggaran = new JTextField(15);
        JButton btnMulai = new JButton("Mulai");
        btnMulai.setFont(buttonFont);
        btnMulai.setBackground(new Color(0, 153, 153));
        btnMulai.setForeground(Color.WHITE);
        btnMulai.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setFont(labelFont);
        labelNama.setForeground(Color.WHITE);
        panelInput.add(labelNama, gbc);
        gbc.gridx = 1;
        panelInput.add(inputNama, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel labelAnggaranInput = new JLabel("Anggaran:");
        labelAnggaranInput.setFont(labelFont);
        labelAnggaranInput.setForeground(Color.WHITE);
        panelInput.add(labelAnggaranInput, gbc);
        gbc.gridx = 1;
        panelInput.add(inputAnggaran, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panelInput.add(btnMulai, gbc);

        panelBackground.add(panelInput, BorderLayout.WEST);

        // Panel Destinasi
        JPanel panelDestinasi = new JPanel(new BorderLayout());
        panelDestinasi.setOpaque(false);
        panelDestinasi.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Daftar Destinasi", 0, 0, labelFont, Color.WHITE));

        DefaultListModel<String> destinasiModel = new DefaultListModel<>();
        JList<String> listDestinasi = new JList<>(destinasiModel);
        listDestinasi.setFont(labelFont);

        JComboBox<String> kategoriComboBox = new JComboBox<>(new String[]{"Wisata Alam", "Wisata Budaya", "Wisata Kuliner"});
        kategoriComboBox.setFont(buttonFont);
        kategoriComboBox.setBackground(Color.WHITE);

        JButton btnTambah = new JButton("Tambah ke Rencana");
        btnTambah.setFont(buttonFont);
        btnTambah.setBackground(new Color(0, 153, 76));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFocusPainted(false);

        panelDestinasi.add(kategoriComboBox, BorderLayout.NORTH);
        panelDestinasi.add(new JScrollPane(listDestinasi), BorderLayout.CENTER);
        panelDestinasi.add(btnTambah, BorderLayout.SOUTH);
        panelBackground.add(panelDestinasi, BorderLayout.CENTER);

        // Panel Rencana Perjalanan
        JPanel panelRencana = new JPanel(new BorderLayout());
        panelRencana.setOpaque(false);
        panelRencana.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Rencana Perjalanan", 0, 0, labelFont, Color.WHITE));
        rencanaModel = new DefaultListModel<>();
        JList<String> listRencana = new JList<>(rencanaModel);
        listRencana.setFont(labelFont);
        JButton btnSimpan = new JButton("Simpan Rencana");
        JButton btnHapus = new JButton("Hapus Destinasi");
        btnSimpan.setFont(buttonFont);
        btnSimpan.setBackground(new Color(0, 102, 204));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);

        btnHapus.setFont(buttonFont);
        btnHapus.setBackground(new Color(204, 0, 0));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFocusPainted(false);

        labelAnggaran = new JLabel("Anggaran: Rp0");
        labelAnggaran.setFont(labelFont);
        labelAnggaran.setForeground(Color.WHITE);

        labelTotalBiaya = new JLabel("Total Biaya: Rp0");
        labelTotalBiaya.setFont(labelFont);
        labelTotalBiaya.setForeground(Color.WHITE);

        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.setOpaque(false);
        panelInfo.add(labelAnggaran);
        panelInfo.add(labelTotalBiaya);

        panelRencana.add(new JScrollPane(listRencana), BorderLayout.CENTER);
        panelRencana.add(panelInfo, BorderLayout.NORTH);

        JPanel panelRencanaButtons = new JPanel(new GridLayout(1, 2));
        panelRencanaButtons.setOpaque(false);
        panelRencanaButtons.add(btnHapus);
        panelRencanaButtons.add(btnSimpan);

        panelRencana.add(panelRencanaButtons, BorderLayout.SOUTH);
        panelBackground.add(panelRencana, BorderLayout.EAST);

        // Event: Mulai
        btnMulai.addActionListener(e -> {
            String nama = inputNama.getText();
            int anggaran;
            if (nama.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong.");
                return;
            }
            try {
                anggaran = Integer.parseInt(inputAnggaran.getText());
                if (anggaran <= 0) {
                    JOptionPane.showMessageDialog(this, "Anggaran harus lebih besar dari 0.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan anggaran yang valid.");
                return;
            }
            pengguna = new Pengguna(nama, anggaran);
            labelAnggaran.setText("Anggaran: Rp" + anggaran);

            // Load destinasi default
            destinasiModel.clear();
            loadDestinasi(kategoriComboBox.getSelectedItem().toString(), destinasiModel);
        });

        // Event: Pilih Kategori
        kategoriComboBox.addActionListener(e -> {
            if (pengguna == null) return;
            destinasiModel.clear();
            loadDestinasi(kategoriComboBox.getSelectedItem().toString(), destinasiModel);
        });

        // Event: Tambah ke Rencana
        btnTambah.addActionListener(e -> {
            String selected = listDestinasi.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Pilih destinasi terlebih dahulu.");
                return;
            }
            if (pengguna != null) {
                String[] parts = selected.split(" - Rp");
                String nama = parts[0];
                int harga = Integer.parseInt(parts[1]);
                pengguna.tambahDestinasi(new Destinasi(nama, "", harga, ""));
                rencanaModel.addElement(selected);
                updateTotalBiaya();
            }
        });

        // Event: Hapus Destinasi
        btnHapus.addActionListener(e -> {
            int selectedIndex = listRencana.getSelectedIndex();
            if (selectedIndex >= 0 && pengguna != null) {
                pengguna.hapusDestinasi(selectedIndex);
                rencanaModel.remove(selectedIndex);
                updateTotalBiaya();
            }
        });

        // Event: Simpan Rencana
        btnSimpan.addActionListener(e -> {
            if (pengguna == null) {
                JOptionPane.showMessageDialog(this, "Silakan mulai terlebih dahulu.");
                return;
            }
            try (FileWriter writer = new FileWriter("rencana_perjalanan.json")) {
                writer.write("{\n");
                writer.write("  \"nama\": \"" + pengguna.getNama() + "\",\n");
                writer.write("  \"anggaran\": " + pengguna.getAnggaran() + ",\n");
                writer.write("  \"rencana\": [\n");
                for (int i = 0; i < rencanaModel.size(); i++) {
                    writer.write("    { \"destinasi\": \"" + rencanaModel.getElementAt(i) + "\" }\n");
                    if (i < rencanaModel.size() - 1) {
                        writer.write(",\n");
                    }
                }
                writer.write("  ],\n");
                writer.write("  \"totalBiaya\": " + pengguna.getTotalBiaya() + "\n");
                writer.write("}\n");
                JOptionPane.showMessageDialog(this, "Rencana berhasil disimpan ke rencana_perjalanan.json");
            } catch (IOException ex) {
                logger.severe("Error menyimpan rencana: " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Gagal menyimpan rencana: " + ex.getMessage());
            }
        });

        // Menambahkan menu keluar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuKeluar = new JMenuItem("Keluar");
        menuKeluar.addActionListener(e -> System.exit(0));
        menuFile.add(menuKeluar);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
    }

    private void loadDestinasi(String kategori, DefaultListModel<String> model) {
        switch (kategori) {
            case "Wisata Alam":
                model.addElement("Pantai Parangtritis - Rp15000");
                model.addElement("Merapi Park - Rp20000");
                model.addElement("Heha Sky View - Rp10000");
                break;
            case "Wisata Budaya":
                model.addElement("Candi Borobudur - Rp50000");
                model.addElement("Taman Pintar - Rp40000");
                model.addElement("Agrowisata Bhumi Merapi - Rp30000");
                break;
            case "Wisata Kuliner":
                model.addElement("Sate Klathak Pak Pong - Rp30000");
                model.addElement("Gudeg Yu Djum - Rp25000");
                model.addElement("Bakpia Pathok 25 - Rp20000");
                break;
        }
    }

    private void updateTotalBiaya() {
        if (pengguna != null) {
            int total = pengguna.getTotalBiaya();
            labelTotalBiaya.setText("Total Biaya: Rp" + total);
            if (total > pengguna.getAnggaran()) {
                labelTotalBiaya.setForeground(Color.RED);
            } else {
                labelTotalBiaya.setForeground(Color.GREEN);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}
