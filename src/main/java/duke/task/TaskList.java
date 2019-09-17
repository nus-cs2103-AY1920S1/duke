package duke.task;

import duke.Parser;
import duke.exception.DukeIndexOutOfBoundsException;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import static duke.task.TaskType.TODO;
import static duke.task.TaskType.EVENT;
import static duke.task.TaskType.DEADLINE;

/**
 * Represents a list of tasks in Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        tasks.add(null); // leave index 0 unused for clarity.
    }

    /**
     * Add a task to the list.
     *
     * @param task the task to add.
     * @return the task added.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the list.
     *
     * @param idx the index of the task to delete (as listed).
     * @return the deleted task.
     * @throws DukeIndexOutOfBoundsException if the index of the task to delete is not shown (as listed).
     */
    public Task delete(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException("Hmm... There's no such task index.");
        }
        return tasks.remove(idx);
    }

    /**
     * Gets a task from the list.
     *
     * @param idx the index of the task to get (as listed).
     * @return the task obtained.
     * @throws DukeIndexOutOfBoundsException if the index of the task to delete is not shown (as listed).
     */
    public Task get(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException("Hmm... There's no such task index.");
        }
        return tasks.get(idx);
    }

    /**
     * Finds all tasks matching a keyword given.
     *
     * @param keyword the keyword to search among task descriptions for.
     * @return the task list containing all matched tasks.
     */
    public TaskList find(String keyword) {
        TaskList newList = new TaskList();
        for (Task task: this.getTaskList()) {
            if (task != null) {
                if (task.getDescription().contains(keyword)) {
                    newList.add(task);
                }
            }
        }
        return newList;
    }

    /**
     * Gets an ordered task list filtered to specific task type.
     *
     * @param type the task type to filter by.
     * @return the filtered, ordered tasklist.
     */
    public TaskList filter(TaskType type) {
        TaskList newList = new TaskList();
        for (Task task: this.getTaskList()) {
            if (task == null
                || type == DEADLINE && !(task instanceof Deadline)
                || type == EVENT && !(task instanceof Event)
                || type == TODO && !(task instanceof Todo)) {
                continue;
            }
            newList.add(task);
        }
        // have the null value stay at 0-index to maintain 1-indexing
        newList.tasks = sortByType(newList.getTaskList(), type);
        return newList;
    }

    private ArrayList<Task> sortByType(ArrayList<Task> list, TaskType type) {
        switch (type) {
        case EVENT:
            return sortEvents(list);
        case DEADLINE:
            return sortDeadlines(list);
        case ALL:
            // sort by deadlines first, then
            ArrayList<Task> deadlineList = new ArrayList<>();
            ArrayList<Task> eventList = new ArrayList<>();
            ArrayList<Task> todoList = new ArrayList<>();
            for (Task t: list) {
                // account for 1-indexing where null is at 0th index
                if (t != null) {
                    if (t instanceof Deadline) {
                        deadlineList.add(t);
                    } else if (t instanceof Event) {
                        eventList.add(t);
                    } else if (t instanceof Todo) {
                        todoList.add(t);
                    }
                }
            }
            // we don't sort to-dos at all
            ArrayList<Task> sortedDeadlineList = sortDeadlines(deadlineList);
            ArrayList<Task> sortedEventList = sortEvents(eventList);
            // add deadlines first, then events, then todos
            ArrayList<Task> newList = new ArrayList<>();
            newList.add(null);
            newList.addAll(sortedDeadlineList);
            newList.addAll(sortedEventList);
            newList.addAll(todoList);
            return newList; // have list point at the updated list
        case TODO:
            break;
        default:
            assert false;
        }
        return list;
    }

    private ArrayList<Task> sortDeadlines(ArrayList<Task> list) {
        list.sort((d1, d2) -> {
            if (d1 == null && d2 == null) {
                return 0;
            }
            if (d1 == null) {
                return -1;
            }
            if (d2 == null) {
                return 1;
            }
            ZonedDateTime date1 = Parser.parseDateTime(((Deadline) d1).getDeadline(), DEADLINE);
            ZonedDateTime date2 = Parser.parseDateTime(((Deadline) d2).getDeadline(), DEADLINE);
            return date1.compareTo(date2);
        });
        return list;
    }

    private ArrayList<Task> sortEvents(ArrayList<Task> list) {
        // account for task lists being 1-indexed, with null in index 0
        list.sort((e1, e2) -> {
            if (e1 == null && e2 == null) {
                return 0;
            }
            if (e1 == null) {
                return -1;
            }
            if (e2 == null) {
                return 1;
            }
            return ((Event) e1).getPeriod().compareTo(((Event) e2).getPeriod());
        });
        return list;
    }

    /**
     * Marks a task as completed.
     *
     * @param idx the index of the task to mark as done (as listed).
     * @return the task marked as complete.
     * @throws DukeIndexOutOfBoundsException if the index of the task to delete is not shown (as listed).
     */
    public Task markAsDone(int idx) throws DukeIndexOutOfBoundsException {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException("Hmm... There's no such task index.");
        }
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public long count() {
        return tasks.size() - 1; // account for 1-indexing.
    }

    public boolean isEmpty() {
        return count() == 0;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
