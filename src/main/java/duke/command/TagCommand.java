package duke.command;

import java.io.IOException;

import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;
import duke.exception.DukeInvalidTaskException;
import duke.exception.DukeWriteToFileException;
import duke.task.Task;

/**
 * An executable object representation of a user command to tag a specified task.
 */
public class TagCommand extends Command {
    private int id;
    private String tag;

    /**
     * Constructs a <code>TagCommand</code> object with the ID of a task to tag with a given <code>String</code>.
     * 
     * @param command raw command string that generated this <code>Command</code> object.
     * @param id <code>int</code> ID of the task to be tagged.
     * @param tag <code>String</code> tag to apply to this <code>Task</code>.
     */
    public TagCommand(String command, int id, String tag) {
        super(command);
        this.id = id;
        this.tag = tag;
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

        Task updatedTask = tasks.tagTask(this.id, this.tag);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in TagCommand");
        }
        
        String template = "Noted. I've updated this task:\n  %s";
        return String.format(template, updatedTask.toString());
    }
}
