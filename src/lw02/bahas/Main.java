package lw02.bahas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedTransaction = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (sc.hasNext()) {
            String[] transaction = new String[3];
            transaction[0] = sc.next();
            transaction[1] = sc.next();
            transaction[2] = sc.next();
            transactions.add(transaction);
        }

        sc.close();

        queue.addAll(transactions);

        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;

            for (String[] data : customers) {
                if (data[0].equalsIgnoreCase(name)) {
                    customer = data;
                    break;
                }
            }

            if(customer == null) {
                customer = new String[]{name, "0"};
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equalsIgnoreCase("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equalsIgnoreCase("WITHDRAW")) {
                if (amount <= balance) {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                } else {
                    failedTransaction.push(transaction);
                }
            }
        }

        System.out.println("=== Final Balance ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println("=== Failed Transaction ===");
        while (!failedTransaction.isEmpty()) {
            String[] transaction = failedTransaction.pop();

            System.out.println(
                    transaction[0] + " " + transaction[1] + " " + transaction[2]
            );
        }
    }
}
