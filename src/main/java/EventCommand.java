public class EventCommand extends AddCommand {
    String rawString;

    public EventCommand(String rawString) {
        this.rawString = rawString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        String remove_command = rawString.replaceFirst("event ", "");
        String[] splited = remove_command.split(" /at ");
        Event curr_task = new Event(splited[0], splited[1]);
        tasks.add(curr_task);
        super.addCommandUpdateState();
    }
}