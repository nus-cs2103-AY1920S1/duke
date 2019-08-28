abstract class Command {
    protected boolean isExit;

    Command() {
        this.isExit = false;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExit() {
        return this.isExit;
    }
}