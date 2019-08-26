public class DeleteCommand extends Command {

    int taskNo;

    public DeleteCommand(int taskNo){
        this.taskNo = taskNo;
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task task = taskList.delete(taskNo);

        if (task != null) {
            ui.printOutput("  " + task,
                    "Noted. I've removed this task: ",
                    taskList.getTaskList().size());
            storage.save(taskList.getTaskList());
        }
    }
}
