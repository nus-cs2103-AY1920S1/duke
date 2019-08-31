public class EventCommand extends Command {
    private String description;
    private String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.addNewTask(new Event(this.description, this.at));
    }

    public boolean isExit() {
        return false;
    }
}
