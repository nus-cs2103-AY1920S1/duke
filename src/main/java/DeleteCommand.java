public class DeleteCommand extends Command {

    DeleteCommand(String details) {
        super(details);
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task deletedTask = tasks.remove(taskIndex);
        ui.showText("Noted. I've removed this task:"
                + "\n  " + deletedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        save(tasks, storage);
    }
}
