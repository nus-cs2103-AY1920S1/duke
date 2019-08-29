package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Ui;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        Task t = allTasks.deleteTask(this.taskNum);

        ui.printLine();
        ui.printSentence("Noted. I've removed this task:");
        ui.printSentence("\t" + t);
        ui.printSentence("Now you have " + allTasks.size() + " tasks in the list.");
        ui.printLine();

        super.execute(ui, storage, allTasks);
    }
}
