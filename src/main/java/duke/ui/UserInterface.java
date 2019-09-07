package duke.ui;

/**
 * This is the user interface of the Duke program. The Duke program will print information here. The user interface can
 * display the change information, error messages, exit message, list of task, welcome message to the user.
 */
public interface UserInterface {

    public String showWelcome();

    public String showError(String message);

    public String showLoadingError();

    public String showLineError(int lineCount, String line);

    public String readCommand();

    public String readCommand(String userInput);

    public String showTable(String list);

    public String showResultsFound(String list);

    public String showAddInformation(String task, int size);

    public String showMarkedAsDone(String task);

    public String showDeletedMessage(String task, int size);

    public String showExitMessage();

}
