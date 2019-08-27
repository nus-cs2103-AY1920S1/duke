public class DeleteTaskCommand extends Command {
    int index;
    DeleteTaskCommand(int index){
        super(1);
        this.index = index-1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.deleteTask(index);
        ui.printString("Noted. I've removed this task: ");
        ui.printString( "   " + currTask);
        ui.printString(tasks.listDetails());
    }
}
