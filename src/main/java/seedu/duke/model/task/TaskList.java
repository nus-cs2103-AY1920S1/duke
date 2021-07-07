package seedu.duke.model.task;

import java.util.ArrayList;
import java.util.Collections;

import seedu.duke.commons.exceptions.listexceptions.InvalidSortCriteriaException;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.storage.Storage;

/**
 * Defines a TaskList object that stores instances of Task.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList with the same contents as the TaskList of the given Storage.
     * @param storage Storage from which this TaskList is to copy its initial content of Tasks.
     */
    public TaskList(Storage storage) {
        this();
        TaskList sourceList = storage.getTaskList();
        for (int i = 0; i < sourceList.size(); i++) {
            this.taskList.add(sourceList.get(i).copy());
        }
        assert sourceList.size() == this.size() : "Size of TaskList copied from Storage differs from "
                + "size of original TaskList in Storage.";
    }

    /**
     * Creates a TaskList with an initial capacity of 100;
     * Initialises an ArrayList of Tasks with initial capacity of 100 to implement this TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Creates a copy of an existing TaskList.
     * @param taskList TaskList with contents to be copied into this TaskList.
     */
    public TaskList(TaskList taskList) {
        this();
        for (int i = 0; i < taskList.size(); i++) {
            this.add(taskList.get(i).copy());
        }
        assert taskList.size() == this.size();
    }

    /**
     * Removes the given Task from this TaskList.
     * @param task Task to be removed from this TaskList.
     */
    public void remove(Task task) {
        int sizeBefore = taskList.size();
        taskList.remove(task);
        int sizeAfter = taskList.size();
        assert sizeAfter != sizeBefore : "Task was called to be removed from TaskList but "
                + "size of TaskList remained unchanged.";
        assert sizeAfter == sizeBefore - 1 : "One item was meant to be removed from the TaskList "
                + "but the difference in sizes of the lists before and after is not 1.";
    }

    /**
     * Adds the given Task to this TaskList.
     * @param task Task to be added to this TaskList.
     */
    public void add(Task task) {
        int sizeBefore = taskList.size();
        taskList.add(task);
        int sizeAfter = taskList.size();
        assert sizeAfter != sizeBefore : "Task was called to be added to TaskList but "
                + "size of TaskList remained unchanged.";
        assert sizeAfter == sizeBefore + 1 : "One item was meant to be added to the TaskList "
                + "but the difference in sizes of the lists before and after is not 1.";
    }

    /**
     * Returns a done message after a / multiple Task(s) have been marked as done in this TaskList.
     * @param rangeList ArrayList of ranges, represented by ArrayLists containing two elements each.
     *                  First element represents starting index while second element represents ending index
     *                  (both inclusive).
     * @return A done message after a / multiple Task(s) have been marked as done from this TaskList.
     *         Indicates to client successful marking of Task(s) as done and lists out
     *         all Task(s) that have been marked as done.
     */
    public String markAsDone(ArrayList<ArrayList<Integer>> rangeList) {
        TaskList doneTasks = new TaskList();

        for (ArrayList<Integer> range : rangeList) {
            for (int i = range.get(0); i <= range.get(1); i++) {
                Task task = get(i);
                task.setIsDone(true);
                doneTasks.add(task);
            }
        }

        if (doneTasks.size() == this.size()) {
            return "Nice! I've marked all tasks as done.";
        } else if (doneTasks.size() > 1) {
            return "Nice! I've marked these tasks as done:\n" + doneTasks + "\n";
        } else {
            return "Nice! I've marked this task as done:\n" + doneTasks.get(0);
        }
    }

    /**
     * Returns an undone message after a / multiple Task(s) have been marked as undone in this TaskList.
     * @param rangeList ArrayList of ranges, represented by ArrayLists containing two elements each.
     *                  First element represents starting index while second element represents ending index
     *                  (both inclusive).
     * @return An undone message after a / multiple Task(s) have been marked as undone from this TaskList.
     *         Indicates to client successful marking of Task(s) as undone and lists out
     *         all Tasks(s) that have been marked as undone.
     */
    public String markAsUndone(ArrayList<ArrayList<Integer>> rangeList) {
        TaskList undoneTasks = new TaskList();

        for (ArrayList<Integer> range : rangeList) {
            for (int i = range.get(0); i <= range.get(1); i++) {
                Task task = get(i);
                task.setIsDone(false);
                undoneTasks.add(task);
            }
        }

        if (undoneTasks.size() == this.size()) {
            return "Nice! I've marked all tasks as undone.";
        } else if (undoneTasks.size() > 1) {
            return "Nice! I've marked these tasks as undone:\n" + undoneTasks;
        } else {
            return "Nice! I've marked this task as undone:\n" + undoneTasks.get(0);
        }
    }

    /**
     * Returns a deleted message after a / multiple Task(s) have been deleted from this TaskList.
     * @param rangeList ArrayList of ranges, represented by ArrayLists containing two elements each.
     *                  First element represents starting index while second element represents ending index
     *                  (both inclusive).
     * @return A deleted message after a / multiple Task(s) have been deleted from this TaskList.
     *         Indicates to client successful deletion and lists out all Task(s)
     *         that have been deleted.
     */
    public String removeRanges(ArrayList<ArrayList<Integer>> rangeList) {

        for (ArrayList<Integer> range : rangeList) {
            for (int i = range.get(1); i >= range.get(0); i--) {
                this.get(i).markAsDelete();
            }
        }

        TaskList deletedTasks = new TaskList();

        for (int i = this.size() - 1; i >= 0; i--) {
            Task task = this.get(i);
            if (task.getMarkedAsDelete() == true) {
                deletedTasks.add(task);
                int sizeBefore = this.size();
                this.remove(task);
                int sizeAfter = this.size();
                assert sizeAfter != sizeBefore : "Task was called to be removed from TaskList but "
                        + "size of TaskList remained unchanged.";
                assert sizeAfter == sizeBefore - 1 : "One item was meant to be removed from the TaskList "
                        + "but the difference in sizes of the lists before and after is not 1.";
            }
        }

        Collections.reverse(deletedTasks.taskList);

        String s = "";
        int prevSize = this.size();

        if (deletedTasks.size() == prevSize) {
            return "Noted. I've removed all tasks in the task list.";
        } else if (deletedTasks.size() > 1) {
            s = "Noted. I've removed these tasks:\n" + deletedTasks + "\n";
        } else {
            s = "Noted. I've removed this task:\n"
                    + deletedTasks.get(0) + "\n";
        }

        if (size() == 1) {
            s += "Now you have 1 task in the list.";
        } else {
            s += "Now you have " + this.size() + " tasks in the list.";
        }

        return s;
    }

    /**
     * Returns the size of this TaskList.
     * @return An integer representing the number of Tasks in this TaskList.
     */
    public int size() {
        int res = taskList.size();
        assert res >= 0 : "TaskList size returned is less than zero.";
        return res;
    }

    /**
     * Returns the Task at the specified position of this TaskList.
     * @param i Position of the Task to be retrieved, from index 0 to n-1 in a TaskList of n elements.
     * @return Task at given index of this TaskList.
     */
    public Task get(int i) {
        Task task = taskList.get(i);
        assert task != null && task instanceof Task : "Invalid Task returned from TaskList.";
        return task;
    }

    /**
     * Returns a TaskList with search results for this TaskList.
     * Search keywords may contain the task description of tasks to be searched for,
     * the status of whether tasks have been marked as done, the types of task, date / time
     * (in DD/MM/YYYY and HHMM format respectively), and separator keywords for the type of task
     * (eg. "at" for event and "by" for deadline).
     * If none of the task descriptions, types, done statuses, dates/ times etc. of all the tasks match the
     * search keyword, the method returns an empty TaskList.
     * @param taskString Keywords to be used to search this TaskList for relevant Tasks.
     * @return TaskList containing keywords searched for in the given taskString.
     */
    public TaskList find(String taskString) {
        TaskList searchResults = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            // If search keywords contain the task description or vice versa.
            // the task is added to the list of search results.
            if (task.getTaskString().contains(taskString)
                    || taskString.contains(task.getTaskString())) {
                searchResults.add(task);
            // If search keyword is "done" (i.e. search for tasks which have been marked as completed,
            // the task is added to the list of search results if it has been marked as done.
            } else if (task.getIsDone() == true && taskString.equals("done")) {
                searchResults.add(task);
            // If search keyword is "undone" or "not done"
            // (i.e. search for tasks which have yet to be marked as completed),
            // the task is added to the list of search results if it has yet to be marked as done.
            } else if (task.getIsDone() == false && (taskString.equals("undone")
                    || taskString.contains("not done"))) {
                searchResults.add(task);
            // If search keywords contain type of task (eg. deadline, event etc.),
            // the task is added to the list of search results if it's type matches the type to be searched for.
            // Type of task can be in any case (uppercase / lowercase).
            } else if (task.getType().toString().toLowerCase().contains(taskString.toLowerCase())
                    || taskString.toLowerCase().contains(task.getType().toString().toLowerCase())) {
                searchResults.add(task);
            // If search keywords are contained in the date (DD/MM/YYYY format) or time (HHMM format) attributes
            // of the task, the task is added to the list of search results.
            } else if (task.getDateAndTime() != null
                    && task.getDateAndTime().toString().contains(taskString)
                    || taskString.contains(task.getTaskString())) {
                searchResults.add(task);
            // If search keyword is "at" for an event-type task or search keyword is "by" for an event-type task,
            // the task is added to the list of search results if it is an event-type or
            // deadline type task respectively.
            } else if ((task.getType().toString().equals("EVENT") && taskString.equals("at"))
                    || (task.getType().toString().equals("DEADLINE") && taskString.equals("by"))) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    /**
     * Sorts this TaskList of Tasks based on the given criteria by a client that calls this method.
     * @param criteria Client's request on the order criteria by which to sort the Tasks in this TaskList by.
     * @throws InvalidSortCriteriaException If Duke does not understand the criteria it is given to sort
     *                                      this TaskList.
     */
    public void sort(String criteria) throws InvalidSortCriteriaException {
        if (criteria.equals("description")) {
            taskList.sort(Task.getStringComparator());
        } else if (criteria.equals("revdescription")) {
            taskList.sort(Task.getRevStringComparator());
        } else if (criteria.equals("date")) {
            taskList.sort(Task.getDateAndTimeComparator());
        } else if (criteria.equals("revdate")) {
            taskList.sort(Task.getRevDateAndTimeComparator());
        } else if (criteria.equals("done")) {
            taskList.sort(Task.getDoneComparator());
        } else if (criteria.equals("undone")) {
            taskList.sort(Task.getUndoneComparator());
        } else if (criteria.equals("id")) {
            taskList.sort(Task.getIdComparator());
        } else if (criteria.equals("revid")) {
            taskList.sort(Task.getRevIdComparator());
        } else if (criteria.equals("type")) {
            taskList.sort(Task.getTypeComparator());
        } else if (criteria.equals("revtype")) {
            taskList.sort(Task.getRevTypeComparator());
        } else {
            throw new InvalidSortCriteriaException("Sort criteria not found.");
        }
    }

    /**
     * Returns whether or not a Task with given attributes already exists in the TaskList.
     * @param command Type of Task belonging to the Task for which client intends to find duplicates for.
     * @param taskString Task description of given Task for which client intends to find duplicates for.
     * @param dateAndTime Date and / or Time of given Task for which client intends to find duplicates for.
     * @return A boolean on whether or not a Task with given attributes already exists in the TaskList.
     */
    public boolean hasExistingTask(String command, String taskString, DateAndTime dateAndTime) {
        for (int i = 0; i < this.size(); i++) {
            Task task = this.get(i);
            if (command.equals(task.getType().toString().toLowerCase())
                    && task.getTaskString().equals(taskString)) {
                if (task.getDateAndTime() != null && dateAndTime != null) {
                    if (task.getDateAndTime().compareTo(dateAndTime) == 0) {
                        return true;
                    }
                } else if (task.getDateAndTime() == null && dateAndTime == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the String representation of a TaskList instance.
     * Formats elements in the TaskList as a list, numbered from 1 to n, where n is the size of the taskList.
     * @return String representation of a TaskList instance.
     */
    @Override
    public String toString() {
        String out = "";
        int count = 1;
        for (Task task : taskList) {
            out += count + "." + task + "\n";
            count++;
        }
        assert count - 1 == taskList.size() : "TaskList numbering does not match size of TaskList";
        return out.trim();
    }

}
