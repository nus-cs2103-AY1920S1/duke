public class DoneCommand extends Command {
    private boolean isDone;

    DoneCommand(String details, boolean isDone) {
        super(details);
        this.isDone = isDone;
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task selectedTask = tasks.get(taskIndex);
        if (isDone) {
            selectedTask.markAsDone();
            ui.showText("Nice! I've marked this task as done:"
                    + "\n  " + selectedTask.toString());
        } else {
            selectedTask.markAsUndone();
            ui.showText("Oh dear. I've marked this task as undone:"
                    + "\n  " + selectedTask.toString());
        }
        save(tasks, storage);
    }
}
