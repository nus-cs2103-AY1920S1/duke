package duke.ui;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;

/**
 * This is the user interface of the Duke program. The Duke program will print information here. The user interface can
 * display the change information, error messages, exit message, list of task, welcome message to the user.
 */
public interface UserInterface {

    public String showWelcome();

    public String showLoadingError();

    public String showLineError(int lineCount, String line);

    public String readCommand();

    public String readCommand(String userInput);

    public String showTable(String taskList);

    public String showResultsFound(String list);

    public String showAddInformation(String task, int size);

    public String showMarkedAsDone(String task);

    public String showDeletedMessage(String task, int size);

    public String showExitMessage();

    public String showInvalidCommandError(InvalidCommandException invalidCommand);

    public String showInvalidParametersError(InvalidParameterException invalidParameter);

    public String showSaveError();

    public String showSetReminder(String task, String dateToRemind);

    public String showSchedule(String schedule);
}
