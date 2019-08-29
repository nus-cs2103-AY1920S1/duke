public class DoneCommand extends Command {
    private String taskId;

    public DoneCommand(String idString) {
        this.taskId = idString;
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskId.equals("")) {
            throw new NoIDGivenException("done");
        }
        int id = Parser.parseStrToInt(this.taskId);

        if (id > tasks.getSize() || id < 1) {
            throw new InvalidIDException(this.taskId);
        }

        Task task = tasks.markDone(id);

        storage.updateFile(tasks);

        ui.dukeRespond("Nice! I've marked this task as done:",
                "  " + task.toString());
    }
}
