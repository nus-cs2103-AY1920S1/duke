class ExitCommand extends Command {

    ExitCommand() {
        super();
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printBye();
        super.isExit = true;
    }
}