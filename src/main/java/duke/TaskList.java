package duke;

import java.util.ArrayList;
import tasks.Task;

public class TaskList {
    private ArrayList<Task> taskLst;

    public TaskList() {
        this.taskLst = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskLst) {
        this.taskLst = taskLst;
    }

    public ArrayList<Task> getTaskLst() {
        return taskLst;
    }

}
