/**
 * Represents the help command to exit the program.
 */
public class CommandHelp extends Command {

    /**
     * Represents the full command.
     */
    private String string;

    /**
     * Represents the guide.
     */
    private String helppage = "    Here are the commands available:" + "\n"
            + "    todo <task> - adds a task to be done to the list." + "\n"
            + "    event <task> /at <dd/mm/yyyy hhhh> - adds an event to the list." + "\n"
            + "    deadline <task> /by <dd/mm/yyyy hhhh> - adds a deadline to the list." + "\n"
            + "    list - shows the list of tasks to be done." + "\n"
            + "    done <number> - marks the task of number <number> as completed." + "\n"
            + "    delete <number> - deletes the task of number <number> from the list." + "\n"
            + "    find <keywords> - finds and list out tasks containing the keywords <keywords>." + "\n"
            + "    bye - closes the program.";

    /**
     * Constructor of the help command.
     * @param fullcommand
     */
    public CommandHelp(String fullcommand) {
        string = fullcommand;
    }

    /**
     * Executes the help command.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString(helppage);
    }

    /**
     * Executes the help command in the GUI.
     * @param tasks the task list
     * @param storage the storage writer
     * @return the help page.
     * @throws DukeException
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) throws DukeException {
        return helppage;
    }
}
