import java.io.IOException;
import java.util.Date;

public class DeadlineCommand extends Command {
    private String description;
    private Date byDeadline;

    public DeadlineCommand(String command, String description, Date byDeadline) {
        super(command);
        this.description = description;
        this.byDeadline = byDeadline;
    }

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
