package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the list of completed and uncompleted tasks.
 */
public class TaskList {

    /**
     * Represents the list of tasks.
     */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * This method adds the <code>task</code> into the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * This method clears all the tasks that is currently in the tasklist.
     */
    public void deleteAll() {
        this.tasks.clear();
    }

    /**
     * This method removes the <code>task</code> from the task list.
     *
     * @param index The zero-based index of the task to be deleted.
     * @return The task that was removed in a string format.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public String delete(int index) throws DukeException {
        try {
            return this.tasks.remove(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * This method marks the specified task in the task list as done.
     *
     * @param taskNumber The task to be marked as done.
     */
    public void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markedAsDone();
    }

    /**
     * This method retrieves all the task from the task list.
     *
     * @return The task lists.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * This method when called will go through <code>tasks</code>
     * to find <code>Task</code> with the keyword specified <code>word</code>.
     *
     * @param word Keyword to search.
     * @return ArrayList of tasks.
     * @throws DukeException when there is no tasks with the <code>word</code> found in task list.
     */
    public ArrayList<Task> findTaskWithWord(String word) throws DukeException {
        assert (tasks != null && tasks.size() != 0) :
                "You have no tasks in your tasks list, HOW AM I GOING TO FIND THIS?!?!";
        ArrayList<Task> tasksWithWord = new ArrayList<>();
        int numberOfTask = this.tasks.size();
        for (int i = 0; i < numberOfTask; i++) {
            Task task = this.tasks.get(i);
            if (task.getDescription().toLowerCase().contains(word)) {
                tasksWithWord.add(task);
            }
        }
        if (tasksWithWord.size() == 0) {
            throw new DukeException("No tasks with '" + word + "' found in your tasklist!");
        } else {
            return tasksWithWord;
        }
    }

    /**
     * This method when called will sort either the deadlines or the
     * events in chronological order.
     *
     * @param word Task type deadline/event to sort
     * @return The list of sorted task
     * @throws DukeException If there are no deadlines or events to be sorted.
     */

    public ArrayList<? extends Task> findTaskOfType(String word) throws DukeException {
        assert (tasks != null && tasks.size() != 0) :
                "You have no tasks in your tasks list, HOW AM I GOING TO FIND THIS?!?!";
        ArrayList<Deadline> deadlines = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();
        int numberOfTask = this.tasks.size();
        switch (word) {
        case "deadline": {
            for (int i = 0; i < numberOfTask; i++) {
                Task task = this.tasks.get(i);
                if (task instanceof Deadline) {
                    deadlines.add((Deadline) task);
                    this.tasks.remove(task);
                    numberOfTask--;
                    i--;
                }
            }
            break;
        }
        case "event": {
            for (int i = 0; i < numberOfTask; i++) {
                Task task = this.tasks.get(i);
                if (task instanceof Event) {
                    events.add((Event) task);
                    this.tasks.remove(task);
                    numberOfTask--;
                    i--;
                }
            }
            break;
        }
        default: {
            throw new DukeException(
                    "Invalid tasks to be sorted. You can only sort deadlines and events");
        }
        }
        if (deadlines.size() == 0 && events.size() == 0) {
            throw new DukeException("No task of type '" + word + "' found in your tasklist!");
        } else if (deadlines.size() == 0) {
            Collections.sort(events);
            return events;
        } else {
            Collections.sort(deadlines);
            return deadlines;
        }
    }


}