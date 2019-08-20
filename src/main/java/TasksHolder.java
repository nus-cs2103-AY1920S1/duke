import java.util.*;

public class TasksHolder {

    private ArrayList<Task> tasksArr = new ArrayList<Task>();

    TasksHolder(){
    }

    public void addTask(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.getTaskDetails());
        tasksArr.add(task);
        System.out.println("Now you have " + tasksArr.size() + " tasks in the list.");
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
