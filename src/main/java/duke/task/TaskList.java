package duke.task;

import duke.Parser;
import duke.exception.DukeIndexOutOfBoundsException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static duke.task.TaskType.DEADLINE_OVERDUE;
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
     * Constructs a 1-indexed task list with the provided tasks.
     *
     * @param tasks the list of tasks used to generate the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this();
        if (!tasks.isEmpty() && tasks.get(0) == null) {
            // adding 1-indexed list of tasks
            this.tasks.addAll(1, tasks);
        } else {
            // adding a 0-indexed list of tasks
            this.tasks.addAll(tasks);
        }
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
    public HashMap<TaskType, TaskList> filter(TaskType type) {
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
        return sortByType(newList, type);
    }

    /**
     * Arranges the tasks into a hash map of (task type : task) list of that task type.
     *
     * @param list the task list containing only specified tasks.
     * @param type the type present in the task list we sort by.
     * @return the hash map of arranged tasks.
     */
    private HashMap<TaskType, TaskList> sortByType(TaskList list, TaskType type) {
        HashMap<TaskType, TaskList> out = new HashMap<>();
        switch (type) {
        case EVENT:
            ArrayList<Task> eventList = sortEvents(list.getTaskList());
            out.put(EVENT, new TaskList(eventList));
            return out;
        case DEADLINE:
            HashMap<TaskType, ArrayList<Task>> h = extractTasks(list.getTaskList());
            out.put(DEADLINE, new TaskList(sortDeadlines(h.get(DEADLINE))));
            out.put(DEADLINE_OVERDUE, new TaskList(sortDeadlines(h.get(DEADLINE_OVERDUE))));
            return out;
        case TODO:
            // Todos don't need to be sorted
            out.put(TODO, new TaskList(list.getTaskList()));
            break;
        case ALL:
            HashMap<TaskType, ArrayList<Task>> h2 = extractTasks(list.getTaskList());
            // add deadlines first, then events, then todos. Todos aren't sorted (yet)
            out.put(DEADLINE, new TaskList(sortDeadlines(h2.get(DEADLINE))));
            out.put(DEADLINE_OVERDUE, new TaskList(sortDeadlines(h2.get(DEADLINE_OVERDUE))));
            out.put(EVENT, new TaskList(sortEvents(h2.get(EVENT))));
            out.put(TODO, new TaskList(h2.get(TODO)));
            return out;
        default:
            assert false; // shouldn't reach here
        }
        return out;
    }

    private HashMap<TaskType, ArrayList<Task>> extractTasks(ArrayList<Task> list) {
        // separate into overdue deadlines and on-time deadlines
        ArrayList<Task> overdue = new ArrayList<>();
        ArrayList<Task> onTime = new ArrayList<>();
        ArrayList<Task> events = new ArrayList<>();
        ArrayList<Task> todos = new ArrayList<>();
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        for (Task task: list) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                // overdue if supposed deadline is earlier than now
                if (d.getZonedDateTime().compareTo(now) < 0) {
                    overdue.add(d);
                } else {
                    onTime.add(d);
                }
            } else if (task instanceof Event) {
                events.add(task);
            } else if (task instanceof Todo) {
                todos.add(task);
            }
        }
        HashMap<TaskType, ArrayList<Task>> h = new HashMap<>();
        h.put(DEADLINE, onTime);
        h.put(DEADLINE_OVERDUE, overdue);
        h.put(EVENT, events);
        h.put(TODO, todos);
        return h;
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

    /**
     * Checks if the task list is empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return count() == 0;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
