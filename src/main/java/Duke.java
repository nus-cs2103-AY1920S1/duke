import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";

        printFormattedText("Hello! I'm Duke\n" +
                "    What can I do for you?");

        while (!input.equals("bye")) {
            input = sc.next();
            if (input.equals("bye")) break;
            printFormattedText(input);
        }

        printFormattedText("Bye. Hope to see you again soon!");
    }

    private static void printFormattedText(String text) {
        printDivider();
        indent();
        System.out.println(text);
        printDivider();
    }

    private static void printDivider() {
        indent();
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static void indent() {
        for (int i = 0; i < 4; i++) System.out.print(" ");
    }
}
