import java.io.IOException;

public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(String command, int id) {
        super(command);
        this.id = id;
    }

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
