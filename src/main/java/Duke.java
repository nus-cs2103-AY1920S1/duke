import duke.command.Command;
import duke.parser.ParserManager;
import duke.task.TaskList;

import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private static TaskList taskList;
    private static ParserManager parserManager;

    /**
     * Initialize static variables
     */
    public static void init() {
        taskList = DataStorage.getStoredTaskList();
        parserManager = new ParserManager();
    }

    public static void main(String[] args) {
        Duke.init();
        UI.printWelcomeMessage();
        runEvents();
        UI.printGoodbyeMessage();
        DataStorage.storeTaskList(taskList);
    }

    /**
     * Adds commands to lists and runs executes commands
     */
    private static void runEvents() {
        Scanner sc = new Scanner(System.in);
        String inputCommand = sc.nextLine().trim();
        while (!inputCommand.toLowerCase().equals("bye")) {
            Optional<Command> fullCommand = parserManager.parseCommand(taskList, inputCommand);
            fullCommand.ifPresent(command -> command.execute(taskList));
            inputCommand = sc.nextLine().trim();
        }
    }
}
