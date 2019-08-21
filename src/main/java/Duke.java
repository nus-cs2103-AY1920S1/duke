import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();

        printLogo();
        printWelcome();

        while (true) {
            if (sc.hasNextLine()) {
                String command = sc.nextLine();
                switch (command) {
                    case "bye":
                        printBye();
                        return;
                    case "list":
                        printList(lst);
                        break;
                    default:
                        lst.add(command);
                        printAddition(command);
                        break;
                }
            }
        }
    }

    private static void printIndent() {
        System.out.print("\t");
    }

    private static void printIndentWSpace() {
        System.out.print("\t ");
    }

    private static void printTopLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printBotLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printWelcome() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Hello! I'm Duke");
        printIndentWSpace();
        System.out.println("What can I do for you?");
        printBotLine();
    }

    private static void printBye() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Bye. Hope to see you again soon!");
        printBotLine();
    }

    private static void printMessage(String msg) {
        printTopLine();
        printIndentWSpace();
        System.out.println(msg);
        printBotLine();
    }

    private static void printAddition(String msg) {
        printMessage(("added: " + msg));
    }

    private static void printList(ArrayList<String> list) {
        int count = 1;
        printTopLine();
        for(String item: list) {
            printIndentWSpace();
            System.out.println(count + ". " + item);
            count++;
        }
        printBotLine();
    }
}
