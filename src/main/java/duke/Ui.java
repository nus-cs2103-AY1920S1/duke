package duke;

import duke.Task;
import duke.TaskList;

import java.util.Scanner;

/**
 * A class to deal with interactions with the user
 */

public class Ui {

    public String getStartingMessage(TaskList tasks) {
        String message = "Hello! I'm Duke\nWhat can I do for you?\n";
        if (tasks.size() == 0) {
            message += "You do not have any stored tasks\n";
        } else {
            message += "This is your current list of tasks\n";
            for (int i = 0; i < tasks.size(); i++) {
                int currentItemNumber = i + 1;
                Task currentTask = tasks.get(i);
                message += currentItemNumber + "." + currentTask + "\n";
            }
        }
        return message;
    }

    public String getErrorMessage(DukeException e) {
        return e.getMessage();
    }
}
