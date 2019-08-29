public class DeleteCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    public DeleteCommand(String stringCommand) {
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        ui.printDeletedMessage();
        taskList.delete(index);
        ui.printNumberOfTasks(taskList);
        ui.printTask(taskList.getTasks().get(index-1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
