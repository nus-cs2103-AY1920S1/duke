package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TotalNoOfTasks {

    /**
     * Prints a string with the total number of tasks currently stored.
     * @return A string describing the total number tasks currently stored.
     */
    public static String totalNoOfTasks(ArrayList<Task> taskList) {
        int noOfTasks = taskList.size();
        return "Now you have " + (noOfTasks) + (noOfTasks == 1 ? " task" : " tasks") + " in the list.\n";
    }
}
