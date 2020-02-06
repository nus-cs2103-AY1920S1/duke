package dose.util.cli;

import dose.task.TaskList;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import java.util.Scanner;

/**
 * Handles Dose's UI when in CLI mode.
 */
public class Cli implements Ui {
    private Scanner in;

    public Cli() {
        this.in = new Scanner(System.in);
    }

    @Override
    public String readCommand() {
        return in.nextLine();
    }

    @Override
    public void showMessage(UiMessage uiMessage) {
        System.out.println(uiMessage.getMessage());
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(DoseException exception) {
        System.out.println(exception.getMessage());
    }

    @Override
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        tasks.printList();
    }

    /**
     * Displays a divider line in the UI.
     */
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }
}
