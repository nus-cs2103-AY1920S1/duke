package seedu.duke.core;

import javafx.scene.control.Label;
import seedu.duke.model.dto.Task;

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

    /**
     * Prints only the specified task from task list.
     * @param list Task list (ArrayList) where all tasks are stored
     * @param index index of which task user want to print out.
     */
    public String displayTask(String output, List<Task> list, int index) {
        if (index >= 0) {
            output = new StringBuilder(output)
                    .append(list.get(index) + "\n").toString() ;
        }
        return output;
    }
}
