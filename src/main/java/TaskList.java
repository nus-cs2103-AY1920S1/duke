import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends CompleteList {

    protected static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    /**
     * Constructor for TaskList.
     */
    public TaskList() {

    }

    /**
     * Overloaded Constructor for Task list in
     * the event that a array list is available
     * from the file.
     *
     * @param list Arraylist that contains all the tasks.
     */
    public TaskList(ArrayList<Task> list) {
        listOfTasks = list;
    }

    public void addToTaskList(Task assignment) {
        assert assignment != null;
        listOfTasks.add(assignment);
    }
}
