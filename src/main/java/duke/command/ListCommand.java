package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        String result = "Here are the tasks in your list:\n";
        int index = 1;
        for (int i = 1; i <= taskList.size(); i++) {
            result += "\n" + index + ". " + taskList.getTask(i);
            index++;
        }


        ui.printText(result);


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