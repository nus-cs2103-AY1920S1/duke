package duke.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Contains the task list.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Creates an instance of TaskList with empty new arrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates an instance of TaskList with input arrayList of tasks.
     *
     * @param tasks list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return size of task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns ith Task element from the task list.
     *
     * @param i index of Task in the list of tasks.
     * @return Task at index i of the list.
     */
    public Task getElement(int i) {
        return this.tasks.get(i);
    }

    /**
     * Sorts tasks based on a category.
     *
     * @param category category that the sorting should be based on.
     */
    public void sortTasks(String category) {
        switch (category) {
        case "time":
            tasks.sort((t1, t2) -> {
                if (t1.getTime() == null || t2.getTime() == null)
                    return 0;
                return t1.getTime().compareTo(t2.getTime());
            });
            break;
        case "description":
            tasks.sort((t1, t2) -> {
                if (t1.getDescription().equals(t2.getDescription()))
                    return 0;
                return t1.getDescription().compareTo(t2.getDescription());
            });
            break;
        case "type":
            tasks.sort((t1, t2) -> {
                if (t1.getLabel().equals(t2.getLabel()))
                    return 0;
                return t1.getLabel().compareTo(t2.getLabel());
            });
            break;
        case "status":
            tasks.sort(Comparator.comparingInt(Task::getStatus));
            break;
        default:
            throw new AssertionError(category);
        }
    }

    /**
     * Validates that the task information entered by the user is in proper format.
     *
     * @param taskType type of task.
     * @param taskDes  description of task.
     * @param taskTime date and time of task
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    private void validateInput(String taskType, String taskDes, String taskTime) throws DukeException {
        if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (taskDes.equals("")) throw new DukeException(
                "\u2639 OOPS!!! The description of a " + taskType + " cannot be empty.");
        if ((taskType.equals("deadline") || taskType.equals("event")) && taskTime.equals(""))
            throw new DukeException(
                    "\u2639 OOPS!!! The time of a " + taskType + " cannot be empty.");
    }

    /**
     * Converts command of users into Task objects with relevant details.
     *
     * @param s String used by user for creating a new task.
     * @return a Task object with input details
     * @throws DukeException Exception thrown when input is of invalid format.
     */
    public Task add(String s) throws DukeException {
        String[] arr = s.split(" ");
        String taskType = arr[0];
        StringBuilder taskDes = new StringBuilder();
        StringBuilder taskTime = new StringBuilder();

        // Get task description
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() >= 1 && arr[i].charAt(0) == '/') {
                // Get task time
                for (int j = i + 1; j < arr.length; j++) {
                    taskTime.append(" ").append(arr[j]);
                }
                break;
            } else {
                taskDes.append(" ").append(arr[i]);
            }
        }
        taskDes = new StringBuilder(taskDes.toString().trim());
        taskTime = new StringBuilder(taskTime.toString().trim());

        // Handle exceptions
        validateInput(taskType, taskDes.toString(), taskTime.toString());

        if (taskType.equals("todo")) {
            return generateTask(taskType, taskDes);
        }
        // Converts date format
        try {
            Date taskDate = new SimpleDateFormat("d/MM/yyyy HHmm").parse(taskTime.toString());
            // Creates new task
            return generateTaskWithDate(taskType, taskDes, taskDate);
        } catch (ParseException e) {
            // Do nothing if the date is not properly formatted
            throw new DukeException("\u2639 The time of the task is not in proper format. \n " +
                    "example of a valid time: 01/01/2000 0800");
        }

    }

    /**
     * Marks the nth task in the task list as done.
     *
     * @param n the position of task in the list of tasks.
     * @return the task marked as done.
     */
    public Task done(int n) {
        Task task = this.tasks.get(n - 1);
        // Mark the corresponding task as done
        task.mark();
        return task;
    }

    /**
     * Deletes nth task from the list of tasks.
     *
     * @param n the position of task in the list of tasks.
     * @return the task deleted from the list.
     */
    public Task delete(int n) throws DukeException {
        if ((n < 0) || (n > tasks.size())) {
            throw new DukeException("\u2639 Oops, there is no matching task in the list.");
        }
        Task task = this.tasks.get(n - 1);
        // Delete task from list
        this.tasks.remove(task);
        return task;
    }

    /**
     * Converts task into a String which can be stored in the data file.
     *
     * @return a String representation to be stored in the task list file.
     */
    public String generateInfo() {
        // Convert Task into a String which can be stored in the data file
        StringBuilder taskFile = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String current;
            if (task.getTimeAsString().equals("")) {
                assert task.getLabel().equals("T") : task;
                current = task.getLabel() + " | " + task.getStatus() + " | " + task.getDescription();
            } else {
                assert (task.getLabel().equals("D") || task.getLabel().equals("E")) : task;
                current = task.getLabel() + " | " + task.getStatus() + " | " + task.getDescription()
                        + " | " + convertDateFormat(task.getTime());
            }
            if (i != tasks.size() - 1) {
                taskFile.append(current).append(System.lineSeparator());
            } else {
                taskFile.append(current);
            }
        }
        return taskFile.toString();
    }

    /**
     * Creates a new task instance with a date and add it to the task list.
     *
     * @param taskType type of task.
     * @param taskDes  description of task.
     * @param taskTime time and/or date of task.
     * @return Task added to the task list.
     */
    private Task generateTaskWithDate(String taskType, StringBuilder taskDes, Date taskTime) {
        Task task;
        switch (taskType) {
        case "deadline":
            task = new Deadline(taskDes.toString(), taskTime);
            tasks.add(task);
            return task;
        case "event":
            task = new Event(taskDes.toString(), taskTime);
            tasks.add(task);
            return task;
        default:
            throw new AssertionError(taskType);
        }
    }

    /**
     * Creates a new task instance and add it to the task list.
     *
     * @param taskType type of task.
     * @param taskDes  description of task.
     * @return Task added to the task list.
     */
    private Task generateTask(String taskType, StringBuilder taskDes) {
        if (taskType.equals("todo")) {
            Task task = new Todo(taskDes.toString());
            tasks.add(task);
            return task;
        } else {
            throw new AssertionError(taskType);
        }
    }

    /**
     * Converts date of a task into string to be stored in file.
     *
     * @param date the date and time of a task;
     * @return string representation of date and time;
     */
    private String convertDateFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("d/MM/yyyy HHmm");
        return formatter.format(date);
    }
}
