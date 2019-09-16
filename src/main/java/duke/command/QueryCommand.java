package duke.command;

import java.util.ListIterator;
import java.util.stream.IntStream;

import duke.component.TaskList;
import duke.task.Task;

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

        IntStream.rangeClosed(1, size)
                .forEach(index -> {
                    // Append the index of a task follows by its description.
                    builder.append(index + ". ");
                    builder.append(iterator.next().toString());
                    builder.append("\n\n"); // Add a new line in between every task
                });

        return builder.toString();
    }
}
