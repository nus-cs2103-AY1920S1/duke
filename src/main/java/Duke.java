import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> list = new LinkedList<>();
        String command;
        printHello();

        while (!( command = sc.nextLine()).equals("bye")) {
            if (command.equals("list")) {
                System.out.println("    _____________________________________");
                for (int i = 0; i < list.size(); i++) {
                    int number = i + 1;
                    System.out.println("     " + number + ". " + list.get(i));
                }
                System.out.println("    _____________________________________");
            } else {
                list.add(command);
                printAddItem(command);
            }
        }
        printBye();
    }
    public static void printHello() {
        System.out.println("    _____________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    _____________________________________\n");
    }

    public static void printBye() {
        System.out.println("    _____________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________");
    }

    public static void printAddItem(String item) {
        System.out.println("    _____________________________________");
        System.out.println("     added: " + item);
        System.out.println("    _____________________________________\n");
    }
}
