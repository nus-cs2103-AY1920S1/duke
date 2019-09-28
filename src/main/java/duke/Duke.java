package duke;

import duke.command.Command;
import duke.command.CommandHistoryStack;
import duke.command.EmptyHistoryException;
import duke.command.UnknownCommandException;
import duke.parser.ParserManager;
import duke.task.TaskList;
import duke.ui.MainWindow;
import javafx.animation.PauseTransition;

public class Duke {
    private TaskList taskList;
    private static ParserManager parserManager;
    private static DataStorage dataStorage;
    private static MainWindow mainWindow;
    private static CommandHistoryStack commandHistory;

    /**
     * Constructor.
     * @param mainWindow - Mainwindow application for JavaFX
     */
    public Duke(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        initialize();
        mainWindow.display(Ui.getWelcomeMessage());
        commandHistory = new CommandHistoryStack();
    }

    /**
     * Initialize static variables.
     */
    public void initialize() {
        dataStorage = new DataStorage();
        this.taskList = dataStorage.getStoredTaskList();
        parserManager = new ParserManager();
    }

    /**
     * Adds commands to lists and runs executes commands.
     */
    public String getResponse(String input) {
        if (!input.trim().toLowerCase().equals("bye")) {
            try {
                if (isUndoCommand(input)) {
                    return executeUndo();
                }
                return executeCommand(input);
            } catch (UnknownCommandException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException e) {
                return Ui.getExceedListMessage(taskList.size());
            } catch (NumberFormatException e) {
                return Ui.getInvalidStatementMessage(input);
            }
        }
        exit();
        return null;
    }

    /**
     * Exits the application.
     */
    public void exit() {
        mainWindow.display(Ui.getGoodbyeMessage());
        PauseTransition exitTransition = new PauseTransition();
        exitTransition.setOnFinished(mainWindow.exitHandler);
        exitTransition.play();
    }

    /**
     * Returns true if command complies to Undo Command Format, else returns false.
     */
    private boolean isUndoCommand(String fullCommand) {
        return fullCommand.toLowerCase().trim().equals("undo");
    }

    /**
     * Revert existing tasklist to state before last modification.
     * @return - String containing message for successful undo
     * @throws EmptyHistoryException - if no more previous tasks to undo
     */
    private String executeUndo() throws EmptyHistoryException {
        this.taskList = commandHistory.pop();
        dataStorage.storeTaskList(this.taskList);
        return commandHistory.getUndoMessage(this.taskList);
    }

    /**
     * Executes command based on input and updates tasklist based on given change.
     * @param input given directly by user
     * @return - String containing successful execution of command
     * @throws UnknownCommandException - Format errors of command
     * @throws RuntimeException - invalid input such as indexoutofbounds or numberformatexceptions
     */
    private String executeCommand(String input) throws UnknownCommandException, RuntimeException {
        Command command = parserManager.parseCommand(this.taskList, input.trim());
        assert command != null;
        commandHistory.update(command, this.taskList);
        String response = command.execute(this.taskList);
        dataStorage.storeTaskList(this.taskList);
        return response;
    }
}
