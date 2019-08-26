abstract class Command {
    private String commandString;
    private boolean exit;

    public Command(String commandString) {
        this.commandString = commandString;
        this.exit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
