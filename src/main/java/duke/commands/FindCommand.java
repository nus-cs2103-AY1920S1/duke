package duke.commands;

import java.util.List;

import duke.core.TaskList;
import duke.core.Ui;
import duke.errors.DukeAssertions;
import duke.errors.DukeException;
import duke.errors.DukeExceptionType;
import duke.tasks.Task;



/**
 * Represents a command which contains an execute method that finds tasks with the matching keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Initialises the find command
     */
    private FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
        assert keyword != null;
    }

    /**
     * Service for creating a find command that checks for multiple keywords
     * @param tokens user input split by space, required for creating a find command
     * @throws DukeException Thrown when the parameters contains multiple keywords
     */
    public static FindCommand createFindCommandIfValid(String[] tokens) throws DukeException{
        if (tokens.length > 2) {
            throw new DukeException("Must be a single keyword", DukeExceptionType.NOTSINGLEWORD);
        }
        return new FindCommand(tokens[1]);
    }

    /**
     * Executes by storing all tasks with descriptions containing the keyword
     * and prints to the user
     * @param taskList list containing current tasks
     * @param ui user interface
     */
    public String execute(TaskList taskList, Ui ui) {
        DukeAssertions.assertNotNull(taskList, ui);
        List<Task> resultList = taskList.findTasks(this.keyword);
        return ui.printFindResults(resultList);
    }


}

