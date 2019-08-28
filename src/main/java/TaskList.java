import java.util.ArrayList;
import duke.task.Task;

/**
 * Represents and contains the tasks list and operations surrounding the tasks list.
 */
public class TaskList {

    private static ArrayList<Task> taskList;

    /**
     * An empty constructor to create the TaskList object.
     */
    public TaskList() {}

    /**
     * Creates the TaskList object with the specified taskList.
     * @param taskList The specified ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        taskList = taskList;
    }

    /**
     * Adds the specified task to the tasks list and prints the necessary output.
     * @param task The specified task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        printAddedTask(task);
    }

    /**
     * Marks the task with the specified task number as done and prints the necessary output.
     * @param taskNo The specified number of the task that is to be marked as done.
     */
    public void markAsDone(int taskNo) {
        Task taskDone = taskList.get(taskNo - 1);
        taskDone.markAsDone();
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + taskDone.toString());
    }

    /**
     * Deletes the task with the specified task number from the tasks list and prints the necessary output.
     * @param taskNumber The specified number of the task to be deleted from the tasks list.
     */
    public void deleteTask(int taskNumber) {
        Task task = taskList.remove(taskNumber - 1);
        printDeletedTask(task);
    }

    /**
     * Prints the necessary output when a specific task is added to the tasks list.
     * @param task The specified task that was added to the task list.
     */
    private void printAddedTask(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    /**
     * Prints the necessary output when a specific task is deleted from the tasks list.
     * @param task The specified task that was deleted from the task list.
     */
    private void printDeletedTask(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    /**
     * Prints the entire list task by task.
     */
    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("\t%d.%s", i + 1, taskList.get(i)));
        }
    }

}
