public class DeleteCommand extends Command {
    protected String num;

    public DeleteCommand(String num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int numChange = Integer.parseInt(this.num);
        if (numChange > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Task " + numChange + " does not exist.");
        }
        Task toRemove = tasks.get(numChange - 1);
        tasks.remove(numChange - 1);
        ui.showLine("Noted. I've removed this task:" + "\n" + toRemove.toString() +
                    "\n" + "Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTaskList());
    }

    public boolean isExit() {
        return false;
    }
}
