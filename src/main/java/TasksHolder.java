import java.util.*;

public class TasksHolder {

    private ArrayList<Task> tasksArr = new ArrayList<Task>();

    TasksHolder(){
    }

    public void addTask(Task task) {
        System.out.println("added: " + task.getTaskName());
        tasksArr.add(task);
    }

    public void printTasks() {
        for (int i = 0 ; i < tasksArr.size() ; i++ )
            System.out.println("  " + (i+1) + ". " + tasksArr.get(i).getTaskName());
    }
}
