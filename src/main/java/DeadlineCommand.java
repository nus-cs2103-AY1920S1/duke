public class DeadlineCommand extends Command {
    Task newTask;

    public DeadlineCommand(String description, String date, String timing){
        this.newTask = new Deadline(description, date, timing);
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
