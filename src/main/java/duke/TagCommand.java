package duke;

public class TagCommand extends Command {
    private int index;
    private String tag;

    public TagCommand(int index, String tag) {
        super();
        this.index = index;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! duke.Task not found.");
        }
        Task task = tasks.getTask(index);
        task.addTag(tag);
        storage.writeToHardDisk(tasks);
        return ui.printTagMessage(tag, task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
