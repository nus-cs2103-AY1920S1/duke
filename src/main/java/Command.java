public abstract class Command {

    public Command() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit() {
        return false;
    }


}
