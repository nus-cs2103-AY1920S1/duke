import java.util.*;

public class Duke {

    public static void runDuke() {
        String line = "    _____________________________________________";
        String currentCommand = "";

        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (!currentCommand.equals("bye")) {
            currentCommand = scanner.nextLine();
            if (!currentCommand.equals("bye")) {
                System.out.println(line);
                System.out.println("     " + currentCommand);
                System.out.println(line);
            }
        }

        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        runDuke();
    }
}
