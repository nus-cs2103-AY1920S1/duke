public class DoneTaskCommand extends Command {
    int index;
    DoneTaskCommand(int index) {
        super(4);
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.doneTask(index);
        ui.printString("Nice! I've marked this task as done: ");
        ui.printString( "   " + currTask.getTaskDetails());
    }

}
