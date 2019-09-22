package duke.task;

import duke.exception.DukeException;
import duke.tag.Tag;
import duke.tag.TagManager;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents a list of every task created by the user.
 */
public class TaskList {
    /** ArrayList of tasks created by the user in this TaskList.*/
    private ArrayList<Task> tasks;

    /** TagManager to manage all the tags created in this list of task. */
    private TagManager tm = new TagManager();

    /**
     * Initializes a TaskList object with the specified list of tasks.
     * Also initialises all the tags created.
     * @param tasks an existing list of tasks which will be initialized with the TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        tm.initTags(this);
    }

    /**
     * Initializes a TaskList object with an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a specified task to the list as well as the tags.
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        tm.putTags(task.getTagSet());
    }

    /**
     * Deletes or adds a tag to the task.
     * @param ind index of task to be altered.
     * @param tag tag to be added or deleted.
     * @param isAdding boolean to represent whether to add or delete the tag.
     * @throws DukeException if the task does not have the tag to delete or if the task already
     *     has the tag.
     */
    public void alterTaskTag(int ind, Tag tag, boolean isAdding) throws DukeException {
        Task task = tasks.get(ind);
        if (isAdding) {
            task.addTag(tag);
            tm.addTag(tag);
        } else {
            task.rmTag(tag);
            tm.rmTag(tag);
        }
    }

    /**
     * Deletes a specified task from the list as well as the tags.
     * @param ind index of the task to be deleted from the list.
     */
    public void deleteTask(int ind) {
        Task t = tasks.get(ind);
        tm.decreaseCount(t.getTagSet());
        tasks.remove(ind);
    }

    /**
     * Returns a list of all tags created.
     * @return list of all tags.
     */
    public String getAllTags() {
        return tm.toString();
    }

    /**
     * Returns a specified task.
     * @param ind index of the task to be returned.
     * @return Specified task.
     */
    public Task getTask(int ind) {
        return tasks.get(ind);
    }

    /**
     * Returns the size of the list.
     * @return Size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns list in stream format.
     * @return stream of the list.
     */
    public Stream<Task> getStream() {
        return tasks.stream();
    }

    /**
     * Returns a string representation of the tasklist.
     * @return string representation of tasklist.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        return sb.toString();
    }
}
