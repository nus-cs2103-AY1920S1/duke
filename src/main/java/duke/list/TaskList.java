package duke.list;

import duke.task.Task;

import java.util.LinkedList;

/**
 * the class TaskList contains the task list as well as the  operations.
 */
public class TaskList extends LinkedList<Task> {
    private static LinkedList<Task> taskList;

    public TaskList() {
        taskList = new LinkedList<>();
    }

    /**
     * constructor to write in an existing list
     * @param taskList the list contains the tasks in text file originally
     */
    public TaskList(LinkedList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    /**
     * this method is to get the list
     * @return LinkedList which stored in this class
     */
    public static LinkedList<Task> getList() {
        return taskList;
    }

    /**
     * the method to print out the list by tasks
     */
    public static void print() {
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            Task t = taskList.get(i);
            System.out.println(index + "." + t);
        }


    }
}
