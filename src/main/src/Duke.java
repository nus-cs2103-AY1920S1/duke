import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String cmd;
        Scanner scan = new Scanner(System.in);

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        String line = "____________________________________________________________";
        String tab = "    ";

        printDuke(line);
        printDuke("Hello! I'm Duke");
        printDuke("What can I do for you?");
        printDuke(line);

        while(true) {
            cmd = scan.next();
            if (cmd.equals("bye")) {
                printDuke(line);
                printDuke("Bye. Hope to see you again soon!");
                printDuke(line);
                break;
            }
            printDuke(line);
            printDuke(cmd);
            printDuke(line);
        }
    }

    public static void printDuke(String toPrint) {
        System.out.println("    " + toPrint);
    }
}