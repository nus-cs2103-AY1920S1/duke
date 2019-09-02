/**
 * This is the abstract class TaskList. This class is used to add and remove tasks from the current list.
 * @author Hua Lun
 */

import java.io.PrintWriter;
import java.util.ArrayList;

abstract class TaskList {
    private int taskNumber;
    private String taskCheck;
    private String taskName;
    private String type;

    public TaskList(int i, String tC, String tN, String t) {
        taskNumber = i;
        taskCheck = tC;
        taskName = tN;
        type = t;
    }


    public abstract DateTime getAB();

    /**
     * <p>
     *     getTaskNumber is used to retrieve the task number
     * </p>
     * @return the task number
     */


    public int getTaskNumber() {
        return taskNumber;
    }


    /**
     * <p>
     *     getTaskCheck is used to check if the task is done or not
     * </p>
     * @return status of the task
     */

    public String getTaskCheck() {
        return taskCheck;
    }

    /**
     * <p>
     *     getTaskName is used to retrieve the name of the task
     * </p>
     * @return task's name
     */


    public String getTaskName() {
        return taskName;
    }

  
    /**
     * <p>
     *     getTyoe is used to retrieve the type of task given
     * </p>
     * @return task's type
     */

    public String getType() {
        return type;
    }

    /**
     * <p>
     *     changeTaskCheck is used to mark the task as complete
     * </p>
     */


    public void changeTaskCheck() {
        taskCheck = "[✓]";
    }

    /**
     * <p>
     *     printList is used to print out all the task in the list
     * </p>
     * @param a the list of task
     */

    static void printList(ArrayList<TaskList> a) {
        System.out.println("Here are the tasks in your list:");
        for (TaskList t : a) {
            System.out.println(t);
        }
    }


    /**
     * <p>
     *     markAsDone is used to mark the given task as completed
     * </p>
     * @param i the completed task's number
     * @param a the list of task
     */

    static void markAsDone(int i, ArrayList<TaskList> a) {
        System.out.println("Nice! I've marked this task as done:");
        TaskList t = a.get(i - 1);
        String currentTask = t.getTaskName();
        String getType = t.getType();
        TaskList doneTask;
        if(getType.equals("todo")) {

            doneTask = new Todo(i, "[✓]", currentTask,
                    getType);
            a.set(i - 1, doneTask);
        } else if (getType.equals("event")) {
            doneTask = new Event(i, "[✓]", currentTask,
                    getType, t.getAB());
            a.set(i - 1, doneTask);
        } else {
            doneTask = new Deadline(i, "[✓]", currentTask,
                    getType, t.getAB());

            doneTask = new Todo(i, "[✓]", currentTask, getType);
            a.set(i - 1, doneTask);
        }
        System.out.println("[✓] " + currentTask);
    }

    /**
     * <p>
     *     addList adds the new task into the list
     * </p>
     * @param t the newly created TaskList object
     * @param a the list of task
     * @param n task number assigned
     */

    void addList(TaskList t, ArrayList<TaskList> a, int n) {
        System.out.println("Got it. I've added this task:");
        a.add(t);
        System.out.println(t);
        System.out.println("Now you have " + Integer.toString(n) +
                " tasks in the list.");
    }


    /**
     * <p>
     *     deleteTask removes the task from the list
     * </p>
     * @param t TaskList object that is to be removed
     * @param a the list of task
     */

    void deleteTask(TaskList t, ArrayList<TaskList> a) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(t);
        int taskNumber = t.getTaskNumber();
        a.remove(taskNumber - 1);

        System.out.println("Now you have " + Integer.toString(a.size()) +
                " tasks in the list.");
    }

    /**
     * <p>
     *     toString method to print TaskList object
     * </p>
     * @return task information
     */
    @Override
    public String toString() {
        return Integer.toString(taskNumber) + "." + taskCheck + " " + taskName;
    }
}
