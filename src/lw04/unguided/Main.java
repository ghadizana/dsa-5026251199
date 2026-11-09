package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] req = new int[m];

        for (int i = 0; i < m; i++) {
            req[i] = sc.nextInt();
        }

        sc.close();

        sortCards(cards, 2);

        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i]);

            if (i < cards.length - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < m; i++) {
            int t = req[i];
            int index = findCard(cards, t);

            if(index != -1) {
                System.out.println(t + ": FOUND at index " + index);
            } else {
                System.out.println(t + ": NOT FOUND");
            }
        }
    }

    static void sortCards(int[] arr, int passes) {
        int n = arr.length;
        for (int i = 0; i < passes && i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }

        return -1;
    }
}
