package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

import java.util.ArrayList;

/** Command invoked when the "find" command is encountered. */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor.
     *
     * @param keyword String representing the keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Will search through all tasks in TaskList for keyword.
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException re-thrown from underlying method calls.
     * @return String representing Duke's response
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        ArrayList<Task> allTasksArrList = allTasks.getArrayList();

        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task t : allTasksArrList) {
            if (t.toString().contains(this.keyword)) {
                filteredTasks.add(t);
            }
        }

        ui.reset();
        if (filteredTasks.size() == 0) {
            ui.addSentence("Sorry, no tasks match your search term!");
        } else {
            int counter = 1;
            ui.addSentence("Here are the tasks that contain your search term:");
            for (Task t : filteredTasks) {
                ui.addSentence("\t" + counter + ". " + t);
                counter++;
            }
        }

        return ui.getResponse();
    }
}
