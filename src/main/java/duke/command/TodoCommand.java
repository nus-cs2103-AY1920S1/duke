package duke.command;

import java.io.IOException;

import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;
import duke.exception.DukeWriteToFileException;
import duke.task.Task;
import duke.task.TodoTask;

public class TodoCommand extends Command {
    private String description;

    /**
     *  Constructs a <code>TodoCommand</code> object with a given description.
     *  @param command raw command string that generated this <code>Command</code> object.
     *  @param description <code>String</code> description of the <code>TodoTask</code> to be created.
     */
    public TodoCommand(String command, String description) {
        super(command);
        this.description = description;
    }

    /**
     *  Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     *  @param tasks associated <code>TaskList</code> object to execute the command with.
     *  @param fileMgr associated <code>Storage</code> object to execute the command with.
     *  @return a <code>String</code> containing the output of executing this command.
     *  @throws DukeException if an I/O error occured when writing the updated TaskList to the file.
     */
    public String execute(TaskList tasks, Storage fileMgr) throws DukeException {
        Task task = new TodoTask(this.description);
        tasks.addTask(task);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in TodoCommand");
        }
        
        String template = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.";
        return String.format(template, task.toString(), tasks.numberOfTasks());
    }
}
