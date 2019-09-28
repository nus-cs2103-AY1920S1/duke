public abstract class Command {

    protected String fullCommand;
//    public Command() {}

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
