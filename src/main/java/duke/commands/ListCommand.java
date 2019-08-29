package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;

/** Command invoked when the "list" command is encountered */
public class ListCommand extends Command {
    /**
     * Will iterate through the Tasks in TaskList and print out the
     * tasks one by one with a counter. This method will not cause the
     * TaskList to be saved.
     * @param ui Ui object that is responsible for printing output as a response
     * @param storage Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException
     */
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
