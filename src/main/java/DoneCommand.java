public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int inputIndex) throws DukeException {
        index = inputIndex;
    }

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (currentTaskList.getNoOfTask() < index || index <= 0) {
                throw new DukeException("index");
            }
            Task currentTask = currentTaskList.getTask(index - 1);
            currentTask.markAsDone();
            storage.updateTaskToFile(currentTaskList.getEntireList());
            return ui.printDone(currentTask);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}