public class ByeTaskCommand extends Command {
    /**
     * Constructor for ByeTaskCommand
     */
    ByeTaskCommand(){
        super(3);
    }

    /**
     * This method writes all the information in the TaskList to the duke.txt file
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskListString = tasks.taskListString();
        ui.printString("Bye. Hope to see you again soon!");
        try {
            storage.write(taskListString);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
