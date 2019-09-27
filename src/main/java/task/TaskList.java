package task;

import exception.DukeException;
import filewriter.Storage;


import java.util.ArrayList;

/**
 * Object containing data structure to store Tasks.
 */
public class TaskList {
    ArrayList<Task> schedule = new ArrayList<>();
    public int taskNum;
    public boolean isFirst;

    /**
     * Constructor for Task List.
     * Called when generating Task List from txt file.
     * @param storage contains ArrayList of Task specified from txt file.
     */
    public TaskList(Storage storage) {
        try {
            this.schedule = storage.getSchedule();
            taskNum = schedule.size();
            if (taskNum == 0) {
                isFirst = true;
            } else {
                isFirst = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for Task List.
     */
    public TaskList() {
        this.schedule = new ArrayList<Task>();
        taskNum = schedule.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }


    /**
     * Constructor for Task List.
     * @param schedule ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> schedule) {
        this.schedule = schedule;
        taskNum = schedule.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }

    /**
     * Gets Task corresponding to specified index.
     * @param index indicates which task in the task list to return.
     * @return Task as specified by index.
     */
    public Task getTask(int index) {
        return schedule.get(index);
    }

    /**
     * Marks a Task in task list as done.
     * @param index Specifies which task to mark as done.
     * @return completed Task.
     * @throws NullPointerException thrown if schedule.get(index) attempts to reference an object with a null value.
     * @throws IndexOutOfBoundsException thrown when index is greater than number of Task(s) in schedule.
     * @throws NumberFormatException thrown if index is not of type int.
     */
    public Task complete(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException {
        Task completedTask = schedule.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Adds new Task to TaskList.
     * @param task Task to add.
     */
    public void addTask(Task task) {
        schedule.add(task);
        taskNum++;
    }

    /**
     * Deletes Task from TaskList.
     * @param index Specifies which task to delete.
     * @return deleted Task.
     * @throws NullPointerException thrown if schedule.get(index) attempts to reference an object with a null value.
     * @throws IndexOutOfBoundsException thrown when index is greater than number of Task(s) in schedule.
     * @throws NumberFormatException thrown if index is not of type int.
     */
    public Task remove(int index)
            throws NullPointerException, IndexOutOfBoundsException {
        Task removedTask = schedule.get(index);
        schedule.remove(index);
        taskNum--;
        return removedTask;
    }

    /**
     * Resets the entire list and start fresh.
     */
    public void reset(){
        schedule = new ArrayList<Task>();
    }

    /**
     * Used to get ArrayList schedule from TaskList.
     * Called when execute method of FindCommand is called.
     * @return ArrayList
     */
    public ArrayList<Task> getList() {
        return schedule;
    }

    /**
     * toString method of TaskList.
     * @return contents of TaskList. i.e. tasks.
     */
    public String toString() {
        String output = "";
        if (taskNum != 0) {
            for (int index = 0; index < taskNum; index++) {
                Task task = schedule.get(index);
                output += ("     " + (index + 1) + "." + task.toString() + "\n");
            }
            return output.substring(0, output.length() - 1);
        } else {
            return output;
        }
    }

    /**
     * Converts data in taskList to the appropriate String format to be stored in text file.
     * @return String text.
     */
    public String toText(){
        String output = "";
        if (taskNum != 0) {
            for (int index = 0; index < taskNum; index++) {
                Task task = getTask(index);
                output += (task.toString() + "\n");
            }
            return output.substring(0, output.length() - 1);
        } else {
            return output;
        }
    }
}
