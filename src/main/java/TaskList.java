import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represent the list of tasks for Duke to keep track of.
 */
public class TaskList {
    public int numTasks = 0;
    private ArrayList<Task> allTasks;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    /**
     * Creates a new Tasklist with the given tasks.
     * @param allTasks representing the current tasks saved in the text file.
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
        this.numTasks = allTasks.size();
    }

    /**
     * Adds a new task to this TaskList.
     * @param newTask new task to be added.
     */
    public void addTask(Task newTask) {
        numTasks++;
        allTasks.add(newTask);
        //System.out.println(tasks.get(numTasks-1));
    }

    /**
     * Print all the tasks on the list.
     */
    public String printAllTasks() {
        String output = "";
        for (int i = 1; i <= numTasks; i++) {
            //System.out.println(i + "." + allTasks.get(i - 1));
            output = output + i + "." + allTasks.get(i - 1) + "\n";
        }
        return output;
    }

    /**
     * Mark the task of the given index as done.
     * @param index of the task to be marked.
     */
    public String markAsDone(int index) {
        String reply = "";
        Task completedTask = allTasks.get(index - 1);
        if (!completedTask.isDone()) {
            reply = completedTask.markAsDone();
        }
        return reply;
    }

    /**
     * Deletes the Task of the give index from the TaskList.
     * @param index of the task to be deleted.
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        Task deletedTask = allTasks.get(index);
        allTasks.remove(index);
        numTasks--;
        return deletedTask;
    }

    /**
     * Search for the keyword given in this TaskList.
     * @param keyword Representing the word we want to search for.
     * @return A TaskList of the filtered tasks containing this keyword.
     */
    public TaskList searchFor(String keyword) {
        ArrayList<Task> relevant = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getDescription().contains(keyword)) {
                relevant.add(task);
            }
        }
        return new TaskList(relevant);
    }

    @Override
    public String toString() {
        String output = "";
        for (Task task : allTasks) {
            output = output + task + System.lineSeparator();
        }
        return output;
    }
}