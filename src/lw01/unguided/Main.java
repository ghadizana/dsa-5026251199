package lw01.unguided;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        List<Rental> rentals = new ArrayList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("rental.txt"));

        while(sc.hasNext()) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            Rental rental;

            if (type.equals("Laptop")) {
                rental = new LaptopRental(id, days);
            } else {
                rental = new ProjectorRental(id, days);
            }

            rentals.add(rental);
        }

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }

        sc.close();
    }
}
