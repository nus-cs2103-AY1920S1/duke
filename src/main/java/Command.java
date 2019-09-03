public abstract class Command {
    int actionType; // -1=>null 0=>add 1=>delete 2=>list 3=>exit 4=>done 5=>find
    Task task;
    Command(int actionType) {
        this.actionType = actionType;
    }

    /**
     * This is an abstract method that all tasks will inherit from
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * This task to check if the command is an exit command
     * @return a boolean stating if the app should be exited
     */
    boolean isExit(){
        return actionType == 3;
    }
}
