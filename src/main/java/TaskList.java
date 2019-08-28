import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arr;

    ArrayList<Task> getArr() {
        return arr;
    }

    /**
     * Prints a string.
     */
    void print(String str) {
        System.out.println(str);
    }

    /**
     * Initialises a Tasklist with files loaded from storage.
     */
    TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Adds a task to arraylist. Does not save changes to file. Use Storage to commit changes.
     *
     * @param task refers to the task to add.
     */
    void addTask(Task task) {
        print("Got it. I've added this task:");
        print(task.toString());
        arr.add(task);
        print("Now you have " + arr.size() + (arr.size() > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Returns the Task that was removed, or null in the case of an IndexOutOfBoundsException.
     *
     * @param index of the task to be removed.
     * @return the Task or null
     */
    Task deleteTask(int index) {
        try {
            return arr.remove(index);
        } catch (IndexOutOfBoundsException e) {
            print("Oops! You have entered a number out of range.");
            return null;
        }
    }

    /**
     * Switch a cross to a tick to mark a task as done.
     * @param index of the task to be marked as done.
     */
    void markAsDone(int index) {
        try {
            arr.get(index).markAsDone();
            print("Nice! I've marked this task as done:");
            print(arr.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            print("Oops! You have entered a number out of range.");
        }
    }

    /**
     * Display all tasks on the list.
     */
    void displayList () {
        Ui.print("Here are the tasks in your list:");
        int i = 0;
        for (Task t : arr) {
            Ui.print(++i + "." + t);
        }
    }
}
