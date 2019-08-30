
public class DoneCommand extends Command {
    int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        taskList.markTaskDone(taskNum);
        Task updatedTask = taskList.getTask(taskNum);
        storage.updateText(taskNum);
        ui.printText("Nice! I've marked this task as done: \n" + updatedTask);
        storage.updateText(taskNum);


    }

    public boolean isExit() {
        return false;
    }
}