import java.util.Optional;
import java.util.Scanner;

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
        UI.printWelcomeMessage();
        runEvents();
        UI.printGoodbyeMessage();
        DataStorage.storeTaskList(taskList);
    }

    /**
     * Adds commands to lists and runs required commands
     */
    private static void runEvents() {
        Scanner sc = new Scanner(System.in);
        String inputCommand = sc.nextLine().trim();
        while (!inputCommand.toLowerCase().equals("bye")) {
            Optional<Command> fullCommand = parser.executeCommand(taskList, inputCommand);
            if(fullCommand.isPresent()) { fullCommand.get().execute(taskList); }
            inputCommand = sc.nextLine().trim();
        }
    }
}
