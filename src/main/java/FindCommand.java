public class FindCommand extends Command {

    private String instruction;

    /**
     * Represents the command of finding tasks which contain a specified keyword.
     * @param instruction refers to the keyword the user is looking for
     */
    public FindCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Returns the message DukeBot will show after each interaction with the user.
     * @param tasks refers to the list of tasks DukeBot is handling
     * @param ui refers to the instance of the UI class which handles output messages
     * @param storage refers to the instance of Storage class which handles read-write to the .txt file
     * @return response of DukeBot to the given user query
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.showFoundItems(instruction, tasks);
    }
}
