package duke.ui;

import java.util.Scanner;

/**
 * This is the user interface of the Duke program. The Duke program will print information here. The user interface can
 * display the change information, error messages, exit message, list of task, welcome message to the user.
 */
public interface UserInterface {

    public void showWelcome();

    public void showError(String message);

    public void showLoadingError();

    public void showLineError(int lineCount, String line);

    public String readCommand();

    public void showTable(String list);

    public void showResultsFound(String list);

    public void showAddInformation(String task, int size);

    public void showMarkedAsDone(String task);

    public void showDeletedMessage(String task, int size);

    public void showExitMessage();

}
