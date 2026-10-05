package lw03.unguided;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollments = new LinkedHashMap<>();
        Map<String, String> checks = new LinkedHashMap<>();

        int rejected = 0;

        while (input.hasNextLine()) {
            String type = input.next();
            String course = input.next();
            int count;

            if (type.equals("REGISTER")) {
                count = input.nextInt();
                if (!enrollments.containsKey(course)) {
                    enrollments.put(course, count);
                } else {
                    int currentEnrollment = enrollments.get(course);
                    enrollments.put(course, currentEnrollment + count);
                }
            } else if (type.equals("WITHDRAW")) {
                count = input.nextInt();

                if (enrollments.containsKey(course) && enrollments.get(course) >= count) {
                    int currentEnrollment = enrollments.get(course);
                    enrollments.put(course, currentEnrollment - count);
                } else {
                    rejected++;
                }
            } else if (type.equals("CHECK")) {
                if (enrollments.containsKey(course)) {
                    checks.put(course, String.valueOf(enrollments.get(course) + " students"));
                } else {
                    checks.put(course, "Not Found");
                    rejected++;
                }
            } else {
                rejected++;
            }
        }

        System.out.println("===== Enrollment Checks =====");
        for (String course : checks.keySet()) {
            System.out.println(course + ": " + checks.get(course));
        }

        System.out.println();

        System.out.println("===== Final Enrollment =====");
        for (String course: enrollments.keySet()) {
            System.out.println(course + ": " + enrollments.get(course) + " students");
        }

        System.out.println();

        System.out.println("Rejected operations: " + rejected);
    }
}
