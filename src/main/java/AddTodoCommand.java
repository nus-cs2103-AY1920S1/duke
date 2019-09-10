public class AddTodoCommand extends Command {
    private String instruction;

    /**
     * Represents the command of adding a todo task to tasklist.
     * @param instruction refers to the todo task
     */
    public AddTodoCommand(String instruction) {
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
        try {
            if (instruction.equals("") || instruction.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of todo cannot be empty");
            } else {
                tasks.addItemToList(new Todo(instruction));
                try {
                    storage.append(tasks.getLastItem());
                } catch (Exception exp) {
                    return ui.showErrorMsg(exp.getMessage());
                }
            }
        } catch (DukeException exp) {
            return ui.showErrorMsg(exp.getMessage());
        }

        return ui.showTaskAddedMsg(tasks.getLastItem(), tasks.getListSize());
    }

}
