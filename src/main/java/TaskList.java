import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TaskList class handles consolidating the task input by the user
 into a list.
 */
public class TaskList {
    /**
     * The ArrayList of Task contained by TaskList.
     */
    private ArrayList<Task> xs;

    /**
     * The number of Task recorded in the TaskList currently.
     */
    private int numOfTasks;

    /**
     * Constructs and initializes the attributes of a new TaskList object.
     */
    public TaskList() {
        xs = new ArrayList<Task>();
        numOfTasks = 0;
    }

    /**
     * Constructs and initializes the attributes of a new TaskList object,
     with data read from previous existing data.
     * @param currentList The list of tasks previously stored on the text file
     written to.
     */
    public TaskList(String currentList) {
        xs = new ArrayList<Task>();
        numOfTasks = 0;
        integrateList(xs, currentList);
    }

    /**
     * A method to process the data of previous taskings, and add them to the
     current TaskList.
     * @param list List of Task to be updated.
     * @param content Data to be processed.
     */
    private void integrateList (ArrayList<Task> list, String content) {
        Scanner s = new Scanner(content);
        while (s.hasNextLine()) {
            String text = s.nextLine();
            String[] itemArr = text.split(" [|] ");
            switch (itemArr[0]) {
            case "T":
                xs.add(new ToDos(itemArr[2], itemArr[1]));
                numOfTasks++;
                break;

            case "D":
                xs.add(new Deadlines(itemArr[2], itemArr[1], itemArr[3]));
                numOfTasks++;
                break;

            case "E":
                xs.add(new Event(itemArr[2], itemArr[1], itemArr[3]));
                numOfTasks++;
                break;
            }
        }
        s.close();
    }

    /**
     * A method to return the number of Task that exist in TaskList.
     * @return Returns the number of Task.
     */
    public int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * A method to retrieve the list of task in TaskList.
     * @return Returns the list of Task.
     */
    public ArrayList<Task> getTaskList() {
        return xs;
    }

    /**
     * A method to add a task to the list of task in TaskList.
     * @param t The task to be added.
     * @return Returns a String to be printed when the list is updated.
     */
    public String addTask(Task t) {
        numOfTasks++;
        xs.add(t);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", t, numOfTasks);
    }

    /**
     * A method to mark a specified task in the list as done.
     * @param num The index of the task in the list.
     * @return Returns a String to be printed when the task is marked as done.
     */
    public String tickTask(int num) {
        xs.get(num - 1).markAsDone();
        return String.format("Nice! I've marked this task as done:\n%s\n", xs.get(num - 1));
    }

    /**
     * A method to remove a task from the list of task in TaskList.
     * @param num The index of the task in the list.
     * @return Returns a String to be printed when the task is removed.
     */
    public String removeTask(int num) {
        numOfTasks--;
        Task t = xs.get(num - 1);
        xs.remove(num - 1);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", t, numOfTasks);
    }

    /**
     * A method iterate through the list of task and print each Task.
     * @return Returns a String representation of all the Task in the TaskList.
     */
    public String printTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= xs.size(); i++) {
            sb.append("\n" + String.format("%d.%s", i, xs.get(i - 1)));
        }
        return sb.toString();
    }
}
