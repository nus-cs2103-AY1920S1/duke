package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

import java.util.ArrayList;

/** Command invoked when the "list" command is encountered. */
public class ListCommand extends Command {
    /**
     * Will iterate through the Tasks in TaskList and print out the
     * tasks one by one with a counter. This method will not cause the
     * TaskList to be saved.
     *
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @return String representing Duke's response
     * @throws DukeException re-thrown from underlying method calls.
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        ArrayList<Task> allTasksArrList = allTasks.getArrayList();

        int counter = 1;
        ui.reset();
        ui.addSentence("Here are the tasks in your list:");
        for (Task t : allTasksArrList) {
            ui.addSentence("\t" + counter + ". " + t);
            counter++;
        }
        return ui.getResponse();
    }
}
