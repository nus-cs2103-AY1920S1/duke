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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskListString = tasks.taskListString();

        try {
            storage.write(taskListString);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Bye. Hope to see you again soon!";
    }
}
