package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Create a deadline command instance.
     * @param description information of the deadline task
     * @param by deadline of the deadline task
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Create a deadline task.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        TaskList taskList = model.getTaskList();
        Task deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
        ui.showAddTask(deadline, taskList);
        storage.saveData(model);
    }
}
