package duke;

import duke.command.Command;
import duke.command.CommandHistoryStack;
import duke.command.UnknownCommandException;
import duke.parser.ParserManager;
import duke.task.TaskList;
import duke.ui.MainWindow;

public class Duke {
    private TaskList taskList;
    private static ParserManager parserManager;
    private static DataStorage dataStorage;
    private static MainWindow mainWindow;
    private static CommandHistoryStack commandHistory;

    public Duke(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initialize();
        mainWindow.display(Ui.getWelcomeMessage());
        commandHistory = new CommandHistoryStack();
    }

    /**
     * Initialize static variables
     */
    public void initialize() {
        dataStorage = new DataStorage();
        this.taskList = dataStorage.getStoredTaskList();
        parserManager = new ParserManager();
    }

    /**
     * Adds commands to lists and runs executes commands
     */
    public String getResponse(String input) {
        if(!input.trim().toLowerCase().equals("bye")) {
            try {
                if (isUndoCommand(input)) {
                    this.taskList = commandHistory.pop();
                    dataStorage.storeTaskList(this.taskList);
                    return commandHistory.getUndoMessage(this.taskList);
                } else {
                    Command command = parserManager.parseCommand(this.taskList, input.trim());
                    assert command != null;
                    commandHistory.update(command, this.taskList);
                    String response = command.execute(this.taskList);
                    dataStorage.storeTaskList(this.taskList);
                    return response;
                }
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

    private static boolean isUndoCommand(String fullCommand) {
        return fullCommand.toLowerCase().trim().equals("undo");
    }
}
