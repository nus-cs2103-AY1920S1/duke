package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ListIterator;

/**
 * Encapsulates a command which query the tasks list of duke.Duke bot.
 */
public class QueryCommand extends Command {
    private QueryType queryType;

    /**
     * The type of query.
     */
    public enum QueryType {
        LIST_ALL, FIND_BY_KEYWORD;
    }

    /**
     * Constructs a QueryCommand object.
     *
     * @param queryType type of query.
     * @param input user's input.
     */
    public QueryCommand(QueryType queryType, String input) {
        super(input);
        this.queryType = queryType;
    }

    /**
     * Executes the query command accordingly.
     *
     * @param tasksList the tasks list of duke.Duke.
     * @param database the database of duke.Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        initialise(database, tasksList);

        String response = "";
        if (QueryType.LIST_ALL.equals(queryType)) {
            response = list();
        } else if (QueryType.FIND_BY_KEYWORD.equals(queryType)) {
            response = listTasksByKeyword();
        } else {
            throw new DukeException("Internal logic bug occurs!");
        }

        return response;
    }

    private String list() {
        StringBuilder builder = new StringBuilder(250);

        builder.append("Here are the tasks in your list:\n");
        appendListRepresentation(taskList, builder);

        return builder.toString();
    }

    /**
     * Lists a list of tasks which contains the keyword given by the user.
     */
    private String listTasksByKeyword() {
        // Extract keyword from user's input.
        String keyword = input.substring(4).trim();

        // Prepare the necessary components for searching the relevant tasks.
        TaskList resultList = new TaskList();
        ListIterator<Task> iterator = taskList.listIterator();
        int size = taskList.size();

        // Find the tasks that have the keyword given by user
        // and append their string representation to the message to be printed.
        for (int i = 0; i < size; i++) {
            Task currTask = iterator.next();
            if (currTask.toString().contains(keyword)) {
                resultList.addTask(currTask);
            }
        }

        // Generate the message to be printed
        StringBuilder builder = new StringBuilder(250);
        builder.append("Here are the matching tasks in your list:\n");
        appendListRepresentation(resultList, builder);

        return builder.toString();
    }

    private void appendListRepresentation(TaskList list, StringBuilder builder) {
        assert list != null : "List given cannot be null!";

        ListIterator<Task> iterator = list.listIterator();
        int size = list.size();

        for (int i = 1; i <= size; i++) {
            builder.append(i + ". ");
            builder.append(iterator.next().toString());
            builder.append("\n");
        }
    }
}
