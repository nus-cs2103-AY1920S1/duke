import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> listOfTasks = new ArrayList<Task>();
    //protected static String file = "todo.txt";

    public TaskList() {

    }

    public TaskList(ArrayList<Task> list) {
        listOfTasks = list;
    }

}
