public class DoneTaskCommand implements Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        // InputMismatchException is handled in the Ui class
        int whichTask = ui.readIndex();
        //IndexOutOfBoundsException is handled in the TaskList class
        taskList.markAsDone(whichTask);
    }
}
