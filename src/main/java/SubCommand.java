import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Commands which adds a task to list,
 * where task has meta information.
 */
public class SubCommand extends AddCommand {

    protected String subCommand, subDescription;

    public SubCommand(String commandWord, String description,
                      String subCommandWord, String subDescription) {
        super(commandWord, description, true);
        this.subCommand = subCommandWord;
        this.subDescription = subDescription;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException, ParseException {
        Task newTask;
        switch (command) {
        case "deadline":
            newTask = new Deadline(description, subDescription);
            break;
        case "event":
            newTask = new Event(description, subDescription);
            break;
        default:
            throw new DukeException("Something went wrong :-( No such command with subcommand!");
        }

        taskList.addTask(newTask);
        ArrayList<Task> taskArr = taskList.getTaskArr();
        storage.save(taskArr);
        ui.showAddTaskResponse(newTask, taskArr);

    }

    public void print() {
        super.print();
        System.out.println("Subcommand: " + subCommand);
        System.out.println("Subcommand description: " + subDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        // Not even the same class
        if (!(o instanceof SubCommand)) {
            return false;
        }
        SubCommand sc = (SubCommand) o;
        return sc.subCommand == subCommand &&
                sc.subDescription == subDescription;
    }

}