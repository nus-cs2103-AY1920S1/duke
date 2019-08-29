public abstract class Command {
    String input;
    boolean isExit = false;

    public Command(String input) {
        this.input = input;
    }
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;


    public boolean isExit() {
        return this.isExit;
    }
}
