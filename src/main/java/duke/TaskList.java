package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    public ArrayList<Task> tasks;

    /**
     * Initiates a Task object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initiates a Task object.
     * @param tasks an ArrayList of existing tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks smoe tasks as done.
     * @param nums the numbers of tasks to be marked as done
     * @throws DukeException if num is out of range
     */
    public void done(Integer... nums) throws DukeException {
        for (int num : nums) {
            if (num > tasks.size() || num < 1) {
                throw new DukeException("Task number out of range.");
            }
            assert 0 < num && num <= tasks.size() : "invalid task number";
            tasks.get(num - 1).setDone(true);
        }
    }

    /**
     * Deletes some tasks.
     * @param nums the numbers of tasks to be deleted
     * @throws DukeException if num is out of range
     */
    public void delete(Integer... nums) throws DukeException {
        Arrays.sort(nums, Collections.reverseOrder());
        for (int num : nums) {
            if (num > tasks.size() || num < 1) {
                throw new DukeException("Task number out of range.");
            }
            assert 0 < num && num <= tasks.size() : "invalid task number";
            tasks.remove(num - 1);
        }
    }

    /**
     * Finds tasks that have matching words in their descriptions.
     * @param target searching term
     * @return TaskList of matching tasks
     */
    public TaskList find(String target) {
        ArrayList<Task> targets = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(target)) {
                targets.add(t);
            }
        }
        return new TaskList(targets);
    }

    /**
     * Adds a task.
     * @param type type of task to be added
     * @param description description of new task
     * @throws DukeException if type of task connot be recognised or task description cannot be parsed
     */
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
            throw new DukeException("Sorry Mirana, but I don't know what that means :-(");
        }
        try {
            newTask.repr();
            tasks.add(newTask);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | ParseException ex) {
            throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
        }
    }

}