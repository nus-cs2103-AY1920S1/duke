public class ListCommand extends Command {

    public ListCommand(String inputCommand){
        super(inputCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printRecord(tasks.getTaskList());
    }
}
