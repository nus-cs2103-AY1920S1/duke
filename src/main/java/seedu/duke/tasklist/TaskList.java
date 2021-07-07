package seedu.duke.tasklist;

import seedu.duke.task.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class contains the list of Tasks (stored as an ArrayList) and methods to add, delete Tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructor for TaskList class. Returns a TaskList object.
     *
     * @param taskList An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Returns the number of Task objects currently stored in the list.
     *
     * @return Number of Task objects.
     */
    public int getSize() {
        return getArrayList().size();
    }

    /**
     * Returns the object Task at the specified int index.
     *
     * @param i Integer index.
     * @return Task object which is located at index i.
     */
    public Task getTask(int i) {
        assert i >= 0 : "Task index should be >= 0";
        return getArrayList().get(i);
    }

    /**
     * Adds a new Task object into the list.
     *
     * @param newTask Task object that needs to be added into the list.
     */
    public void addTask(Task newTask) {
        getArrayList().add(newTask);
    }

    /**
     * Deletes a Task object from a specified index.
     *
     * @param i Integer index of where the Task object (to be deleted) is in.
     */
    public void deleteTask(int i) {
        assert i >= 0 : "Task index should be >= 0";
        getArrayList().remove(i);
    }

    /**
     * Returns the ArrayList(Task) inside the TaskList object.
     * Is a getter function to get the private attribute, listOfTasks.
     *
     * @return ArrayList(Task) object.
     */
    public ArrayList<Task> getArrayList() {
        return this.listOfTasks;
    }

    /**
     * Returns another TaskLIst object which contains Task objects that is similar to the search string.
     *
     * @param searchTerm String that needs to be inside the Task description of similar Tasks.
     * @return Tasklist object, which has an ArrayList (Task) that only contains matching Task objects.
     */
    public TaskList findSimilarTasks(String searchTerm) {

        ArrayList<Task> listOfTasks = this.getArrayList();
        List<Task> t = listOfTasks.stream()
                .filter(o -> o.getTaskName().contains(searchTerm))
                .collect(Collectors.toList());

        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        Iterator iter = t.iterator();
        while (iter.hasNext()) {
            matchingTasks.add((Task) iter.next());
        }
        return new TaskList(matchingTasks);
    }

}
