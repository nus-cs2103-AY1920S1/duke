public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String line) {
        super(line);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] arr = super.line.split(" /by ");
        Task deadline = new Deadline(arr[0], arr[1]);
        tasks.add(deadline);
        ui.showAddInformation(deadline.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }

}
