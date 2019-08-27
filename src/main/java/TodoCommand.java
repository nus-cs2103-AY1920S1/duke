public class TodoCommand extends Command {
    Task newTask;
    public TodoCommand(String taskDescription){
        newTask = new Todo(taskDescription);
    }
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
        storage.setChangedTrue();
    }

    public boolean isExit(){
        return false;
    }
}
