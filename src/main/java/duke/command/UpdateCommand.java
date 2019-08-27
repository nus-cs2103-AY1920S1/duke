package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class UpdateCommand extends Command {

    private String directive;
    private int position;

    public UpdateCommand(String directive, int position) {
        super();
        this.directive = directive;
        this.position = position;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.markNumberedTaskAsDone(position);
        ui.notifyMarkedAsDone(task);
        storage.writeListToFile(taskList);
    }

}