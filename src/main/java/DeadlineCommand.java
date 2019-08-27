import java.io.IOException;
import java.util.Date;

public class DeadlineCommand extends Command {
    private String description;
    private Date byDeadline;

    /**
     *  Constructs a <code>DeadlineCommand</code> object with a given description and deadline.
     *  @param command raw command string that generated this <code>Command</code> object.
     *  @param description <code>String</code> description of the <code>DeadlineTask</code> to be created.
     *  @param byDeadline date of the <code>DeadlineTask</code> to be created, as a <code>Date</code> object.
     */
    public DeadlineCommand(String command, String description, Date byDeadline) {
        super(command);
        this.description = description;
        this.byDeadline = byDeadline;
    }

    /**
     *  Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     *  @param tasks associated <code>TaskList</code> object to execute the command with.
     *  @param fileMgr associated <code>Storage</code> object to execute the command with.
     *  @return a <code>String</code> containing the output of executing this command.
     *  @throws DukeException if an I/O error occured when writing the updated TaskList to the file.
     */
    public String execute(TaskList tasks, Storage fileMgr) throws DukeException {
        Task task = new DeadlineTask(this.description, this.byDeadline);
        tasks.addTask(task);
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in DeadlineCommand");
        }
        
        String template = "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.";
        return String.format(template, task.toString(), tasks.numberOfTasks());
    }
}
