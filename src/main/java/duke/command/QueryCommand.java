package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.support.PrintFunction;
import duke.task.Task;

import java.util.ListIterator;

/**
 * Encapsulates a command which query the tasks list of Duke bot.
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
     * @param tasksList the tasks list of Duke.
     * @param ui the ui of Duke.
     * @param database the database of Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public void execute(TaskList tasksList, Ui ui, DukeDatabase database) throws DukeException {
        initialise(tasksList, ui, database);

        if (QueryType.LIST_ALL.equals(queryType)) {
            list();
        } else if (QueryType.FIND_BY_KEYWORD.equals(queryType)) {
            listTasksByKeyword();
        } else {
            throw new DukeException("Internal logic bug occurs!");
        }
    }

    /**
     * Lists all the tasks in the taskList.
     */
    private void list() {
        ui.echo(getPrintListFunction(taskList, "Here are the tasks in your list:"));
    }

    /**
     * Lists a list of tasks which contains the keyword given by the user.
     */
    private void listTasksByKeyword() {
        // Extract keyword from user's input.
        String keyword = input.substring(4).trim();

        // Prepare the necessary components for searching the relevant tasks.
        TaskList resultList = new TaskList();
        ListIterator<Task> iterator = taskList.listIterator();
        int size = taskList.size();

        // Find the tasks that have the keyword given by user
        // and add them to the result list.
        for (int i = 0; i < size; i++) {
            Task currTask = iterator.next();
            if (currTask.toString().contains(keyword)) {
                resultList.addTask(currTask);
            }
        }

        // Print the result list.
        ui.echo(getPrintListFunction(resultList,"Here are the matching tasks in your list:"));
    }

    /**
     * Returns a PrintFunction used to print the given message followed by the tasks list to the console.
     * @param list the list of tasks to be printed.
     * @param message the message to be printed.
     * @return the PrintFunction object used to the do the printing as described in the method description.
     */
    private PrintFunction getPrintListFunction(TaskList list, String message) {
        return () -> {
            System.out.print(Ui.INDENTATION_LVL1 + message + "\n");
            ListIterator<Task> iterator = list.listIterator();

            for (int i = 0; i < list.size(); i++) {
                String taskDetails = iterator.next().toString();
                System.out.printf(ui.indentAndSplit(String.format("%d.%s", i + 1, taskDetails),
                        Ui.INDENTATION_LVL1));
            }
        };
    }
}
