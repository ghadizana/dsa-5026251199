package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        List<Rental> rentals = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("lw01/unguided/rental.txt"))) {
            int total = sc.nextInt();
            
            for (int i = 0; i < total; i++) {
                String type = sc.next();
                String id = sc.next();
                int days = sc.nextInt();
                int units = sc.nextInt();
                
                Rental rental;
                
                if (type.equalsIgnoreCase("Laptop")) {
                    rental = new LaptopRental(id, days);
                } else {
                    rental = new ProjectorRental(id, days);
                }
                
                rentals.add(rental);
            }
            
            for (Rental rental : rentals) {
                System.out.println(rental.summary());
            }
        }
    }
}
