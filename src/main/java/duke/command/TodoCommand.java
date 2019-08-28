package duke.command;

import duke.command.Command;
import duke.exception.DukeException;
import duke.initials.Todo;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.SimpleDateFormat;

public class TodoCommand extends Command {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showLine();
            String remaining = ui.getRemainingWords().trim();
            if (remaining.equals("")) {
                throw new DukeException("☹OOPS!!! The Description of a todo cannot be empty");
            }
            Todo t = new Todo(remaining.substring(0));
            tasks.getTaskArrayList().add(t);
            storage.writeData();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.getTaskArrayList().size() + " tasks in the list.");
            ui.showLine();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
