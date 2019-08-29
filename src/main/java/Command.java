public abstract class Command {
    String command;

    abstract void execute(TaskList tasks, UI ui, Storage storage) throws Exception;
    abstract boolean isExit();
}
