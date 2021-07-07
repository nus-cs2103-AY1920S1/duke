import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Task List stores task information in an array list.
 */

public class TaskList {
    
    private ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<>();
    }

    /**
     * Get the task list.
     * @return tasks in arraylist
     */
    public ArrayList<Task> getTaskList() {
        return tasklist;
    }

    /**
     * constructor with task list from storage.
     * @param tasklist from storage class
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * add task to the list.
     * @param task task
     */
    public void addTask(Task task) {
        tasklist.add(task);
    }


    public int getCount() {
        return tasklist.size();
    }

    /**
     * delete task in the list.
     * @param number task number
     */
    public void deleteTask(int number) {
        tasklist.remove(number);
    }

    public void deleteAll() {
        tasklist = new ArrayList<>();
    }

    /**
     * sort the task in the list according to description alphabetically.
     * @return sorted by description
     */
    public String sortByDescription() {
        Collections.sort(getTaskList(), compareByDescription);
        String desc = "";
        for (int i = 0; i < getTaskList().size(); i++) {
            desc += getTaskList().get(i).toString() + "\n";
        }
        return desc;
    }

    /**
     * sort the task in the list according to type. Todo task first followed by Event task and finally, Deadline task.
     * @return sorted by type
     */
    public String sortByType() {
        Collections.sort(getTaskList(),compareByType);
        String desc = "";
        for (int i = 0; i < getTaskList().size(); i++) {
            desc += getTaskList().get(i).toString() + "\n";
        }
        return desc;
    }

    public Comparator<Task> compareByDescription = (o1, o2) -> o1.getDescription().compareTo(o2.getDescription());

    public Comparator<Task> compareByType = (o1, o2) -> {
        String tag1;
        String tag2;
        if (o1 instanceof ToDo) {
            tag1 = "1";
        } else if (o1 instanceof  Event) {
            tag1 = "2";
        } else {
            tag1 = "3";
        }
        if (o2 instanceof ToDo) {
            tag2 = "1";
        } else if (o2 instanceof  Event) {
            tag2 = "2";
        } else {
            tag2 = "3";
        }
        return tag1.compareTo(tag2);
    };
}