public class DeleteCommand extends Command {
    private String taskId;

    public DeleteCommand(String idString) {
        this.taskId = idString;
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskId.equals("")) {
            throw new NoIDGivenException("delete");
        }
        int id = Parser.parseStrToInt(this.taskId);

        if (id > tasks.getSize() || id < 1) {
            throw new InvalidIDException(this.taskId);
        }
        Task task = tasks.deleteTask(id);

        storage.updateFile(tasks);

        ui.dukeRespond("Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
