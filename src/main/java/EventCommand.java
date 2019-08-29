public class EventCommand extends AddCommand {
    public EventCommand() {
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String command = input.substring(5).trim();
        AddCommand.addTask(tasks, ui, storage, new EventsTask(command));
    }
}
