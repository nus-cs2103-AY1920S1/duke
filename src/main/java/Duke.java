import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        ArrayList<String> tasks = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
                scanner.close();
                break;
            } else if (command.equals("list")) {
                list(tasks);
            } else {
                echo(command, tasks);
            }
        }
    }

    public static void greetUser() {
        printLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printLine();
    }

    public static void list(ArrayList<String> tasks) {
        printLine();
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t " + i + ". " + tasks.get(i - 1));
        }
        printLine();
    }

    public static void echo(String command, ArrayList<String> tasks) {
        printLine();
        System.out.println("\t added: " + command);
        tasks.add(command);
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
