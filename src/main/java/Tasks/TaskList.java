package Tasks;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int itemNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskList.get(itemNum));
        taskList.remove(itemNum);
        System.out.println("Now you have " + taskList.size() + " in the list.");
    }

    public void markTaskDone(int itemNum) {
        taskList.get(itemNum).doneJob();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(itemNum));
        System.out.println("Now you have " + taskList.size() + " in the list.");
    }

    public void showAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(taskList.get(i));
            continue;
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
