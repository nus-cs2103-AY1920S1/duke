import java.util.Scanner;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String line = line();
        String exit = "Bye. Hope to see you again soon!";
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.printf("    %s%n    %s%n    %s%n", line, input, line);
            input = sc.nextLine();
        }

        System.out.printf("    %s%n    %s%n    %s%n", line, exit, line);
    }

    public static String line() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 35; i++) {
            line.append("_");
        }

        String stringLine = line.toString();
        return stringLine;
    }

    public static void greet() {
        String line = line();
        String greeting = "Hello! I'm Duke\n" +
                "    What can I do for you?";

        System.out.printf("    %s%n    %s%n    %s%n", line, greeting, line);
    }
}