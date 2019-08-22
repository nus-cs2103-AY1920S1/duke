import java.util.*;
public class Duke {
    /* Globals */
    private static TaskList tasklist;
    private static Parser parser;

    public static void init() {
        tasklist = new TaskList();
        parser = new Parser();
    }

    public static void main(String[] args) {
        Duke.init();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        runEvents();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds commands to lists and runs required commands
     */
    private static void runEvents() {
        tasklist = new TaskList();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        while (!command.toLowerCase().equals("bye")) {
            parser.executeCommand(tasklist, command);
            command = sc.nextLine().trim();
        }
    }
}
