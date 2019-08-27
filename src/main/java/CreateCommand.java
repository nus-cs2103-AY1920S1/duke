public class CreateCommand extends Command {

    private String directive;
    private String description;
    private String addOn;

    public CreateCommand(String directive, String description) {
        this.directive = directive;
        this.description = description;
    }

    public CreateCommand(String directive, String description, String addOn) {
        this.directive = directive;
        this.description = description;
        this.addOn = addOn;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        boolean isAdd = true;
        Task task = new Task(description);
        switch (directive) {
        case "todo":
            task = new ToDo(description);
            break;
        case "deadline":
            task = new Deadline(description, addOn);
            break;
        case "event":
            task = new Event(description, addOn);
            break;
        default:
            isAdd = false;
        }
        if (isAdd) {
            taskList.addItem(task);
            ui.notifyTaskAdded(task, taskList.size());
            storage.addTaskToFile(task);
            return;
        }
        throw new DukeException("Error Executing Create Command");
    }
}
