abstract class Command {
    boolean isExit = false;

    abstract void execute(TaskList tasks, UserInterface ui, Storage storage);

    boolean isExit() {
        return isExit;
    }
}
