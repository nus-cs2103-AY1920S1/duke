package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws Exception{
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! That number you put in does not exit");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
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

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        DeleteCommand c = (DeleteCommand) o;

        return this.command.equals(c.command);
    }
}
