public class DoneCommand extends Command {
    private int index;

    DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TodoList tasks, Storage storage) {
        if (index > tasks.length()) throw new DukeException("OOPS!!! That's an invalid index");
        Task completed = tasks.markAsDone(this.index);
        super.run(tasks, storage);
        return "Nice! I've marked this task as done:\n  "
                + completed.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
