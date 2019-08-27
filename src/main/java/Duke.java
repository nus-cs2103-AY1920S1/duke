import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        final String LINE = "____________________________________________________________";
        final String TAB = "    ";
        String cmd;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        printDuke(LINE);
        printDuke("Hello! I'm Duke");
        printDuke("What can I do for you?");
        printDuke(LINE);

        while(true) {
            cmd = scan.nextLine();
            if (cmd.equals("bye")) {
                printDuke(LINE);
                printDuke("Bye. Hope to see you again soon!");
                printDuke(LINE);
                break;
            }
            if (cmd.equals("list")) {
                printDuke(LINE);
                printDuke(list);
                printDuke(LINE);
            } else {
                list.add(cmd);
                printDuke(LINE);
                System.out.println(TAB + "added: " + cmd);
                printDuke(LINE);
            }
        }
    }

    public static void printDuke(String toPrint) {
        System.out.println("    " + toPrint);
    }

    public static void printDuke(ArrayList toPrint) {
        for (int i = 1; i <= toPrint.size(); i++) {
            System.out.println("    " + i + ". " + toPrint.get(i - 1));
        }
    }
}