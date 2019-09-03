package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;

/** Command invoked when "todo", "event" or "deadline" commands are encountered. */
public class AddCommand extends Command {
    ArrayList<String> commandParams;

    /**
     * Constructor.
     *
     * @param commandParams ArrayList containing the individual String components
     *                      required for the different Task types.
     */
    public AddCommand(ArrayList<String> commandParams) {
        this.commandParams = commandParams;
    }

    /**
     * Executes the desired behaviour for the AddCommand object. In this case,
     * this involves adding a ToDo, Event or Deadline object to the TaskList.
     *
     * @param ui       Ui object that is responsible for printing output as a response
     * @param storage  Storage object respnsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @throws DukeException re-thrown from underlying method calls.
     */
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        Task t = new Task("Uninitialised Task");
        switch (this.commandParams.get(0)) {
        case "todo":
            t = allTasks.addToDo(this.commandParams.get(1));
            break;
        case "event":
            t = allTasks.addEvent(this.commandParams.get(1), this.commandParams.get(2), this.commandParams.get(2));
            break;
        case "deadline":
            t = allTasks.addDeadline(this.commandParams.get(1), this.commandParams.get(2));
            break;
        default:
            break;
        }

        //UI response
        ui.printLine();
        ui.printSentence("Got it. I've added this task:");
        ui.printSentence("\t" + t);
        ui.printSentence("Now you have " + allTasks.size() + " tasks in the list");
        ui.printLine();

        //Parent method invoked to save TaskList
        super.execute(ui, storage, allTasks);
    }
}
