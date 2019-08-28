public abstract class AddCommand implements Command {
    String line;

    public AddCommand(String line) {
        this.line = line;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }

}
