import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public static void delTask(int taskNum) {
        taskList.remove(taskNum - 1);
    }

    public static int getTaskListSize() {
        return taskList.size();
    }

    public static Task getTaskAt(int taskNum) {
        return taskList.get(taskNum - 1);
    }

    public static ArrayList<Task> getCurrentTasks() {
        return taskList;
    }

    public static void printTasks() {
        if (taskList.isEmpty()) {
            System.out.println("task list is empty");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
    }
}
