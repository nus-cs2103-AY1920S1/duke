public class DoneCommand extends Command {
    protected String num;

    public DoneCommand(String num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int numChange = Integer.parseInt(this.num);
        if (numChange > tasks.size()) {
            throw new DukeException("☹ OOPS!!! Task " + numChange + " does not exist.");
        } else {
            Task done = tasks.get(numChange - 1);
            done.markAsDone();
            ui.showLine("Nice! I've marked this task as done:" + "\n" + done.toString());
            storage.save(tasks.getTaskList());
        }
    }

    public boolean isExit() {
        return false;
    }
}

