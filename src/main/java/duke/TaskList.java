package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list from a list of task inputs.
     * @param taskInputs A list of task inputs, where each task input is represented as a list of words
     * @throws DukeException Error raised if tasks cannot be loaded
     */
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

    /**
     * Returns a task at the given position.
     * @param position Given position
     * @return Task at the given position
     * @throws DukeException Error raised if no task exists at the given position
     */
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

    /**
     * Deletes a task at the given position.
     * @param position Given position
     * @throws DukeException Error raised if no task exists at the given position
     */
    public void deleteTask(int position) throws DukeException {
        try {
            tasks.remove(position);
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    /**
     * Dumps the tasks in a save-compatible format for storage.
     * @return A list of strings, each representing a task in a save-compatible format
     */
    public List<String> dump() {
        ArrayList<String> dump = new ArrayList<>();
        for (Task task : tasks) {
            dump.add(task.toSaveString());
        }
        return dump;
    }
}
