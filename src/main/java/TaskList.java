import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<List<String>> taskInputs) throws DukeException {
        this.tasks = new ArrayList<>();
        for (List<String> taskInput : taskInputs) {
            Task task;
            switch (taskInput.get(0)) {
            case "T":
                task = new Todo(taskInput.get(2));
                break;
            case "D":
                task = new Deadline(taskInput.get(2), taskInput.get(3));
                break;
            case "E":
                task = new Event(taskInput.get(2), taskInput.get(3));
                break;
            default:
                throw new DukeException("Could not load your tasks!");
            }
            if (taskInput.get(1).equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task getTask(int position) throws DukeException {
        try {
            return tasks.get(position);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int position) throws DukeException {
        try {
            Task task = tasks.get(position);
            tasks.remove(position);
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    public List<String> dump() {
        ArrayList<String> dump = new ArrayList<>();
        for (Task task : tasks) {
            dump.add(task.toSaveString());
        }
        return dump;
    }
}
