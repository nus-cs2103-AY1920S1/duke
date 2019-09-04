package duke.task;

import java.util.ArrayList;

/**
 * TaskList contains an array-list of tasks. It keeps tracks
 * of all the tasks and is the gateway for all modifications
 * between Duke and the tasks.
 */
public class TaskList {
//    static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
//    static SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMMM yyyy hh':'mma");
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int getSizeOfTaskList() {
        return taskList.size();
    }

    /**
     * Marks a task as completed by changing its
     * completed field to true.
     *
     * @param taskIndex the index of the task.
     */
    public void checkTask(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.taskDone();
    }

    public String getTaskDsc(int taskIndex) {
        Task t = taskList.get(taskIndex);
        return t.getTaskDetails();
    }

    public String getFullTaskInfo(int taskIndex) {
        Task t = taskList.get(taskIndex);
        return t.toString();
    }

//    /**
//     * A getter for the arraylist of tasks.
//     *
//     * @return an arraylist of all the tasks in the task list.
//     */
//    public ArrayList<Task> getTaskList() {
//        return this.taskList;
//    }


    /**
     * Returns a string of information of all tasks in the task list.
     *
     * @return a string containing information of ALL tasks in the task list.
     */
    public String saveInfo() {
        StringBuilder info = new StringBuilder();
        boolean isFirstIteration = true;
        for (Task t : taskList) {
            if (isFirstIteration) {
                info.append(t.saveInfo());
                isFirstIteration = false;
            } else {
                info.append(System.getProperty("line.separator")).append(t.saveInfo());
            }
        }
        return info.toString();
    }
}
