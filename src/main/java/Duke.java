import java.util.*;
public class Duke {
    /* Globals */
    private static TaskList tasklist;
    private static LogicManager logicManager;

    public static void init() {
        tasklist = new TaskList();
        logicManager = new LogicManager();
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
    static void runEvents() {
        tasklist = new TaskList();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        while (!command.equals("bye")) {
            logicManager.executeCommand(tasklist, command);
            command = sc.nextLine().trim();
        }
    }
}
