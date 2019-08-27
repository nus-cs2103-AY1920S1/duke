public abstract class Command {

    public boolean isTerminated() {
        return false;
    }

    public abstract void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException;

}
