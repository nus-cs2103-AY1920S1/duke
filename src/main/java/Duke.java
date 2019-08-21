import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {

    static TaskList taskList = new TaskList();
    static Scanner scanner = new Scanner(System.in);
    static PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Main program loop of the program.
     * @param args Unused args.
     */
    public static void main(String[] args) {
        welcomeMessage();
        String command = "";

        while (!command.equals("bye")) {
            command = scanner.nextLine();
            processCommand(command);
        }
        scanner.close();
        System.exit(1);
    }

    /**
     * Processes the user input and outputs a response.
     * @param command The user's input.
     */
    public static void processCommand(String command) {
        String[] commandArray = command.split(" ");
        ps.println("\t____________________________________________________________");
        if (command.equals("bye")) {
            ps.println("\tBye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            taskList.printList();
        } else if (commandArray[0].equals("done") && commandArray[1].matches("\\d+")) {
            int id = Integer.parseInt(commandArray[1]);
            taskList.markAsDone(id);
        } else {
            taskList.add(command);
        }
        ps.println("\t____________________________________________________________");
    }

    /**
     * Displays the welcome message when the user starts the program.
     */
    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ps.println("Hello from\n" + logo);
        ps.println("\t____________________________________________________________");
        ps.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        ps.println("\t____________________________________________________________");
    }
}
