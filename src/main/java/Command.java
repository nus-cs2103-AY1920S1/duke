import java.io.IOException;
import java.text.ParseException;

abstract class Command {

    /**
     * constructor.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException when input is different from required.
     */
    abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, ParseException;

    /**
     * whether is it an exit command
     * @return true when it is an exit command, false otherwise.
     */
    boolean isExit() {
        return (this instanceof ExitCommand);
    }
}

class AddCommand extends Command {
    private Task t;

    /**
     * constructor.
     * @param t is the task.
     */
    AddCommand(Task t) {
        this.t = t;
    }

    /**
     * add task and print out information.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws IOException when input is different from required.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(t);
        storage.save();
        return "     Got it. I've added this task:\n" +
        "       " + t + "\n"
        + "      Now you have " + tasks.getSize() + " tasks in the list.";
    }
}

class DeleteCommand extends Command {
    private int index;

    /**
     * constructor
     * @param index to know which position to delete.
     */
    DeleteCommand(int index) {
        assert index > 0 : "index of task cannot be negative";
        this.index = index;
    }

    /**
     * delete task and print out information.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException when input is different from required.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        Task t = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save();
        return "     Noted. I've removed this task:\n" +
        "       " + t + "\n" +
        "      Now you have "  + tasks.getSize() + " tasks in the list.";
    }

}

class ExitCommand extends Command {

    /**
     * exit the bot and prints good bye message.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "     Bye. Hope to see you again soon!";
    }
}

class ListCommand extends Command {

    /**
     * print out the list of tasks.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String print = "     Here are the tasks in your list:\n";
        print = print + tasks.printList();
        return print;
    }
}

class DoneCommand extends Command {
    private int index;

    DoneCommand(int index) {
        assert index > 0 : "index of task cannot be negative";
        this.index = index;
    }

    /**
     * mark a task as done and print out the information.
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException when input is different from required.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        String print = "";
        Task t = tasks.getTask(index);
        t.markAsDone();
        storage.save();
        return "     Nice! I've marked this task as done: \n" +
        "       " + t;
    }
}

class FindCommand extends Command {
    private String keyword;
    FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * prints out the list of tasks with the keyword
     * @param tasks is the list of tasks.
     * @param ui is the UI class.
     * @param storage is the Storage class.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "     Here are the matching tasks in your list:" +
        tasks.printListWithKeyword(keyword);
    }
}

class PostponeCommand extends Command {
    private int index;
    private String time;

    /**
     * constructor.
     * @param index for the task to postpone to.
     * @param time for the task to postpone to.
     */
    PostponeCommand(int index, String time) {
        assert index > 0 : "index of task cannot be negative";
        this.index = index;
        this.time = time;
    }

    /**
     * postpone the task.
     * @param tasks   is the list of tasks.
     * @param ui      is the UI class.
     * @param storage is the Storage class.
     * @throws DukeException when input is different from required.
     * @throws IOException   when input is different from required.
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, ParseException {
        if (index >= tasks.getSize()) {
            throw new NoTaskException();
        }
        Task t = tasks.getTask(index);
        t.postpone(time);
        storage.save();
        return "     Noted. I've postponed this task:\n" +
                "       " + t;
    }
}