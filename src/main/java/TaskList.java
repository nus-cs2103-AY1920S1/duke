import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = new ArrayList<Task>(taskArr);
    }

}
