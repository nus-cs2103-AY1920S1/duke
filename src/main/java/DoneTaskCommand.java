public class DoneTaskCommand extends Command {
    int index;


    /**
     * Done task Command
     * @param index index on the list you wish to make done
     */

    DoneTaskCommand(int index) {
        super(4);
        this.index = index - 1;
    }

    /**
     * This makes the task done and print the relevant information
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.doneTask(index);
        ui.printString("Nice! I've marked this task as done: ");
        ui.printString( "   " + currTask.getTaskDetails());
    }

}
