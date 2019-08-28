public abstract class Command {

    private boolean isExit;
    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExit() {
        return isExit;
    }

    void canExit() { this.isExit = true; }
}
