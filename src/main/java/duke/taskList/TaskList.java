package duke.taskList;

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
     * @param list  ArrayList of Task.
     * @throws DukeException thrown when the arrayList is empty.
     */
    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.size() > 0) {
            this.tasks = list;
        } else {
            throw new DukeException("Empty");
        }
    }

    /**
     * duplicate an return a copy of the list
     * @return a copy of the list
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> copy = new ArrayList<>(tasks);
        return copy;
    }

    public void addToList(Task task) {
        tasks.add(task);
    }

    public Task deleteFromList(int index) {
        Task tk = tasks.get(index);
        //removed msg
        tasks.remove(index);
        Task.reduceByOne();
        return tk;
    }
}

