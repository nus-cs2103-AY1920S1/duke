import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(MyList taskList, UserInterface ui, Storage storage) throws TaskNotPresentException, FileSaveException {
        if (taskNum < 1 || taskNum > taskList.getNumTasks()) {
            throw new TaskNotPresentException();
        }
        Task task = taskList.getTask(taskNum);
        task = taskList.removeTask(taskNum);
        try {
            storage.updateList(taskList);
        } catch (IOException e) {
            throw new FileSaveException();
        }
        ui.printDeleteMsg(task, taskList);
    }
}
