import java.util.AbstractCollection;
import java.util.ArrayList;


/**
 * Represents a TaskList Class which represented the entire
 * list of task that the user have entered into the program.
 * The TaskList class handles all the manipulating of the List.
 */

public class TaskList {
    private ArrayList<Task> listOfTask;

    public TaskList() {
        listOfTask = new ArrayList<>();
    }

    public void listTask() {
        if (listOfTask.size() == 0) {
            System.out.println("List is Empty");
        } else {
            for (int i = 0; i < listOfTask.size(); i++) {
                System.out.println(i + 1 + "." + listOfTask.get(i));
            }
        }
    }

    public Task getTask(int index) {
        return listOfTask.get(index);
    }

    public ArrayList<Task> getEntireList() {
        return listOfTask;
    }

    public void addTask(Task newTask) {
        listOfTask.add(newTask);
    }

    public int noOfTask() {
        return listOfTask.size();
    }

    public void removeTask(int index) {
        listOfTask.remove(index);
    }
}
