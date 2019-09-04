package seedu.duke.core;

import javafx.scene.control.Label;
import seedu.duke.model.Task;

import java.util.List;

public class Ui {
    private static String LOGO =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static String GREETINGMSG =
            "________________________________________________________________________________________________ \n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "________________________________________________________________________________________________";

    public void readCommand() {

    }

    public Label showWelcome() {
        return new Label(GREETINGMSG);
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public Label printByeMessage() {
        return new Label("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {

    }

    /**
     * Prints all the tasks inside task list.
     * @param list Task list (ArrayList) where all tasks are stored.
     */
    public String displayList(String output, List<Task> list) {
        output += "Here are the tasks in your list:\n";
        int index = 0;
        for (Task task : list) {
            index++;
            output += (index + "." + task + "\n");
        }
        return output;
    }
}
