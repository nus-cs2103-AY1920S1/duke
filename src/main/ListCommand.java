public class ListCommand extends Command{

    public void execute(TaskList task, Ui ui, Storage storage){
        ui.printList(task.getList());
    }
}
