import java.util.List;

public interface MyList {
    //add a task to the list
    public abstract void add(Task task);

    //returns list of task
    public abstract List<Task> getList();

    //returns total number of tasks
    public abstract int getNumTasks();

    //returns tasks by index
    public abstract Task getTaskByIndex(int index);
}
