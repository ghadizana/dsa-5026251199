package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner playlist = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        Scanner participants = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Scanner inventory = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        managePlaylist(playlist);
        manageParticipants(participants);
        manageInventory(inventory);
    }

    private static void managePlaylist(Scanner playlist) {
        List<String> songs = new ArrayList<>();

        while (playlist.hasNext()) {
            String line = playlist.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String opr = parts[0];

            if (opr.equals("ADD")) {
                String song = parts[1];
                songs.add(song);
            } else if (opr.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(insertParts[0]);
                String song = insertParts[1];
                if (index >= 0 && index <= songs.size()) {
                    songs.add(index, song);
                }
            } else if(opr.equals("REMOVE")){
                String song = parts[1];
                songs.remove(song);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + songs.size());
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ": " + songs.get(i));
        }
    }

    private static void manageParticipants(Scanner participants) {
        Set<String> uniqueParticipants = new LinkedHashSet<>();

        int duplicate = 0;

        while (participants.hasNextLine()) {
            String name = participants.nextLine().trim();

            if (name.isEmpty()) {
                continue;
            }

            if (uniqueParticipants.contains(name)) {
                duplicate++;
            } else {
                uniqueParticipants.add(name);
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + uniqueParticipants.size());
        int index = 1;
        for (String participant : uniqueParticipants) {
            System.out.println(index + ". " + participant);
            index++;
        }
        System.out.println("Duplicate registrations: " + duplicate);

    }

    private static void manageInventory(Scanner inventory) {
        Map<String, Integer> stockInventory = new LinkedHashMap<>();
        int failed = 0;

        while (inventory.hasNext()) {
            String type = inventory.next();
            String product = inventory.next();
            int quantity = inventory.nextInt();

            if (type.equalsIgnoreCase("ADD")) {
                if (stockInventory.containsKey(product)) {
                    int currentStock = stockInventory.get(product);
                    stockInventory.put(product, currentStock + quantity);
                } else {
                    stockInventory.put(product, quantity);
                }
            } else if (type.equalsIgnoreCase("SELL")) {
                if (stockInventory.containsKey(product)) {
                    int currentStock = stockInventory.get(product);

                    if (currentStock >= quantity) {
                        stockInventory.put(product, currentStock - quantity);
                    } else {
                        failed++;
                    }
                } else {
                    failed++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : stockInventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failed);

    }
}
