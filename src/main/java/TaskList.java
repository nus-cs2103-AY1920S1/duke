import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskLst;

    public TaskList() {
        this.taskLst = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskLst) {
        this.taskLst = taskLst;
    }

}
