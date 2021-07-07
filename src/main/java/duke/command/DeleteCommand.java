package duke.command;

import java.io.IOException;

import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;
import duke.exception.DukeInvalidTaskException;
import duke.exception.DukeWriteToFileException;
import duke.task.Task;

/**
 * An executable object representation of a user command to delete a specified task.
 */
public class DeleteCommand extends Command {
    private int id;

    /**
     * Constructs a <code>DeleteCommand</code> object with the ID of a task to be removed.
     * 
     * @param command raw command string that generated this <code>Command</code> object.
     * @param id <code>int</code> ID of the task to be removed.
     */
    public DeleteCommand(String command, int id) {
        super(command);
        this.id = id;
    }

    /**
     * Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     * 
     * @param tasks associated <code>TaskList</code> object to execute the command with.
     * @param fileMgr associated <code>Storage</code> object to execute the command with.
     * @return a <code>String</code> containing the output of executing this command.
     * @throws DukeException if the command references an invalid <code>Task</code> or a file I/O error occured.
     */
    public String execute(TaskList tasks, Storage fileMgr) throws DukeException {
        // GUARD: against invalid (non-existent) task IDs
        if (this.id < 1 || this.id > tasks.numberOfTasks()) {
            throw new DukeInvalidTaskException(this.id, this.getCommand());
        }

        Task task = tasks.deleteTask(id);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in DeleteCommand");
        }
        
        String template = "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.";
        return String.format(template, task.toString(), tasks.numberOfTasks());
    }
}
