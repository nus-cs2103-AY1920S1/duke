package duke.util;

import duke.exception.DukeException;
import duke.task.Task;
import java.util.ArrayList;

/**
 * A class representing the entire list of tasks stored by the user. Stores an ArrayList object and can carry
 * out operations to list all tasks, add tasks, delete tasks or mark specific tasks as done.
 */
public class TaskList {

    /** ArrayList of task objects */
    private ArrayList<Task> list;

    /**
     * Constructor initiating the class containing a certain ArrayList of tasks.
     *
     * @param list ArrayList that contains all the tasks to be stored.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Function that returns the list of tasks.
     *
     * @return ArrayList containing Task objects.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Function that prints out all the Task objects in the ArrayList.
     *
     * @throws DukeException If the task list is empty.
     */
    public String list() throws DukeException {
        if (list.size() == 1) {
            throw new DukeException("OOPS!!! You have no tasks to be displayed.");
        } else {
            String result = "Here are the tasks in your list:\n";
            for (int i = 1; i < list.size(); i++) {
                result = result + i + "." + list.get(i);
                if (i < list.size() - 1) {
                    result = result + "\n";
                }
            }
            return result;
        }
    }

    /**
     * Function that takes in an index parameter and marks the task at that index as done.
     *
     * @param num Index of the Task object to be marked as done.
     * @throws DukeException If a Task object does not exist at the given index.
     */
    public String markAsDone(int num) throws DukeException {
        if (num >= list.size() || num < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            return list.get(num).markAsDone();
        }
    }

    /**
     * Takes in a Task object and adds it to the ArrayList of Task objects.
     *
     * @param task Task object representing task to be added to the list.
     */
    public String addTask(Task task) {
        list.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                list.get(list.size() - 1), list.size()-1);
    }

    /**
     * Deletes the task at a given index.
     *
     * @param num Index of the Task to be deleted from the ArrayList.
     * @throws DukeException If the Task object at the given index does not exist.
     */
    public String deleteTask(int num) throws DukeException {
        if (num >= list.size() || num < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                    list.remove(num), list.size() - 1);
        }
    }

    /**
     * Finds and prints out tasks that contains a given String.
     *
     * @param keyword String to be searched for in the tasks.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> tasksMatched = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getName().contains(keyword)) {
                tasksMatched.add(list.get(i));
            }
        }
        String result = "Here are the matching tasks in your list:\n";
        for (int j = 0; j < tasksMatched.size(); j++) {
            result = result + j+1 + "." + tasksMatched.get(j);
            if (j < tasksMatched.size() - 1) {
                result = result + "\n";
            }
        }
        return result;
    }

    public String setPriority(int taskNumber, String priority) throws DukeException {
        if (taskNumber >= list.size() || taskNumber < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            return list.get(taskNumber).setPriority(priority);
        }
    }
}
