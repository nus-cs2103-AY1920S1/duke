import java.io.IOException;

public class DoneCommand extends Command {
    private int id;

    public DoneCommand(String command, int id) {
        super(command);
        this.id = id;
    }

    public String execute(TaskList tasks, Storage fileMgr) throws DukeException {
        // GUARD: against invalid (non-existent) task IDs
        if (this.id < 1 || this.id > tasks.numberOfTasks()) {
            throw new DukeInvalidTaskException(this.id, this.getCommand());
        }

        Task task = tasks.getTask(id);
        task.complete();
        try {
            fileMgr.writeTaskList(tasks);
        } catch (IOException e) {
            throw new DukeWriteToFileException("writeTaskList method invocation in DoneCommand");
        }
        
        return String.format("Nice! I've marked this task as done:\n  %s", task.toString());
    }
}
