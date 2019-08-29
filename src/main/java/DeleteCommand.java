import java.util.Scanner;

public class DeleteCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        int deletion = ui.readIndex();
        if (deletion < 0 || deletion > tasks.getTaskList().size()) {
            throw new DukeException("Task not found");
        }
        tasks.deleteTask(deletion);
    }
}
