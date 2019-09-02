import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TaskList {

    ArrayList<Task> list;

    public TaskList (ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add (Task task) {

        list.add(task);
    }

    public Task done (int index) {
        Task task = list.get(index);
        task.markAsDone();

        return task;
    }

    public void list() {

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i));
        }
    }

    public Task delete (int index) {

        Task task = list.remove(index);

        return task;

    }
}
