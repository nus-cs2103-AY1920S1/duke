public abstract class Command {
    protected String command;
    protected String commandDetails;
    protected String INDENT;

    public Command(String command, String commandDetails, String INDENT) {
        this.command = command;
        this.commandDetails = commandDetails;
        this.INDENT = INDENT;
    }

    public boolean isExit() {
        return command.equals("bye");
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
