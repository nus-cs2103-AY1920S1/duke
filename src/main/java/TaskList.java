import java.util.ArrayList;

/**
 * A class representing the entire list of tasks stored by the user. Stores an ArrayList object and can carry
 * out operations to list all tasks, add tasks, delete tasks or mark specific tasks as done.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructor initiating the class containing a certain ArrayList of tasks.
     *
     * @param list ArrayList that contains all the tasks to be stored.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Function that returns the list of tasks.
     *
     * @return ArrayList containing Task objects.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Function that prints out all the Task objects in the ArrayList.
     *
     * @throws DukeException If the task list is empty.
     */
    public void list() throws DukeException{
        if (list.size() == 1) {
            throw new DukeException("OOPS!!! You have no tasks to be displayed.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < list.size(); i++) {
                System.out.println(i + "." + list.get(i));
            }
        }
    }

    /**
     * Function that takes in an index parameter and marks the task at that index as done.
     *
     * @param num Index of the Task object to be marked as done.
     * @throws DukeException If a Task object does not exist at the given index.
     */
    public void markAsDone(int num) throws DukeException {
        if (num >= list.size() || num < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            System.out.println(list.get(num).markAsDone());
        }
    }

    /**
     * Takes in a Task object and adds it to the ArrayList of Task objects.
     *
     * @param task Task object representing task to be added to the list.
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1));
        System.out.println("Now you have " + (list.size() - 1) + " tasks in the list");
    }

    /**
     * Deletes the task at a given index.
     *
     * @param num Index of the Task to be deleted from the ArrayList.
     * @throws DukeException If the Task object at the given index does not exist.
     */
    public void deleteTask(int num) throws DukeException {
        if (num >= list.size() || num < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            System.out.println("Noted. I've removed this task:\n" + list.remove(num));
            System.out.println("Now you have " + (list.size() - 1) + " tasks in the list.");
        }
    }

    public void findTasks(String keyword) {
        ArrayList<Task> tasksMatched = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getName().contains(keyword)) {
                tasksMatched.add(list.get(i));
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int j = 0; j < tasksMatched.size(); j++) {
            System.out.println(j+1 + "." + tasksMatched.get(j));
        }
    }
}
