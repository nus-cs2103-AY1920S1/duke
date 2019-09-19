package duke.command;

import java.util.ListIterator;
import java.util.stream.IntStream;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.task.Task;

/**
 * Encapsulates a command which finds a task in the tasks list of Duke based on a keyword.
 */
public class FindCommand extends QueryCommand {
    private String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param k keyword used to find target tasks.
     */
    public FindCommand(String k) {
        keyword = k;
    }

    /**
     * Returns a message which contains all the matching tasks.
     * A task is matching if its description contains the keyword given by the user.
     *
     * @param database database of Duke.
     * @param tasksList tasks list of Duke.
     * @return
     */
    public String execute(DukeDatabase database, TaskList tasksList) {
        StringBuilder builder = new StringBuilder(250);

        String header = "Here are the matching tasks in your list:\n";
        String printedList = getPrintedList(getMatchingList(keyword, tasksList));

        builder.append(header);
        builder.append(printedList);

        return builder.toString();
    }

    /**
     * Returns a TaskList which contains all the tasks that matches the keyword.
     *
     * @param keyword keyword used to find tasks which have the keyword in their description.
     * @param tasksList  tasks list of Duke.
     * @return a TaskList which contains all the tasks that matches the keyword.
     */
    private TaskList getMatchingList(String keyword, TaskList tasksList) {
        TaskList matchingList = new TaskList();

        ListIterator<Task> iterator = tasksList.listIterator();
        int size = tasksList.size();

        // Find the tasks that have the keyword given by user
        // and append their string representation to the message to be printed.
        IntStream.rangeClosed(1, size)
                .forEach(i -> {
                    Task currTask = iterator.next();
                    String description = currTask.toString().toLowerCase();
                    if (description.contains(keyword.toLowerCase())) {
                        matchingList.addTask(currTask);
                    }
                });

        return matchingList;
    }
}
