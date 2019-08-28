import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskArrayList;

    /**
     * Constructs an instance of TaskList
     * @param taskArrayList
     */
    TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        if (taskArrayList == null) {
            throw new DukeException("ArrayList is not loaded");
        }
        this.taskArrayList = taskArrayList;
    }

    TaskList() {
    }

    /**
     * Deletes a specific task in the list based on the index in the array
     * @param index
     */
    public void delete(int index) {
        taskArrayList.remove(index);
    }

    /**
     * Adds a specific task into the list
     * @param task
     */
    public void add(Task task) {
        taskArrayList.add(task);
    }

}
