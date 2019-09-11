public class UpdateDescriptionCommand extends UpdateCommand {

    private String taskDescription;
    private String newDescription;

    public UpdateDescriptionCommand(String taskDescription, String newDescription) {
        this.taskDescription = taskDescription;
        this.newDescription = newDescription;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        try {
            task = tasks.findTask(taskDescription);
            tasks.getList().remove(task);
            task.setDescription(newDescription);
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
        tasks.getList().add(task);
        storage.writeFile(tasks.getList());
        return toString() + ui.printTask(tasks.getList().size(), task);
    }

    @Override
    public String toString() {
        return "Got it. I've updated this task's description:\n";
    }
}
