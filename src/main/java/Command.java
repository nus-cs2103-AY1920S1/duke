/**
 * Abstract class for all Commands which handle changes to user interaction, task list and saved todo file
 */
abstract class Command {
    /**
     * First keyword entered by user determining command type
     */
    protected String command;
    /**
     * Following keywords indicating specifications of command (if relevant)
     */
    protected String commandDetails;
    /**
     * Constant standard indentation from start of line (formatting)
     */
    protected String INDENT;

    /**
     * Superclass to all Commands, taking in keywords and indentation for execution of relevant functions
     * @param command First keyword entered by user determining command type
     * @param commandDetails Following keywords for specification of command
     * @param INDENT Constant indentation from start of line (formatting)
     */
    public Command(String command, String commandDetails, String INDENT) {
        this.command = command;
        this.commandDetails = commandDetails;
        this.INDENT = INDENT;
    }

    /**
     * Returns Boolean indicating if the chat closes (upon user typing "bye")
     * @return Boolean indicating if the chat will close
     */
    public boolean isExit() {
        return command.equals("bye");
    }

    /**
     * Abstract class for all further Commands to handle all interaction and task changes
     * @param tasks Contains task list and operations to delete from list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     * @throws DukeException Custom exception
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
