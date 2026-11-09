import java.util.Scanner;

public class Main {

    // Method untuk memproses pesanan dengan format "Nama Menu = Jumlah"
    static void prosesPesananFormatString(Menu[] daftarMenu, Menu[] menuPesanan, int[] jumlahPesanan, int indeksPesanan, Scanner scanner) {
        System.out.println("Pesanan ke-" + (indeksPesanan + 1) + " (Format: Nama Menu = Jumlah, misal: Nasi Padang = 2, atau Tekan Enter untuk lewati):");
        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return;
        }

        // Menggunakan tanda "="untuk memisahkan input menjadi nama menu dan jumlah porsi 
        if (input.contains("=")) {
            String[] bagian = input.split("=");
            if (bagian.length == 2) {
                String namaInput = bagian[0].trim();
                int jumlahInput = 0;

                try {
                    jumlahInput = Integer.parseInt(bagian[1].trim());
                } catch (Exception e) {
                    jumlahInput = 0;
                }

                
                if (jumlahInput > 0) {
                    // validasi nama menu, serta menyimpan menu yang ditemukan ke dalam array menuPesanan
                    Menu menuDitemukan = cariMenuManual(daftarMenu, namaInput);
                    if (menuDitemukan != null) {
                        menuPesanan[indeksPesanan] = menuDitemukan;
                        jumlahPesanan[indeksPesanan] = jumlahInput;
                        System.out.println("  [+] Berhasil menambahkan: " + menuDitemukan.namaMenu + " x" + jumlahInput);
                    } else {
                        System.out.println("  [-] Menu '" + namaInput + "' tidak ditemukan dalam daftar menu.");
                    }
                } else {
                    System.out.println("  [-] Jumlah porsi harus berupa angka lebih dari 0.");
                }
            } else {
                System.out.println("  [-] Format salah! Gunakan format: Nama Menu = Jumlah");
            }
        } else {
            System.out.println("  [-] Format salah! Gunakan tanda '=' (misal: Nasi Padang = 2)");
        }
    }

    // Pencarian menu secara bertahap tanpa perulangan
    static Menu cariMenuManual(Menu[] daftarMenu, String nama) {
        if (daftarMenu[0].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[0];
        if (daftarMenu[1].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[1];
        if (daftarMenu[2].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[2];
        if (daftarMenu[3].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[3];
        if (daftarMenu[4].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[4];
        if (daftarMenu[5].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[5];
        if (daftarMenu[6].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[6];
        if (daftarMenu[7].namaMenu.equalsIgnoreCase(nama)) return daftarMenu[7];
        return null;
    }

    static int ambilInputAngka(Scanner scanner) {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return 0;
            return Integer.parseInt(input);
        } catch (Exception e) {
            return 0;
        }
    }

    static double cetakDetailPesanan(Menu menu, int jumlah) {
        if (menu == null || jumlah <= 0) return 0;
        double totalHargaPerItem = menu.harga * jumlah;
        System.out.printf("%-18s x%-3d = Rp %,10.0f\n", menu.namaMenu, jumlah, totalHargaPerItem);
        return totalHargaPerItem;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu[] daftarMenu = {
            new Menu("Nasi Padang", 25000, "Makanan"),
            new Menu("Nasi Goreng", 20000, "Makanan"),
            new Menu("Mie Jebew Pedas", 18000, "Makanan"),
            new Menu("Mie Bangladesh", 18000, "Makanan"),
            new Menu("Es Teh", 6000, "Minuman"),
            new Menu("Es Jeruk", 8000, "Minuman"),
            new Menu("Kopi Hitam", 10000, "Minuman"),
            new Menu("Kopi Susu", 12000, "Minuman"),
        };

        System.out.println("=========================================================================");
        System.out.println("=                             MENU RESTORAN                             =");
        System.out.println("=========================================================================");
        System.out.println("[ MAKANAN ]                               [ MINUMAN ]");
        System.out.printf("1. %-17s (Rp 25.000)    5. %-15s (Rp  6.000)\n", daftarMenu[0].namaMenu, daftarMenu[4].namaMenu);
        System.out.printf("2. %-17s (Rp 20.000)    6. %-15s (Rp  8.000)\n", daftarMenu[1].namaMenu, daftarMenu[5].namaMenu);
        System.out.printf("3. %-17s (Rp 18.000)    7. %-15s (Rp 10.000)\n", daftarMenu[2].namaMenu, daftarMenu[6].namaMenu);
        System.out.printf("4. %-17s (Rp 18.000)    8. %-15s (Rp 12.000)\n", daftarMenu[3].namaMenu, daftarMenu[7].namaMenu);
        System.out.println("=========================================================================\n");

        Menu[] menuPesanan = new Menu[4];
        int[] jumlahPesanan = new int[4];

        // Menjalankan proses input pesanan sebanyak 4 kali secara bertahap
        prosesPesananFormatString(daftarMenu, menuPesanan, jumlahPesanan, 0, scanner);
        prosesPesananFormatString(daftarMenu, menuPesanan, jumlahPesanan, 1, scanner);
        prosesPesananFormatString(daftarMenu, menuPesanan, jumlahPesanan, 2, scanner);
        prosesPesananFormatString(daftarMenu, menuPesanan, jumlahPesanan, 3, scanner);

        // Penjumlahan subtotal secara bertahap
        double subtotal = 0;
        if (menuPesanan[0] != null) subtotal += menuPesanan[0].harga * jumlahPesanan[0];
        if (menuPesanan[1] != null) subtotal += menuPesanan[1].harga * jumlahPesanan[1];
        if (menuPesanan[2] != null) subtotal += menuPesanan[2].harga * jumlahPesanan[2];
        if (menuPesanan[3] != null) subtotal += menuPesanan[3].harga * jumlahPesanan[3];

        double pajak = subtotal * 0.10;
        double biayaLayanan = 8000;
        double totalSebelumDiskon = subtotal + pajak + biayaLayanan;

        double potonganMinumanGratis = 0;
        double nominalDiskon = 0;
        Menu minumanGratis = null;

        // Validasi apakah ada pesanan minuman di antara pesanan yang dipesan dan pengecekan apakah memenuhi syarat promo minuman gratis
        boolean adaBeliMinuman = 
            (menuPesanan[0] != null && menuPesanan[0].KategoriMenu.equalsIgnoreCase("Minuman")) ||
            (menuPesanan[1] != null && menuPesanan[1].KategoriMenu.equalsIgnoreCase("Minuman")) ||
            (menuPesanan[2] != null && menuPesanan[2].KategoriMenu.equalsIgnoreCase("Minuman")) ||
            (menuPesanan[3] != null && menuPesanan[3].KategoriMenu.equalsIgnoreCase("Minuman"));

        System.out.println("\n====================== INFO PROMO & DISKON ============================");
        boolean dapatPromo = false;

        if (subtotal > 50000 && adaBeliMinuman) {
            System.out.println("Selamat! Karena total belanja melebihi Rp 50.000 dan Anda memesan minuman,");
            System.out.println("              Anda berhak mendapatkan 1 Minuman Gratis!");
            System.out.print("Masukkan nama minuman gratis pilihan Anda! (misal: Es Teh/Es Jeruk/Kopi Hitam/ Kopi Susu): ");
            String namaBonus = scanner.nextLine().trim();
            
            Menu bonusPilihan = cariMenuManual(daftarMenu, namaBonus);
            if (bonusPilihan != null && bonusPilihan.KategoriMenu.equalsIgnoreCase("Minuman")) {
                minumanGratis = bonusPilihan;
                potonganMinumanGratis = minumanGratis.harga;
                System.out.println("-> Bonus: " + minumanGratis.namaMenu + " berhasil ditambahkan!");
            } else {
                System.out.println("-> Pilihan minuman gratis tidak valid atau bukan kategori minuman.");
            }
            dapatPromo = true;
        }

        // validasi apakah subtotal lebih dari 100.000 untuk mendapatkan diskon 10%
        if (subtotal > 100000) {
            nominalDiskon = subtotal * 0.10;
            System.out.println("  Wah, Selamat ya! total belanja Anda di atas Rp 100.000. ");
            System.out.println("Anda berhak mendapatkan Diskon Potongan Harga sebesar 10%!");
            dapatPromo = true;
        }

        if (!dapatPromo) {
            System.out.println("             Sayang sekali, Anda belum memenuhi syarat.                 ");
            System.out.println("Silakan tingkatkan total transaksi Anda untuk memperoleh potongan harga.");
        }

        double totalAkhir = totalSebelumDiskon - potonganMinumanGratis - nominalDiskon;

        System.out.println("\n========================= STRUK PESANAN ===============================");
        System.out.println("Daftar Barang Yang Dibeli : ");
        cetakDetailPesanan(menuPesanan[0], jumlahPesanan[0]);
        cetakDetailPesanan(menuPesanan[1], jumlahPesanan[1]);
        cetakDetailPesanan(menuPesanan[2], jumlahPesanan[2]);
        cetakDetailPesanan(menuPesanan[3], jumlahPesanan[3]);

        if (minumanGratis != null) {
            System.out.printf("%-18s x1   = Rp %,10.0f  (GRATIS)\n", minumanGratis.namaMenu, minumanGratis.harga);
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("Subtotal      : Rp %,.0f\n", subtotal);
        System.out.printf("Pajak (10%%)   : Rp %,.0f\n", pajak);
        System.out.printf("Biaya Layanan : Rp %,.0f\n", biayaLayanan);
        if (potonganMinumanGratis > 0) System.out.printf("Promo Minuman : -Rp %,.0f\n", potonganMinumanGratis);
        if (nominalDiskon > 0) System.out.printf("Diskon 10%%    : -Rp %,.0f\n", nominalDiskon);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("GRAND TOTAL   : Rp %,.0f\n", totalAkhir);
        System.out.println("--------------------------------------------------------------------");

        System.out.print("Uang Bayar    : Rp ");
        double uangBayar = ambilInputAngka(scanner);

        if (uangBayar >= totalAkhir) {
            System.out.printf("KEMBALIAN     : Rp %,.0f\n", (uangBayar - totalAkhir));
        } else {
            System.out.printf("KEKURANGAN    : Rp %,.0f\n", (totalAkhir - uangBayar));
        }
    }
}