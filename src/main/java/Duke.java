import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {

    static TaskList taskList = new TaskList();
    static Scanner scanner = new Scanner(System.in);
    static PrintStream ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    static String[] validTaskTypes = new String[] {"deadline", "event", "todo"};
    /**
     * Main program loop of the program.
     * @param args Unused args.
     */
    public static void main(String[] args) {
        welcomeMessage();
        String command = "";

        while (!command.equals("bye")) {
            try {
                command = scanner.nextLine();
                ps.println(command);
                ps.println("\t____________________________________________________________");
                processCommand(command);
            } catch (DukeException e) {
                ps.println(e.getMessage());
                ps.println("\t____________________________________________________________");
            }
        }
        scanner.close();
        System.exit(1);
    }

    /**
     * Processes the user input and outputs a response.
     * @param command The user's input.
     */
    public static void processCommand(String command) throws DukeException {
        String[] commandArray = command.split(" ");
        if (command.equals("bye")) {
            ps.println("\tBye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            taskList.printList();
        } else if (commandArray[0].equals("done") && commandArray[1].matches("\\d+")) {
            int id = Integer.parseInt(commandArray[1]);
            taskList.markAsDone(id);
        } else if (isValidTaskType(commandArray[0])){
            if (command.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            taskList.addTask(command);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
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

    public static boolean isValidTaskType(String taskType) {
        for (String s : validTaskTypes) {
            if (taskType.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
