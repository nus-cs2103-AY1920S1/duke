public class MarkAsDoneCommand extends Command {
    public MarkAsDoneCommand(String taskInformation) {
        super(taskInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TaskDoesNotExistException {
        int taskNumber = Integer.parseInt(commandInformation);
        tasks.markTaskAsCompleted(taskNumber, true);
        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
