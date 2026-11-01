package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int branch = sc.nextInt();

        int[] branches = new int[branch];

        for (int i = 0; i < branch; i++) {
            branches[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        int[] targets = new int[target];

        for (int i = 0; i < target; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        insertionSort(branches, 3);

        for (int i = 0; i < branches.length; i++) {
            if (i>0) {
                System.out.print(" ");
            }

            System.out.print(branches[i]);
        }

        System.out.println();

        for (int i = 0; i < targets.length; i++) {
            int index = linearSearch(branches, targets[i]);

            if(index != -1) {
                System.out.println(targets[i] + ": FOUND at index " + index);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }

    public static void insertionSort(int[] branches, int passes) {
        for(int i =1; i <= passes; i++) {
            int key = branches[i];

            int j = i - 1;

            while(j >= 0 && branches[j] < key) {
                branches[j+1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }
    }

    public static int linearSearch(int[] branches, int target) {
        for (int i = 0; i < branches.length; i++) {
            if(branches[i] == target) {
                return i;
            }
        }

        return -1;
    }
}