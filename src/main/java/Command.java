public abstract class Command {
    abstract protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit(){
        return this instanceof ExitCommand;
    }
}

class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.rewrite(tasks.getSerialized());
        ui.show("Got it. I've added this task:\n  " +
                task +
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}

class ExitCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.show("Bye. Hope to see you again soon!");
    }
}

class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(String command) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(command.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Task t = tasks.remove(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Noted. I've removed this task:\n  " +
                t +
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}

class DoneCommand extends Command{
    private int index;

    public DoneCommand(String command) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(command.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Task t = tasks.setDone(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Nice! I've marked this task as done:\n" + t);
    }
}

class ListCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.toUiString());
    }
}