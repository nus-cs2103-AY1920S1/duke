public class ListCommand extends Command {
    public ListCommand(String stringCommand){
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printMessage("Here are the tasks in your list:");
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
