import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> l) {
        this.list = l;
    }
}
