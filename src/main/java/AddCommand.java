import java.io.IOException;

public class AddCommand extends Command {
    protected String description;
    // Whether command has meta info (like /by, /at)
    protected boolean hasSubCommand;

    /**
     * Constructor for commands adding tasks to list
     * @param commandWord keyword
     * @param description description of task to add
     * @param hasSubCommand whether it has a subcommand like '/by', '/at'
     */
    public AddCommand(String commandWord, String description, boolean hasSubCommand) {
        super(commandWord);
        this.description = description;
        this.hasSubCommand = hasSubCommand;
    }

    /**
     * Executes commands <code>todo</code>.
     * Adds ToDo task to list, saves new list to txt file, shows user message
     * @param taskList AL of tasks
     * @param ui Deals with console input and outputs
     * @param storage Deals with data in hard disk
     * @throws IOException exception
     * @throws DukeException If no description of ToDo task provided
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);
        storage.save(taskList.getTaskArr());
        ui.showAddTaskMessage(newTask, taskList.getTaskArr());
    }

    public void print() {
        super.print();
        System.out.println("Command description: " + description);
        System.out.println("Has subcommand: " + hasSubCommand);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        // Not even the same class
        if (!(o instanceof AddCommand)) {
            return false;
        }
        AddCommand c = (AddCommand) o;
        return c.description == description
                && c.hasSubCommand == hasSubCommand;
    }
}
