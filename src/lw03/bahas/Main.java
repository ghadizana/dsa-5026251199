package lw03.bahas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();

        int failed = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if(type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if(type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock - quantity);
                } else {
                    failed++;
                }
            } else {
                failed++;
            }
        }

        input.close();

        System.out.println("===== Problem 3 =====");

        for (String product: inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failed);

        // manageInventory(input);
    }

    private static void manageInventory(Scanner input) {
        Map<String, Integer> inventory = new LinkedHashMap<>();

        int failed = 0;

        while (input.hasNextLine()) {
            String type = input.next();
            String product = input.next();
            int quantity = input.nextInt();

            if (type.equalsIgnoreCase("ADD")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, (currentStock + quantity));
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equalsIgnoreCase("SELL")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);

                    if (currentStock >= quantity) {
                        inventory.put(product, (currentStock - quantity));
                    } else {
                        failed++;
                    }
                } else {
                    failed++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failed);
    }
}
