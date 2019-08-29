package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        ArrayList<Task> allTasksArrList = allTasks.getArrayList();

        int counter = 1;
        ui.printLine();
        ui.printSentence("Here are the tasks in your list:");
        for (Task t : allTasksArrList) {
            ui.printSentence("\t" + counter + ". " + t);
            counter++;
        }
        ui.printLine();
    }
}
