package duke.util;

import duke.task.TaskList;
import duke.util.exception.DukeException;
import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the command entered by the user.
     * @return Command entered by the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Displays a divider line in the UI.
     */
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Displays the required message in the UI, given the type of message required.
     * @param uiMessage Enum indicating type of message required to be displayed.
     */
    public void showMessage(UiMessage uiMessage) {
        System.out.println(uiMessage.getMessage());
    }

    /**
     * Displays the required error message in the UI, given the type of exception thrown.
     * @param exception Exception thrown by the application.
     */
    public void showError(DukeException exception) {
        System.out.println(exception.getMessage());
    }

    public void showTasks(TaskList matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        matchingTasks.printList();
    }

//    public String getResponse() {
//        // todo
//    }
}
