public class ListTaskCommand extends Command {

    public ListTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        ui.listTasks();
    }

}
