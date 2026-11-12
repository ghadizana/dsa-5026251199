package lw04.bahas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int branch = sc.nextInt();

        int[] branches = new int[branch];

        for(int i = 0; i < branch; i++) {
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
            if(i < branch - 1) {
                System.out.println(" ");
            }

            System.out.print(branches[i]);
        }

        System.out.println();

        for(int t : targets) {
            int index = linearSearch(branches, t);

            if(index != -1) {
                System.out.println(t + ": FOUND at index " + index);
            } else {
                System.out.println(t + ": NOT FOUND");
            }
        }
    }

    static void insertionSort(int[] arr, int passes) {
        for (int i = 1; i < passes && i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;

            while(j >= 0 && arr[j] < key) {
                arr[j+1] = arr[j];
                j--;
            }

            arr[j+1] = key;
        }
    }

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }

        return -1;
    }
}
 