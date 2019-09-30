package duke.util.cli;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;
import java.util.Scanner;

public class Cli implements Ui {
    private Scanner in;

    public Cli() {
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

    @Override
    public void showMessage(UiMessage uiMessage) {
        System.out.println(uiMessage.getMessage());
    }

    @Override
    public void showError(DukeException exception) {
        System.out.println(exception.getMessage());
    }

    @Override
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        tasks.printList();
    }

//    public String getResponse() {
//        // todo
//    }
}
