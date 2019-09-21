package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.DeadlineTask;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String taskDescription;
    private LocalDateTime taskTime;
    
    public DeadlineCommand(String taskDescription, LocalDateTime taskTime) {
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
    }
    
    /**
     * Creates a DeadlineTask and adds it to this TaskList.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui       The Ui object passed from Duke.
     * @param storage  The Storage object passed from Duke.
     * @return The response String.
     * @throws DukeException A DukeException custom exception.
     * @throws IOException An IOException.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskDescription.matches("\\s*")) {
            throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        Task currentTask = new DeadlineTask(taskDescription, taskTime);
        taskList.addTask(currentTask);
        storage.writeSavedList(taskList.getList());
        return ui.showAfterAddingTask(currentTask, taskList.getSize());
    }
}
    
    
    
    
    
