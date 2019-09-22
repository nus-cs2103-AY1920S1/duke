package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList deals with operations pertaining to the task list.
 */
public class TaskList {

    private List<Task> list;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Another constructor for TaskList class.
     *
     * @param tasks Tasks to load into the task list.
     */
    public TaskList(List<Task> tasks) {
        list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task);
        }
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Adds a task into the task list.
     * If task is a priority, task will be added to the front of the list.
     * If task is not a priority, it will be added to the back of the list.
     *
     * @param task Task to be added into the list.
     */
    public void addToList(Task task) {
        if (task.isPriority()) {
            list.add(0, task);
        } else {
            list.add(task);
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return int Size of task list.
     */
    public int getListSize() {
        return list.size();
    }

    /**
     * Returns the task after marking it as done.
     *
     * @param num Number of the task in the task list.
     * @return task Task that has been marked as done.
     */
    public Task markAsDone(int num) {
        Task task = list.get(num - 1);
        task.setDone();
        return task;
    }

    /**
     * Returns the deleted task.
     *
     * @param num Number of the task in the task list.
     * @return task Task that has been deleted.
     */
    public Task delete(int num) {
        Task deletedTask = list.get(num - 1);
        list.remove(num - 1);
        return deletedTask;
    }

    /**
     * Returns the prioritised task.
     *
     * @param num Number of the task in the task list.
     * @return prioritisedTask Task that has been prioritised.
     */
    public Task prioritise(int num) {
        Task prioritisedTask = list.get(num - 1);
        prioritisedTask.setAsPriority();
        list.remove(num - 1);
        addToList(prioritisedTask);
        return prioritisedTask;
    }

    /**
     * Returns a string consisting of all tasks that matches the given keyword in the task list.
     *
     * @param s The keyword.
     * @return String All tasks that contain the keyword.
     */
    public String find(String s) {
        List<Task> temp = new ArrayList<>();
        for (Task task : list) {
            String[] descriptionArray = task.getDescription().split(" ");
            for (int i = 0; i < descriptionArray.length; i++) {
                if (s.equals(descriptionArray[i])) {
                    temp.add(task);
                }
            }
        }
        return convertToString(temp);
    }

    /**
     * Returns a string containing all tasks in the task list.
     */
    public String printList() {
        int i = 1;
        boolean isNotPriority = false;
        String s = "";
        for (Task task : list) {
            if (!task.isPriority() && !isNotPriority) {
                isNotPriority = true;
                s += "----------------------\n";
            }
            s += i + ". " + task + "\n";
            i++;
        }
        return s;
    }

    private String convertToString(List<Task> t) {
        int i = 1;
        String s = "";
        for (Task task : t) {
            s += i + ". " + task + "\n";
            i++;
        }
        return s;
    }
}
