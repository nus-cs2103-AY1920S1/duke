abstract class Command {

    private boolean isExit;

    Command(boolean isExit) {
        this.isExit = isExit;
    }

    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    boolean isExit() {
        return isExit;
    }
}