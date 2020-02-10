package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList{
    ArrayList<Task> taskList;
    int no_of_tasks;

    public TaskList(ArrayList<Task> t){
        this.taskList = t;
        this.no_of_tasks = t.size();
    }

    public TaskList() {
    }

    public void add(Task t){
        taskList.add(t);
        no_of_tasks++;
    }
    public void remove(int index){
        taskList.remove(index - 1);
        no_of_tasks--;
    }

    public int get_NoOfTasks(){
        return no_of_tasks;
    }
    public ArrayList<Task> get_TaskList(){
        return taskList;
    }

}
