package duke;

import duke.command.Command;
import duke.command.CommandHistoryStack;
import duke.command.UnknownCommandException;
import duke.parser.ParserManager;
import duke.task.TaskList;
import duke.ui.MainWindow;

public class Duke {
    private static TaskList taskList;
    private static ParserManager parserManager;
    private static DataStorage dataStorage;
    private static MainWindow mainWindow;
    private static CommandHistoryStack commandHistory;

    public Duke(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        Duke.initialize();
        mainWindow.display(Ui.getWelcomeMessage());
    }

    /**
     * Initialize static variables
     */
    public static void initialize() {
        dataStorage = new DataStorage();
        taskList = dataStorage.getStoredTaskList();
        parserManager = new ParserManager();
        commandHistory = new CommandHistoryStack();
    }

    /**
     * Adds commands to lists and runs executes commands
     */
    public static String getResponse(String input) {
        if(!input.trim().toLowerCase().equals("bye")) {
            try {
                Command command = parserManager.parseCommand(taskList, input.trim(), commandHistory);
                assert command != null;
                String response = command.execute(taskList);
                dataStorage.storeTaskList(taskList);
                return response;
            } catch (UnknownCommandException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException e) {
                return Ui.getExceedListMessage(taskList.size());
            } catch (NumberFormatException e) {
                return Ui.getInvalidStatementMessage(input);
            }
        }
        return Ui.getGoodbyeMessage();
    }
}
