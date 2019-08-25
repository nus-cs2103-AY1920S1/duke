import java.io.IOException;

public class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException{

    }

    public boolean isExit() {
        return (this instanceof ExitCommand);
    }
}

class AddCommand extends Command {
    private Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.addTask(t);
        storage.save();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t);
        System.out.println("      Now you have " + tasks.getSize() + " tasks in the list.");
    }
}

class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        Task t = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + t);
        System.out.println("      Now you have "  + tasks.getSize() + " tasks in the list.");
    }

}

class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(
                "     Bye. Hope to see you again soon!");
    }
}

class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("     Here are the tasks in your list:");
        tasks.printList();
    }
}

class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        Task t = tasks.getTask(index);
        t.markAsDone();
        storage.save();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + t);
    }

}