import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        printWelcomeMsg();

        String[] list = new String[100];
        Arrays.fill(list, "");
        int count = 0;
        String command = sc.nextLine();

        while (!command.equals("")) {
            printHorizontalLine();
            if (command.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (!list[i].equals("")) {
                        System.out.println("     " + (i+1) + ". " + list[i]);
                    } else {
                        break;
                    }
                }
                printHorizontalLine();
            } else {
                list[count] = command;
                count++;
                System.out.println("     added: " + command);
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
