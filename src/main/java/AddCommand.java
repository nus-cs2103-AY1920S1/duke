class AddCommand extends Command {
    protected Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printAddTaskMessage(task.toString(), tasks.size());
    }
}