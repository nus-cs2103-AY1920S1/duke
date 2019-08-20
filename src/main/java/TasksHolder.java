import java.util.*;

public class TasksHolder {

    private ArrayList<Task> tasksArr = new ArrayList<Task>();

    TasksHolder(){
    }

    public void addTask(Task task) {
        System.out.println("  added: " + task.getTaskName());
        tasksArr.add(task);
    }

    public void taskDone(int index) {
        System.out.println("Nice! I've marked this task as done: ");
        Task currTask = tasksArr.get(index - 1);
        currTask.done();
        System.out.println(currTask.getTaskDetails());
    }

    public void printTasks() {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0 ; i < tasksArr.size() ; i++ )
            System.out.println("  " + (i+1) + ". " + tasksArr.get(i).getTaskDetails());
    }
}
