import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents and contains the tasks list and operations surrounding the tasks list.
 */
public class TaskList {

    private static ArrayList<Task> tasks;
    private Ui ui;

    /**
     * An empty constructor to create the TaskList object.
     */
    public TaskList() {
        this.ui = new Ui();
    }

    /**
     * Creates the TaskList object with the specified taskList.
     * @param taskList The specified ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        TaskList.tasks = taskList;
        this.ui = new Ui();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void setTaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Adds the specified task to the tasks list and prints the necessary output.
     * @param task The specified task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.printAddedTask(task);
    }

    /**
     * Marks the task with the specified task number as done and prints the necessary output.
     * @param taskNo The specified number of the task that is to be marked as done.
     */
    public void markAsDone(int taskNo) {
        Task taskDone = tasks.get(taskNo - 1);
        taskDone.markAsDone();
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + taskDone.toString());
    }

    /**
     * Deletes the task with the specified task number from the tasks list and prints the necessary output.
     * @param taskNumber The specified number of the task to be deleted from the tasks list.
     */
    public void deleteTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        ui.printDeletedTask(task);
    }

    /**
     * Find the matching tasks in the task list with the specified keyword.
     * @param keyword The specified keyword that the matching tasks will contain.
     */
    public void findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String desc;

        for (Task task : TaskList.tasks) {
            desc = task.getDesc();
            if (desc.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.printMatchingTasks(matchingTasks);
    }

//    /**
//     * Prints the necessary output when a specific task is added to the tasks list.
//     * @param task The specified task that was added to the task list.
//     */
//    private void printAddedTask(Task task) {
//        System.out.println("\tGot it. I've added this task:");
//        System.out.println("\t  " + task.toString());
//        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.size()));
//    }
//
//    /**
//     * Prints the necessary output when a specific task is deleted from the tasks list.
//     * @param task The specified task that was deleted from the task list.
//     */
//    private void printDeletedTask(Task task) {
//        System.out.println("\tNoted. I've removed this task:");
//        System.out.println("\t  " + task.toString());
//        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.size()));
//    }
//
//    private void printMatchingTasks(ArrayList<Task> matchingTasks) {
//        System.out.println("\tHere are the matching tasks in your list:");
//        for (int i = 0; i < matchingTasks.size(); i++) {
//            System.out.println(String.format("\t%d.%s", i + 1, matchingTasks.get(i)));
//        }
//    }
//
//    /**
//     * Prints the entire list task by task.
//     */
//    public void printList() {
//        System.out.println("\tHere are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println(String.format("\t%d.%s", i + 1, tasks.get(i)));
//        }
//    }

}
