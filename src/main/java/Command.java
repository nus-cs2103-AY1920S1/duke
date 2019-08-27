public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage);

    public abstract boolean isExit();
}
