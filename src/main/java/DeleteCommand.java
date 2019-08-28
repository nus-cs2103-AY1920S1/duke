public class DeleteCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    public DeleteCommand(String stringCommand) {
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        taskList.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
