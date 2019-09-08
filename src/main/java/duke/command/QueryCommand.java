package duke.command;

import duke.component.TaskList;
import duke.task.Task;

import java.util.ListIterator;

/**
 * A class which serves as the parent of classes which queries the tasks list of Duke.
 */
public abstract class QueryCommand extends Command {

    /**
     * Returns the printed form of a TaskList object.
     * The TaskList object is printed in this form:
     * 1. Task 1
     * 2. Task 2
     * ... and so on.
     *
     * @param tasksList a TaskList object to be printed.
     * @return the printed form the TaskList object.
     */
    protected String getPrintedList(TaskList tasksList) {
        StringBuilder builder = new StringBuilder(200);

        ListIterator<Task> iterator = tasksList.listIterator();
        int size = tasksList.size();
        for (int i = 1; i <= size; i++) {
            // Append the index of a task follows by its description.
            builder.append(i + ". ");
            builder.append(iterator.next().toString());
            builder.append("\n");
        }

        return builder.toString();
    }
}
