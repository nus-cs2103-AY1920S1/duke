public class InvalidCommand extends Command {

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        return ui.printException(new DukeException("invalid"));
    }
}