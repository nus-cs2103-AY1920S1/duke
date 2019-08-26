import java.util.*;
public class Duke {
    /* Globals */
    private static TaskList taskList;
    private static Parser parser;

    public static void init() {
        taskList = DataStorage.getStoredTaskList();
        parser = new Parser();
    }

    public static void main(String[] args) {
        Duke.init();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        runEvents();
        System.out.println("Bye. Hope to see you again soon!");
        DataStorage.storeTaskList(taskList);
    }

    /**
     * Adds commands to lists and runs required commands
     */
    private static void runEvents() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        while (!command.toLowerCase().equals("bye")) {
            parser.executeCommand(taskList, command);
            command = sc.nextLine().trim();
        }
    }
}
