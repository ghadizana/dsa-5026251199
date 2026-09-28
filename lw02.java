import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        LinkedList<String[]> stokBuku = new LinkedList<String[]>();
        LinkedList<String[]> permintaan = new LinkedList<String[]>();
        LinkedList<String[]> dataAnggota = new LinkedList<String[]>();
        Queue<String[]> prosesPermintaan = new LinkedList<String[]>();
        Queue<String[]> sukses = new LinkedList<String[]>();
        Stack<String[]> gagal = new Stack<String[]>();

        String[] kalkulus = {"Kalkulus", "2"};
        String[] Fisika = {"Fisika", "2"};
        String[] Statistika = {"Statistika", "2"};

        stokBuku.add(kalkulus);
        stokBuku.add(Fisika);
        stokBuku.add(Statistika);

        while(input.hasNext()){
            String peminjam = input.next();
            String judulBuku = input.next();
            String[] dataPermintaan = {peminjam, judulBuku};

            permintaan.add(dataPermintaan);
        }

        input.close();

        prosesPermintaan.addAll(permintaan);

        while(!prosesPermintaan.isEmpty()){
            String[] dataSekarang = prosesPermintaan.poll();
            String namaPeminjam = dataSekarang[0];
            String buku = dataSekarang[1];

            String[] anggota = null;

            for(String[] data : dataAnggota){
                if(data[0].equals(namaPeminjam)){
                    anggota = data;
                    break;
                }
            }

            if(anggota == null){
                anggota = new String[]{namaPeminjam, "0"};
                dataAnggota.add(anggota);
            }

            int jumlahPinjam = Integer.parseInt(anggota[1]);
            int max = 2;
            boolean kondisi1 = false;
            boolean kondisi2 = false;

            for(int i = 0; i < stokBuku.size(); i++){
                if(buku.equals(stokBuku.get(i)[0])){
                    if(Integer.parseInt(stokBuku.get(i)[1]) >= 1){
                        kondisi1 = true;
                    }
                    break;
                }

            
            }

            for(int j = 0; j < dataAnggota.size(); j++){
                if(namaPeminjam.equals(dataAnggota.get(j)[0])){
                    int batas = Integer.parseInt(dataAnggota.get(j)[1]);
                    if(max > batas){
                        kondisi2 = true;
                    }
                    break;
                }

            
            }

            if(kondisi1 && kondisi2){
                jumlahPinjam += 1;
                anggota[1] = String.valueOf(jumlahPinjam);
                int sisaStok;

                for(int i = 0; i < stokBuku.size(); i++){
                if(buku.equals(stokBuku.get(i)[0])){
                   sisaStok = Integer.parseInt(stokBuku.get(i)[1]) - 1;
                   
                   stokBuku.get(i)[1] = String.valueOf(sisaStok);
                }

                sukses.add(dataSekarang);
            }
            }else{
                gagal.push(dataSekarang);
            }


        }

        System.out.println("=== Successfully Processed Requests ===");
        while(!sukses.isEmpty()){
            String[] sukses2 = sukses.poll();
            
            System.out.println(sukses2[0] + " " + sukses2[1]);
        }

        System.out.println();

        System.out.println("=== Remaining Book Stock ===");
        for(int b = 0; b < stokBuku.size(); b++){
            System.out.println(stokBuku.get(b)[0] + " : " + stokBuku.get(b)[1]);
        }

        System.out.println();

        System.out.println("=== Failed Requests ===");
        while(!gagal.isEmpty()){
            String[] gagal2 = gagal.pop();
            
            System.out.println(gagal2[0] + " " + gagal2[1]);
        }


    }
}