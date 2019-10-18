package duke.task;

import duke.DukeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of Tasks.
 */
public class TaskList implements Serializable {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
        // No-argument constructor for Serializable
    }

    /**
     * Constructs a TaskList with a shallow copy of the given List of Tasks.
     *
     * @param tasks  the List of Tasks this TaskList should contain
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Appends the specified Task to the end of this TaskList.
     *
     * @param t  the Task to be appended to this TaskList
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Marks the Task at the specified index as done.
     * The index is one-based and is as given by the {@link #toString()} method.
     *
     * @param oneIndex  one-based index of the Task to mark
     * @throws DukeException  if there is no Task with the specified index or if the Task is already done
     */
    public void markDone(int oneIndex) throws DukeException {
        tasks.get(oneIndex - 1).markDone();
    }

    /**
     * Removes the Task at the specified index from the list.
     * The index is one-based and is as given by the {@link #toString()} method.
     *
     * @param oneIndex  one-based index of the Task to remove
     * @return  the removed Task
     */
    public Task delete(int oneIndex) {
        return tasks.remove(oneIndex - 1);
    }

    /**
     * Returns the Task at the specified index.
     * The index is one-based and is as given by the {@link #toString()} method.
     *
     * @param oneIndex  one-based index of the Task to return
     * @return  the Task with the specified index
     */
    public Task get(int oneIndex) {
        return tasks.get(oneIndex - 1);
    }

    /**
     * Returns the number of Tasks in the list.
     *
     * @return  the number of Tasks in this list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a list of one-based indices of the Tasks in this list matching the specified keyword.
     * Searches the String returned by each Task's {@link Task#toString()} method.
     * The search can be either case-sensitive or non-case-sensitive.
     *
     * @param keyword          String to search for
     * @param isCaseSensitive  true if the search is to be case-sensitive, false if not
     * @return  list of one-based indices of the Tasks matching the keyword
     */
    public List<Integer> findKeywordOneIndices(String keyword, boolean isCaseSensitive) {
        if (!isCaseSensitive) {
            keyword = keyword.toLowerCase();
        }

        List<Integer> foundTaskIndices = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            if (!isCaseSensitive) {
                taskString = taskString.toLowerCase();
            }
            if (taskString.contains(keyword)) {
                foundTaskIndices.add(i + 1);
            }
        }
        return foundTaskIndices;
    }

    /**
     * Returns a list of Tasks in this list matching the specified keyword.
     * Searches the String returned by each Task's {@link Task#toString()} method.
     * The search can be either case-sensitive or non-case-sensitive.
     *
     * @param keyword          String to search for
     * @param isCaseSensitive  true if the search is to be case-sensitive, false if not
     * @return  list Tasks matching the keyword
     */
    public List<Task> findKeywordTasks(String keyword, boolean isCaseSensitive) {
        return findKeywordOneIndices(keyword, isCaseSensitive)
                .stream()
                .map(oneIndex -> get(oneIndex))
                .collect(Collectors.toList());
    }

    /**
     * Returns a human-readable string representation of this list.
     * Tasks are prefixed with a one-based index.
     * Each Task is followed by a newline character.
     *
     * @return  a String representing this list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", (i + 1), tasks.get(i).toString()));
        }
        return sb.toString();
    }
}
