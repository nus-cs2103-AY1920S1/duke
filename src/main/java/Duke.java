import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        printWelcomeMsg();
        String command = sc.nextLine();

        while (!command.equals("")) {
            printHorizontalLine();
            if (command.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            } else {
                System.out.println("     " + command);
                printHorizontalLine();
            }
            command = sc.nextLine();
        }

        sc.close();
    }

    private static void printWelcomeMsg() {

        printHorizontalLine();
        System.out.println("     Hello! I'm Duke\n" + "     What can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
