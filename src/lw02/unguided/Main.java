
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();

        LinkedList<String[]> stocks = new LinkedList<String[]>();

        LinkedList<String[]> borrowed = new LinkedList<>();

        Queue<String[]> processes = new LinkedList<>();

        Stack<String[]> failed = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        String[] kalkulus = {"Kalkulus", "2"};
        String[] fisika = {"Fisika", "1"};
        String[] statistika = {"Statistika", "2"};

        stocks.add(kalkulus);
        stocks.add(fisika);
        stocks.add(statistika);

        while (sc.hasNext()) {
            String name = sc.next();
            String title = sc.next();

            requests.add(new String[]{name, title});
        }

        sc.close();

        processes.addAll(requests);

        while (!processes.isEmpty()) {
            String[] request = processes.poll();

            String name = request[0];
            String title = request[1];

            String[] borrow = null;

            for (String[] data : borrowed) {
                if (data[0].equalsIgnoreCase(name)) {
                    borrow = data;
                    break;
                }
            }

            if (borrow == null) {
                borrow = new String[]{name, "0"};
                borrowed.add(borrow);
            }

            int sumBorrowed = Integer.parseInt(borrow[1]);
            int max = 2;
            boolean stock = false;
            boolean limit = false;

            for (int a = 0; a < stocks.size(); a++) {
                if (title.equalsIgnoreCase(stocks.get(a)[0])) {
                    if (Integer.parseInt(stocks.get(a)[1]) >= 1) {
                        stock = true;
                    }
                    break;
                }
            }

            for (int b = 0; b < borrowed.size(); b++) {
                if (name.equalsIgnoreCase(borrowed.get(b)[0])) {
                    int lim = Integer.parseInt(borrowed.get(b)[1]);
                    if (max > lim) {
                        limit = true;
                    }
                    break;
                }
            }

            if (stock && limit) {
                sumBorrowed += 1;
                borrow[1] = String.valueOf(sumBorrowed);
                int stockBalance;

                for (int c = 0; c < stocks.size(); c++) {
                    if (title.equalsIgnoreCase(stocks.get(c)[0])) {
                        stockBalance = Integer.parseInt(stocks.get(c)[1]) - 1;

                        stocks.get(c)[1] = String.valueOf(stockBalance);
                    }
                }
            } else {
                failed.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] data : requests) {
            if (!failed.contains(data)) {
                System.out.println(data[0] + " " + data[1]);

            }
        }

        System.out.println("=== Remaining Book Stock ===");
        for (int d = 0; d < stocks.size(); d++) {
            System.out.println(stocks.get(d)[0] + ": " + stocks.get(d)[1]);
        }

        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] failedReq = failed.pop();

            System.out.println(failedReq[0] + " " + failedReq[1]);
        }
    }
}
