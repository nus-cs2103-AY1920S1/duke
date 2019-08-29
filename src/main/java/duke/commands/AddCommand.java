package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class AddCommand extends Command {
    ArrayList<String> commandParams;
    public AddCommand(ArrayList<String> commandParams) {
        this.commandParams = commandParams;
    }

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
        }

        ui.printLine();
        ui.printSentence("Got it. I've added this task:");
        ui.printSentence("\t" + t);
        ui.printSentence("Now you have " + allTasks.size() + " tasks in the list");
        ui.printLine();

        super.execute(ui, storage, allTasks);
    }
}
