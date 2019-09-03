package duke.command;

import java.io.IOException;
import java.util.Date;

import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;
import duke.exception.DukeWriteToFileException;
import duke.task.Task;
import duke.task.EventTask;

public class EventCommand extends Command {
    private String description;
    private Date atTime;

    /**
     *  Constructs an <code>EventCommand</code> object with a given description and event timestamp.
     *  @param command raw command string that generated this <code>Command</code> object.
     *  @param description <code>String</code> description of the <code>EventTask</code> to be created.
     *  @param atTime timestamp of the <code>EventTask</code> to be created, as a <code>Date</code> object.
     */
    public EventCommand(String command, String description, Date atTime) {
        super(command);
        this.description = description;
        this.atTime = atTime;
    }

    /**
     *  Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     *  @param tasks associated <code>TaskList</code> object to execute the command with.
     *  @param fileMgr associated <code>Storage</code> object to execute the command with.
     *  @return a <code>String</code> containing the output of executing this command.
     *  @throws DukeException if an I/O error occured when writing the updated TaskList to the file.
     */
    public String execute(TaskList tasks, Storage fileMgr) throws DukeException {
        Task task = new EventTask(this.description, this.atTime);
        tasks.addTask(task);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in EventCommand");
        }
        
        String template = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.";
        return String.format(template, task.toString(), tasks.numberOfTasks());
    }
}
