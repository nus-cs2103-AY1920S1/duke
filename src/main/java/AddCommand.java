/**
 * Add Command. Adds a task to the tasklist.
 */
public class AddCommand extends Command {
    /** Description of the task. */
    private String description;

    /**
     * Constructor. Sets isExit to false as it is not an exit command.
     * @param description Description of the task to be added.
     */
    public AddCommand(String description) {
        this.description = description;
        isExit = false;
    }

    /**
     * Behaviour of Add Command. Adds the Task.
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message = "";
        String indent = ui.getIndent();
        try {
            Task task = TaskFactory.create(description);
            taskList.addTask(task);
            message = task.message();
        } catch (TaskException e) {
            message = indent + e.getMessage();
        } finally {
            System.out.println(message);
            storage.write(taskList);
        }
    }
}
