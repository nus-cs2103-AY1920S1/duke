public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        try {
            Task doneTask = tasks.getTask(index);
            doneTask.markDone();
            ui.output("Nice! I've marked this task as done: \n" + doneTask.toString());
        }
        catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Please input a number between 1 and %d after 'done'",
                    tasks.getTasksSize()));
        }
    }

    public boolean isRunning() {
        return true;
    }
}
