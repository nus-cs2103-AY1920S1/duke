import java.util.Date;

public class AddDeadlineCommand implements Command {
    private String taskName;
    private Date deadline;

    public AddDeadlineCommand(String taskName, Date deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task temp = new TaskBuilder().type(TaskType.DEADLINE).description(taskName).deadline(deadline).build();
        taskList.addTask(temp);
        storage.save(taskList.getArr());
    }
}
