import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList;

    public TaskList() {
    }

    public TaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
        printAddedTask(task);
    }

    public void markAsDone(int taskNo) {
        Task taskDone = taskList.get(taskNo - 1);
        taskDone.markAsDone();
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + taskDone.toString());
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
        printDeletedTask(task);
    }

    public void deleteTask(int taskNumber) {
        Task task = taskList.remove(taskNumber - 1);
        printDeletedTask(task);
    }

    private void printAddedTask(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    private void printDeletedTask(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, taskList.get(i)));
        }
    }

}
