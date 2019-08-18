import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("bye")) {
                exit();
                scanner.close();
                break;
            } else {
                echo(command);
            }
        }
    }

    public static void greetUser() {
        printLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printLine();
    }

    public static void echo(String command) {
        printLine();
        System.out.println("\t " + command);
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t -------------------------------------");
    }

}
