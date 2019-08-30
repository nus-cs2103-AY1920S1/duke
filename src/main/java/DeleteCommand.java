public class DeleteCommand extends Command {

    int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.deleteText(taskNum);

        Task removedTask = taskList.deleteTask(taskNum);
        ui.printText("Noted. I've removed this task:\n" + removedTask +
                "Now you have " + taskList.size() + " tasks in the list.");


    }

    public boolean isExit() {
        return false;
    }


}