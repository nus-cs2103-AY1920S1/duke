import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<String> items = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (scanner.hasNext()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                printExitMessage();
                break;
            } else if (command.equals("list")) {
                printItems();
                continue;
            }

            addItem(command);
        }
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printSeparator();
        System.out.println();
    }

    public static void printExitMessage() {
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
        System.out.println();
    }

    public static void addItem(String item) {
        if (item.isEmpty()) {
            return;
        }

        items.add(item);

        printSeparator();
        System.out.println(" added: " + item);
        printSeparator();
        System.out.println();
    }

    public static void printItems() {
        printSeparator();
        for (int i = 0; i < items.size(); i++) {
            String output = String.format(" %d. %s", i + 1, items.get(i));
            System.out.println(output);
        }
        printSeparator();
        System.out.println();
    }
}
