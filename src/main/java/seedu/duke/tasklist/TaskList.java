package seedu.duke.tasklist;

import seedu.duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    public int getSize(){
        return listOfTasks.size();
    }

    public Task getTask(int i){
        return listOfTasks.get(i);
    }

    public void addTask(Task newTask){
        this.listOfTasks.add(newTask);
    }

    public void deleteTask(int i){
        this.listOfTasks.remove(i);
    }
}
