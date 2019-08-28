public abstract class Command {
    public abstract void execute(MyList taskList, UserInterface ui, Storage storage) throws DukeException;

    //default value of isExit
    public boolean isExit() {
        return false;
    }
}
