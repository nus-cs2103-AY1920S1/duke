import java.io.IOException;

import java.util.ArrayList;

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

    public void print() {
        System.out.println("Command: " + command);
        System.out.println("Program continues after execution: " + isStillInProgram);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof Command)) { return false; }
        Command c = (Command) o;
        return c.command == command &&
                c.isStillInProgram == isStillInProgram;
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

    public void print() {
        super.print();
        System.out.println("Command description: " + description);
        System.out.println("Has subcommand: " + hasSubCommand);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof AddCommand)) { return false; }
        AddCommand c = (AddCommand) o;
        return c.description == description &&
                c.hasSubCommand == hasSubCommand;
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

    public void print() {
        super.print();
        System.out.println("Subcommand: " + subCommand);
        System.out.println("Subcommand description: " + subDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof SubCommand)) { return false; }
        SubCommand c = (SubCommand) o;
        return c.subCommand == subCommand &&
                c.subDescription == subDescription;
    }

}

class FindCommand extends Command {
    protected String keyword;
    public FindCommand(String findDescription) {
        super("find");
        this.keyword = findDescription;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasksArr = new ArrayList<Task>();
        for (Task currTask : taskList.getTaskArr()) {
            if (currTask.containsKeyword(keyword)) {
                matchingTasksArr.add(currTask);
            }
        }
        if (matchingTasksArr.size() == 0) {
            ui.showMessage("No matching tasks!");
        } else {
            ui.showMatchingKeywordTasks(matchingTasksArr);
        }

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
            ui.showAllTasks(taskList);
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

    public void print() {
        super.print();
        System.out.println("Index of task to delete: " + deleteIdx);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof DeleteCommand)) { return false; }
        DeleteCommand c = (DeleteCommand) o;
        return c.deleteIdx == deleteIdx;
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

    public void print() {
        super.print();
        System.out.println("Index of task to mark done: " + doneIdx);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof DoneCommand)) { return false; }
        DoneCommand c = (DoneCommand) o;
        return c.doneIdx == doneIdx;
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
