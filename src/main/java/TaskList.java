import java.text.ParseException;
import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void done(int num) throws DukeException {
        if (num >= tasks.size() || num < 1) {
            throw new DukeException("Task number out of range.");
        }
        tasks.get(num - 1).isDone = true;
    }

    public void delete(int num) throws DukeException {
        if (num >= tasks.size() || num < 1) {
            throw new DukeException("Task number out of range.");
        }
        tasks.remove(num - 1);
    }

    public void add(String type, String description) throws DukeException {
        Task newTask;
        switch (type) {
            case "todo":
                newTask = new Todo(description);
                break;
            case "event":
                newTask = new Event(description);
                break;
            case "deadline":
                newTask = new Deadline(description);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        try {
            newTask.repr();
            tasks.add(newTask);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | ParseException ex) {
            throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
        }
    }

}