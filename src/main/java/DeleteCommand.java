public class DeleteCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    /**
     * Constructor for DeleteCommand
     * @param stringCommand
     */
    public DeleteCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        ui.printDeletedMessage();
        taskList.delete(index);
        ui.printNumberOfTasks(taskList);
        ui.printTask(taskList.getTasks().get(index-1));
    }

    /**
     * Checks if Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
