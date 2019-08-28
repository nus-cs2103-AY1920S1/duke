public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        try {
            Task deleted = tasks.removeTask(index);
            ui.output(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                    deleted.toString(), tasks.getTasksSize()));
        }
        catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Please input a number between 1 and %d after 'delete'",
                    tasks.getTasksSize()));
        }
    }

    public boolean isRunning() {
        return true;
    }
}
