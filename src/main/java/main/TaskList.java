package main;

import task.*;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Empty constructor for TaskList objects. (Creates an entirely new TaskList)
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList objects. (Takes in an ArrayList of tasks).
     * @param tasks ArrayList. Loads up the tasks present in tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the Task present at index i.
     * @param i int index.
     * @return Task at the i position of the TaskList.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the number of tasks present in the TaskList.
     * @return int.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the number of tasks present in the TaskList.
     * @return int.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Removes the Task object at the i index of the TaskList and returns it.
     * @param i int index.
     * @return Task that is removed from the i index.
     */
    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    /**
     * Appends Task t to the back of the Tasklist
     * @param t Task to be appended.
     * @return Task that was added.
     */
    public Task addTask(Task t) {
        tasks.add(t);
        return t;
    }

    /**
     * Returns a String that shows the list of tasks in the TaskList.
     * @return String.
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                result = result + "    " + (i + 1) + ". " + tasks.get(i).toString();
            } else {
                result = result + "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return result;
    }

    public ArrayList<String> findAllMatches(String desc) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasDescription(desc)) {
                result.add("    " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        return result;
    }
}
