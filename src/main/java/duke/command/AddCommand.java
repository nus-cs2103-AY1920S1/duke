package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;
import duke.exception.DukeException;

import java.util.Arrays;
import java.util.Date;

public class AddCommand extends Command {
    private String[] parsedString;
    private Date date;

    public String[] getParsedString() {
        return parsedString;
    }

    public Date getDate() {
        return date;
    }

    public AddCommand(String[] command, Date date) {
        this.parsedString = command;
        this.date = date;
    }

    public AddCommand(String[] command) {
        this.parsedString = command;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception {
        Task task;
        switch (parsedString[0]) {
        case "todo":
            task = new ToDo(parsedString[1]);
            break;
        case "deadline":
            task = new Deadline(parsedString[1], date);
            break;
        case "event":
            task = new Event(parsedString[1], date);
            break;
        default:
            // if the user type anything besides the three types of item
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        System.out.println("Got it. I've added this task");
        System.out.println("  " + task.toString());
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list", tasks.size()));
        }
        storage.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    // Overriding equals() to compare two AddCommand objects
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof AddCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        AddCommand c = (AddCommand) o;

        // Compare the data members and return accordingly
        if (date != null) {
            return Arrays.equals(this.parsedString, c.parsedString)
                    && date.equals(c.date);
        } else {
            return Arrays.equals(this.parsedString, c.parsedString);
        }
    }
}
