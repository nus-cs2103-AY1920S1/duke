public class AddEventCommand extends AddCommand {

    public AddEventCommand(String line) {
        super(line);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] arr = super.line.split(" /at ");
        Task event = new Event(arr[0], arr[1]);
        tasks.add(event);
        ui.showAddInformation(event.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }

}
