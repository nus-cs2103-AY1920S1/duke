package duke.tasklist;

import duke.task.Task;
import duke.dukeexception.DukeException;

import java.util.ArrayList;

/**
 * A taskList storage an ArrayList of task of the user.
 * @author Yang Shuting
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Construct a TaskList from existing list.
     * @param tasks  ArrayList of Task.
     * @throws DukeException thrown when the arrayList is empty.
     */
    public TaskList(ArrayList<Task> tasks) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Empty");
        }
        assert !tasks.isEmpty();
        this.tasks = new ArrayList<>(tasks);
        assert this.tasks.equals(tasks) && this.tasks != tasks;
    }

    /**
     * duplicate an return a copy of the list.
     * @return a copy of the list
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> copy = new ArrayList<>(tasks);
        assert copy.equals(tasks) && copy != tasks;
        return copy;
    }

    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * deletes a task from the task list.
     * @param index the sequence number of the task in the list
     * @return the task that has been deleted
     */
    public Task deleteFromList(int index) {
        assert index < tasks.size() : index;
        Task tk = tasks.get(index);
        //removed msg
        tasks.remove(index);
        Task.reduceByOne();
        return tk;
    }
}

