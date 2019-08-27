public abstract class Command {
    Command() {

    }

    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    public boolean isBye() {
        return false;
    }
}

