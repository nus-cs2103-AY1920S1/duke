import com.sun.source.util.TaskListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

// Contains the task list
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

    // Adds task and saves changes in hard disk
    public Task addTask(Task newTask) throws IOException {
        taskArr.add(newTask);
        return newTask;
    }

    // Marks task of given list idx as done
    public Task markTaskDone(int doneIdx) {
        Task doneTask = taskArr.get(doneIdx-1);
        doneTask.markDone();
        return doneTask;
    }

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
