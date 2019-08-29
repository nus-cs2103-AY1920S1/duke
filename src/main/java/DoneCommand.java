public class DoneCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        int taskNo = ui.readIndex();
        if (taskNo < 0 || taskNo > tasks.getTaskList().size()) {
            throw new DukeException("Task not found");
        }
        tasks.setDoneTask(taskNo);
    }
}
