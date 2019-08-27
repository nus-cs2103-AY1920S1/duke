public class ByeCommand extends Command {

    ByeCommand() {
        super("");
        this.isExit = true;
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        save(tasks, storage);
        ui.showText("Bye. Hope to see you again soon!");
    }
}
