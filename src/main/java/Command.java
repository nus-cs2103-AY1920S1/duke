public abstract class Command {
    /** Task enter by user */
    protected String input;

    /** The type of task enter by user */
    protected String type;

    /** The exit status */
    protected boolean exit = false;

    /**
     * The Constructor of Abstract Class Command.
     * @param input Task enter by user.
     * @param type The type of task.
     */
    public Command(String input, String type) {
        this.input = input;
        this.type = type;
    }

    /** Execute method */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Exit the Duke Program.
     * @return True to continue or False to exit.
     */
    public Boolean isExit() {
        return exit;
    }
}