package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        Task t = allTasks.completeTask(this.taskNum);

        ui.printLine();
        ui.printSentence("Nice! I've marked this task as done: ");
        ui.printSentence("\t" + t);
        ui.printLine();

        super.execute(ui, storage, allTasks);
    }
}

