package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;
import duke.exception.DukeException;

public class AddCommand extends Command {
    String[] parsedString;
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
                task = new Deadline(parsedString[1], parsedString[2]);
                break;
            case "event":
                task = new Event(parsedString[1], parsedString[2]);
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
}
