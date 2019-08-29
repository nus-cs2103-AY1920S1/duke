package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
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
        LIST_ALL;
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
        } else {
            throw new DukeException("Internal logic bug occurs!");
        }
    }

    /**
     * Lists all the tasks in the taskList.
     */
    private void list() {
        ui.echo(() -> {
            System.out.print(Ui.INDENTATION_LVL1 + "Here are the tasks in your list:\n");
            ListIterator<Task> iterator = taskList.listIterator();

            for (int i = 0; i < taskList.size(); i++) {
                String taskDetails = iterator.next().toString();
                System.out.printf(ui.indentAndSplit(String.format("%d.%s", i + 1, taskDetails),
                        Ui.INDENTATION_LVL1));
            }
        });
    }
}
