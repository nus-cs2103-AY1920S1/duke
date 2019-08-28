import java.util.ArrayList;

public class TaskList{

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existingTaskList) {
        this.tasks = existingTaskList;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    Task addTask(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    Task deleteTask(int taskNum) {
        Task removedTask = this.tasks.remove(taskNum-1);
        return removedTask;
    }

    Task finishTask(int taskNum) {
        Task completedTask = this.tasks.get(taskNum-1);
        completedTask.finishTask();
        return completedTask;
    }

    int size() {
        return tasks.size();
    }

    /**
     * Returns the task with the corresponding task ID.
     * @param taskID is the ACTUAL task ID, with index starting from 1
     * @return Task Object with the corresponding task ID.
     */
    Task getTask(int taskID) {
        return tasks.get(taskID-1);
    }

    void clearAll() {
        this.tasks.clear();
    }
    /*
    Note to self: the out of bounds exception should be caught earlier under the part where you process command!
     */
}
