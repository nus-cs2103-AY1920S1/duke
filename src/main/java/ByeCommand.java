public class ByeCommand extends Command {

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        return ui.printBye();
    }
}