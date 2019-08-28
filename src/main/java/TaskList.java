import com.sun.source.util.TaskListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Mainly contains the ArrayList of tasks
 * Supports operations to modify this AL
 */
public class TaskList {

    private ArrayList<Task> taskArr;

    public TaskList() {
        taskArr = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listOfTask) {
        taskArr = listOfTask;
    }

    /////////////////
    // OPERATIONS //
    ///////////////

    /**
     * Adds new task to list.
     * Number of tasks in list increases by one.
     * @param newTask
     * @return Task that was added
     * @throws IOException
     */
    public Task addTask(Task newTask) throws IOException {
        taskArr.add(newTask);
        return newTask;
    }

    /**
     * Marks task at given list index as done
     * @param doneIdx
     * @return Task that was marked done
     */
    public Task markTaskDone(int doneIdx) {
        Task doneTask = taskArr.get(doneIdx-1);
        doneTask.markDone();
        return doneTask;
    }

    /**
     * Removes task at given list index.
     * Number of tasks in list decreases by one.
     * @param deleteIdx
     * @return
     */
    public Task deleteTask(int deleteIdx) {
        Task deletedTask = taskArr.get(deleteIdx-1);
        taskArr.remove(deletedTask);
        return deletedTask;
    }

    ////////////////////
    // HELPER METHODS //
    ///////////////////

    public ArrayList<Task> getTaskArr() { return taskArr; }
    public int getNumTasks() { return taskArr.size(); }

}
