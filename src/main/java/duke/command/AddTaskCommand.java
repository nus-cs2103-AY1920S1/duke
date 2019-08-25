package duke.command;

import duke.DukeInvalidArgumentException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

abstract class AddTaskCommand extends WritableCommand {
    Task taskToAdd;

    AddTaskCommand(String[] commandArgs) {
        super(commandArgs);
    }

    @Override
    void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);

        ui.printMsgLine(" Got it. I've added this task:");
        ui.printMsgLine(String.format("   %s", taskToAdd.getStatusText()));
        ui.printMsgLine(String.format(" Now you have %d tasks in the list.", tasks.getSize()));
    }
}
