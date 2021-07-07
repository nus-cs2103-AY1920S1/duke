package duke.command;

import java.io.IOException;

import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;
import duke.exception.DukeWriteToFileException;
import duke.task.Task;

/**
 * An executable object representation of a user command to add a new task of some type.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Constructs an <code>AddCommand</code> object with a given <code>Task</code> instance to add.
     * 
     * @param command raw command string that generated this <code>Command</code> object.
     * @param newTask <code>Task</code> instance to add to a <code>TaskList</code>.
     */
    public AddCommand(String command, Task newTask) {
        super(command);
        this.newTask = newTask;
    }

    /**
     * Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     * 
     * @param tasks associated <code>TaskList</code> object to execute the command with.
     * @param fileMgr associated <code>Storage</code> object to execute the command with.
     * @return a <code>String</code> containing the output of executing this command.
     * @throws DukeException if an I/O error occured when writing the updated TaskList to the file.
     */
    public String execute(TaskList tasks, Storage fileMgr) throws DukeException {
        tasks.addTask(this.newTask);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in AddCommand");
        }
        
        String responseTemplate = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.";
        return String.format(responseTemplate, this.newTask.toString(), tasks.numberOfTasks());
    }
}
