import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> inputList = new LinkedList<>();

        greet();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                printLine();

                printIndent();
                System.out.println("added: " + input);

                printLine();

                inputList.add(input);

                input = sc.nextLine();
            } else {
                printList(inputList);

                input = sc.nextLine();
            }
        }

        exit();
    }

    public static echo(String input) {
        printLine();

        System.out.println()
    }

    public static void printLine() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 60; i++) {
            line.append("_");
        }

        String stringLine = line.toString();
        printIndent();
        System.out.println(stringLine);
    }

    public static void printIndent() {
        System.out.print("    ");
    }

    public static void greet() {
        String greeting = "Hello! I'm Duke\n" +
                "    What can I do for you?";

        printLine();

        printIndent();
        System.out.println(greeting);

        printLine();
    }

    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon";

        printLine();

        printIndent();
        System.out.println(exitMessage);

        printLine();
    }

    public static void printList(LinkedList<String> stringList) {
        ListIterator<String> iter = stringList.listIterator();
        int count = 1;

        printLine();

        while (iter.hasNext()) {
            printIndent();
            System.out.println(count + ". " + iter.next());
            count++;
        }

        printLine();
    }
}