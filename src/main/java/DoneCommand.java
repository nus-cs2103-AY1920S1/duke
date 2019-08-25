class DoneCommand extends Command {
    private int zeroBasedIndex;

    DoneCommand(int oneBasedIndex) {
        this.zeroBasedIndex = oneBasedIndex - 1;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.markTaskAsDone(zeroBasedIndex);
        ui.printMessage("Nice! I've marked this task as done:\n\t\t" + tasks.get(zeroBasedIndex));
        storage.save(tasks.getAllTasks());
    }
}
