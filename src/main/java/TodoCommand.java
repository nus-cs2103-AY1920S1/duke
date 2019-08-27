import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String command, String description) {
        super(command);
        this.description = description;
    }

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
