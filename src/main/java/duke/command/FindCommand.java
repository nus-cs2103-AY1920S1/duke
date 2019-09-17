package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Represents user's 'find' commmand to chatbot.
 * Finds tasks with keyword in it, in the list of tasks.
 * The 'findCommand' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user(in superclass).
 */
public class FindCommand extends Command {

    /**
     * Keyword to find specific tasks.
     */
    String keyword;

    /**
     * Initializes a new instance of FindCommand.
     *
     * @param keyword Keyword to help find the task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds a specific task with keyowrd from list of tasks,
     * and prints result.
     *
     * @param taskList Stores the list of the tasks.
     * @param ui       Interface that interacts with the user.
     * @param storage  Stores the user input in a file.
     * @throws DukeException IOException if there is an error writing or reading file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        List<String> foundTasks = taskList.findTask(this.keyword);

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");

        //Solution adapted from https://stackoverflow.com/questions/18552005/is-there-a-concise-way-to-iterate-over-a-stream-with-indices-in-java-8
        IntStream.rangeClosed(1, foundTasks.size())
                .mapToObj(index -> String.format("\n %d . %s",
                                           index, foundTasks.get(index - 1)))
                .forEach(result::append);

        return result.toString();


    }

    /**
     * Returns a false to indicate program has not exited.
     *
     * @return false Program has not exited.
     */
    public boolean isExit() {
        return false;
    }
}


