public class DoneCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    public DoneCommand(String stringCommand) {
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        ui.printDoneMessage();
        taskList.done(index);
        ui.printTask(taskList.getTasks().get(index-1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
