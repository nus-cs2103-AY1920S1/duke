public class ExitCommand extends Command {

    /**
     * Returns whether the command is an ExitCommand.
     * @return whether the command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints a closing statement before the programme closes.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     * @return message response to user
     */
    @Override
    public String executeAndReturnMessage(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
