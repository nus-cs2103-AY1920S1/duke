public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(Message.MESSAGE_INVALID_TASK_INDEX);
        }
        Task task = taskList.delete(index);
        storage.save(taskList.getSimplifiedTaskRepresentations());
        ui.showDeletion(task);
        ui.showTaskSize(taskList);
    }
}
