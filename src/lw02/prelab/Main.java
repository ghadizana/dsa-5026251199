package lw02.prelab;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> transactions = new LinkedList<>();

        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> processes = new LinkedList<>();

        Stack<String[]> failedTransactions = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while(sc.hasNext()) {
            String name = sc.next();
            String type = sc.next();
            int amount = sc.nextInt();

            // memasukkan transaksi menjadi array string ke linkedlist
            transactions.add(new String[]{name, type, String.valueOf(amount)});

            // menandai nasabah udah terdaftar atau belum
            boolean exist = false;

            // cek nasabah udah terdaftar atau belum
            for(String[] cust : customers) {
                if (cust[0].equals(name)) {
                    exist = true;
                    break;
                }
            }

            // mendaftarkan nasabah baru dengan saldo 0
            if(!exist) {
                customers.add(new String[]{name, "0"});
            }
        }

        sc.close();

        // pindahin data dari linkedlist ke queue
        for(String[] trx : transactions) {
            processes.add(trx);
        }

        // proses transaksi dari antrean FIFO
        while(!processes.isEmpty()) {
            String[] trx = processes.poll();
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            // cari objek data nasabah yang bersangkutan di linkedlist customer
            String[] targetedCust = null;
            for(String[] cust : customers) {
                if(cust[0].equals(name)) {
                    targetedCust = cust;
                    break;
                }
            }

            // cek jika nasabah terdaftar
            if(targetedCust != null) {
                int currentBalance = Integer.parseInt(targetedCust[1]);

                if(type.equalsIgnoreCase("DEPOSIT")) {
                    currentBalance += amount;
                    targetedCust[1] = String.valueOf(currentBalance); // simpan saldo baru
                } else if(type.equalsIgnoreCase("WITHDRAW")) {
                    if(amount > currentBalance) { // cek apakah saldo mencukupi
                        failedTransactions.push(trx); // kalo gagal masukin ke stack
                    } else {
                        currentBalance -= amount;
                        targetedCust[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        // menampilkan transaksi berhasil berdasarkan urutan pertama kali terdaftar
        System.out.println("=== Final Balances ===");
        for(String[] cust : customers) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        // menampilkan transaksi gagal LIFO (yang gagal terakhir dicetak duluan atau descending)
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}