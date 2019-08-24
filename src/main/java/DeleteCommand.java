import task.Task;

public class DeleteCommand extends Command {
    private final String posString;

    public DeleteCommand(String posString) {
        this.posString = posString;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            int position = Integer.parseInt(posString) - 1;
            Task oldTask = tasks.getTask(position);
            tasks.deleteTask(position);
            ui.showTaskDeleted(oldTask);
            ui.showNumTasks(tasks.getNumTasks());
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        }
    }
}
