public class ListCommand extends Command {

    ListCommand() {
        super("");
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showText("You have no tasks now. Hooray!");
        } else {
            ui.showList(tasks);
        }
    }
}
