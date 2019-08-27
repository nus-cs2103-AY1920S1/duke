public class ListCommand extends Command{

    public boolean isExit(){
        return false;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printTasks(taskList);
    }
}
