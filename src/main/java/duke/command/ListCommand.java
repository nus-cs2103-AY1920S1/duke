package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.util.stream.IntStream;

/**
 * Represents user's list commmand to chatbot.
 * The 'ListCommand' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user.
 */
public class ListCommand extends Command {

    /**
     * Prints out all the task that user has to do or has done.
     * Tasks are retrieved from TaskList
     *
     * @param taskList List of the things user needs to do
     * @param ui       Interface that interacts with the user
     * @param storage  Stores the user input in a file
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");

        IntStream.rangeClosed(1, taskList.size())
                .mapToObj(index -> String.format("\n %d . %s",
                        index, taskList.getTask(index)))
                .forEach(result::append);


        return result.toString();


    }

    /**
     * Returns a false to indicate program has not exited.
     *
     * @return false Program has not exited
     */
    public boolean isExit() {
        return false;
    }
}