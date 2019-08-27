import java.io.IOException;
import java.util.List;

// Contains information on a user input
public class Command {
    protected String command; // list, done, bye, todo, deadline, event
    protected boolean isStillInProgram; // Whether command terminates program or not
    public Command(String commandWord) {
        this.command = commandWord;
        this.isStillInProgram = true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
    }

    public boolean toContinueProgram() {
        return isStillInProgram;
    }
}

class AddCommand extends Command {
    protected String description;
    // Whether command has meta info (like /by, /at)
    protected boolean hasSubCommand;
    public AddCommand(String commandWord, String description, boolean hasSubCommand) {
        super(commandWord);
        this.description = description;
        this.hasSubCommand = hasSubCommand;
    }

    // Only todo
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);
        storage.save(taskList.getTaskArr());
        ui.showAddTaskMessage(newTask, taskList.getTaskArr());
    }
}

class SubCommand extends AddCommand {
    protected String subCommand, subDescription;
    public SubCommand(String commandWord, String description,
                      String subCommandWord, String subDescription) {
        super(commandWord, description, true);
        this.subCommand = subCommandWord;
        this.subDescription = subDescription;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task newTask = this.command.equals("deadline") ?
                new Deadline(this.description, this.subDescription) :
                new Event(this.description, this.subDescription);
        taskList.addTask(newTask);
        storage.save(taskList.getTaskArr());
        ui.showAddTaskMessage(newTask, taskList.getTaskArr());
    }

}

class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            ui.showMessage("You have no tasks yet!");
        } else {
            ui.showTasks(taskList);
        }
    }
}

class DeleteCommand extends Command {
    protected int deleteIdx;
    public DeleteCommand(int deleteTaskIdx) {
        super("delete");
        this.deleteIdx = deleteTaskIdx;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task deletedTask = taskList.deleteTask(deleteIdx);
        storage.save(taskList.getTaskArr());
        ui.showDeleteTaskMessage(deletedTask, taskList.getTaskArr());
    }
}

class DoneCommand extends Command {
    protected int doneIdx;
    public DoneCommand(int doneTaskIdx) {
        super("done");
        this.doneIdx = doneTaskIdx;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task doneTask = taskList.markTaskDone(doneIdx);
        storage.save(taskList.getTaskArr());
        ui.showMarkTaskDoneMessage(doneTask);
    }
}

class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
        this.isStillInProgram = false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
