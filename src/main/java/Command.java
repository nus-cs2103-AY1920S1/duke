import java.time.LocalDateTime;

public class Command {
    protected String commandWord;
    protected String desc;
    protected int taskListIndex;
    protected LocalDateTime deadline;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected boolean isExit = false;
    protected Task task;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public Command(String commandWord, String desc) {
        this.commandWord = commandWord;
        this.desc = desc;
    }

    public Command(String commandWord, int taskListIndex) {
        this.commandWord = commandWord;
        this.taskListIndex = taskListIndex;
    }

    public Command(String commandWord, String desc, LocalDateTime deadline) {
        this.commandWord = commandWord;
        this.desc = desc;
        this.deadline = deadline;
    }

    public Command(String commandWord, String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.commandWord = commandWord;
        this.desc = desc;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (commandWord) {
        case "list":
            ui.showTasks();
            break;
        case "todo":
        case "deadline":
        case "event":
            switch (commandWord) {
            case "todo":
                task = new Todo(desc);
                break;
            case "deadline":
                task = new Deadline(desc, deadline);
                break;
            case "event":
                task = new Event(desc, startDateTime, endDateTime);
                break;
            }
            tasks.add(task);
            ui.showAddTaskMsg(task);
            storage.save(tasks);
            break;
        case "done":
            task = tasks.get(taskListIndex);
            task.setDone();
            ui.showTaskDoneMsg(task);
            storage.save(tasks);
            break;
        case "delete":
            task = tasks.remove(taskListIndex);
            ui.showTaskDelMsg(task);
            storage.save(tasks);
            break;
        case "bye":
            isExit = true;
            ui.showByeMsg();
            return;
        default:
            throw new DukeException("\u2754 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
