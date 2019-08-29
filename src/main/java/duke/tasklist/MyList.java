package duke.tasklist;

import duke.task.Task;

import java.util.List;

public interface MyList {
    //add a task to the list
    void add(Task task);

    //returns list of task
    List<Task> getList();

    //returns total number of tasks
    int getNumTasks();

    //returns tasks by index
    Task getTask(int index);

    //removes task by index
    Task removeTask(int index);
}
