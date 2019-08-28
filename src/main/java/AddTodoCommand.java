public class AddTodoCommand implements Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task temp = new TaskBuilder().type(TaskType.TODO).description(description).build();
        taskList.addTask(temp);
        storage.save(taskList.getArr());
    }
}
