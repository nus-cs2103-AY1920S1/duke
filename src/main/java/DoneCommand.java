class DoneCommand extends Command {
    protected int doneTask;

    DoneCommand(int doneTask) {
        super();
        this.doneTask = doneTask;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(this.doneTask);
        ui.printDone(tasks.get(this.doneTask).toString());
    }
}