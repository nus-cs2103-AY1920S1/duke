import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();
        while (sc.hasNext()){
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit(command);
                break;
            } else {
                echo(command);
            }
        }

        sc.close();
    }

    //Greet the user when starting up Duke
    private static void greet() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
        System.out.println();
    }

    // Echo commands entered by users
    private static void echo(String command) {
        printLine();
        System.out.println("     " + command);
        printLine();
        System.out.println();
    }

    //Exits when the user types bye
    private static void exit(String command) {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    // Print indented line
    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
