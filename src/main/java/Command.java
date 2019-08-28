public abstract class Command {
    String stringCommand;

    public Command(String stringCommand) {
        this.stringCommand = stringCommand;
    }

    public abstract void execute(TaskList taskList, UI ui, Storage storage);

    public abstract boolean isExit();
}
