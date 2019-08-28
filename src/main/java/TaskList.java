/**
 * Class to represent the arraylist containing the tasks.
 */

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }
}
