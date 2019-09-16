import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> listOfTasks = new ArrayList<Task>();
    protected Task tasking = new Task("");
    protected Storage store = new Storage(Storage.file);

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
