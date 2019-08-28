public class DoneCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    public DoneCommand(String stringCommand) {
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        ui.printMessage("Nice! I've marked this task as done: ");
        taskList.done(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
