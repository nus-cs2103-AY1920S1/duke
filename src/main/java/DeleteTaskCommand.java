public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand() {
        super(false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        int taskIndex = dataParser.getTaskIndex();
        Task deletedTask = taskList.deleteTask(taskIndex);
        ui.showDeletedTask(deletedTask);
        storage.save();
    }

}
