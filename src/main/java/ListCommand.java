public class ListCommand extends Command {
    public ListCommand(String stringCommand){
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printListMessage();
        ui.printTaskList(taskList);
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
