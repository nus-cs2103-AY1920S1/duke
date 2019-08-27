import java.io.IOException;
import java.util.Date;

public class EventCommand extends Command {
    private String description;
    private Date atTime;

    public EventCommand(String command, String description, Date atTime) {
        super(command);
        this.description = description;
        this.atTime = atTime;
    }

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
