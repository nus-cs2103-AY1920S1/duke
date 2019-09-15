package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray;

    /**
     * Constructor for TaskList if duke.txt file exists.
     *
     * @param taskArray Array of Tasks that is loaded from the duke.txt file.
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    /**
     * Constructor for TaskList if duke.txt file does not exists a empty Task array is created.
     */
    public TaskList() {
        this.taskArray = new ArrayList<Task>();
    }

    public int getSize() {
        return taskArray.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskArray;
    }

    public Task getTask(int num) {
        return taskArray.get(num - 1);
    }

    public void addToRecord(Task t) {
        this.taskArray.add(t);
    }

    public Task removeTask(int num) {
        return this.taskArray.remove(num - 1);
    }

    /**
     * Singles out the Tasks in the ArrayList.
     *
     * @param inputString obtained from the Parser to reflect the string to find
     * @return returnedTaskList, the new ArrayList<Task> containing the tasks found.
     */
    public ArrayList<Task> findTask(String inputString) {
        ArrayList<Task> returnedTaskList = new ArrayList<Task>();
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            String toCheck = t.getDescription();
            if (toCheck.contains(inputString)) {
                returnedTaskList.add(t);
            }
        }
        return returnedTaskList;
    }
}
