package tasks;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TaskList class handles consolidating the task input by the user
 * into a list.
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
     * with data read from previous existing data.
     * @param currentList The list of tasks previously stored on the text file
     written to.
     */
    public TaskList(String currentList) {
        xs = new ArrayList<Task>();
        numOfTasks = 0;
        integrateList(currentList);
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
        for (Task task : xs) {
            if (task.toString().equals(t.toString())) {
                return "Sorry, a similar tasks has been found in the list.";
            }
        }
        numOfTasks++;
        assert numOfTasks >= 1 : "Task started from a negative value";
        xs.add(t);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", t, numOfTasks);
    }

    /**
     * A method to mark a specified task in the list as done.
     * @param num The index of the task in the list.
     * @return Returns a String to be printed when the task is marked as done.
     */
    public String tickTask (int num) {
        xs.get(num - 1).markAsDone();
        return String.format("Nice! I've marked this task as done:\n%s", xs.get(num - 1));
    }

    /**
     * A method to remove a task from the list of task in TaskList.
     * @param num The index of the task in the list.
     * @return Returns a String to be printed when the task is removed.
     */
    public String removeTask(int num) {
        numOfTasks--;
        assert numOfTasks >= 0 : " Negative task numbers found";
        Task t = xs.get(num - 1);
        xs.remove(num - 1);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.", t, numOfTasks);
    }

    /**
     * A method iterate through the list of task and print each Task.
     * @return Returns a String representation of all the Task in the TaskList.
     */
    public String printTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 1; i <= xs.size(); i++) {
            sb.append("\n" + String.format("%d.%s", i, xs.get(i - 1)));
        }
        return sb.toString();
    }

    /**
     * A method to print the tasks in the list that matches the predicate.
     * @return Returns a String representation of all the Task that match the predicate.
     */
    public String printMatchingTasks() {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 1; i <= xs.size(); i++) {
            sb.append("\n" + String.format("%d.%s", i, xs.get(i - 1)));
        }
        return sb.toString();
    }

    /**
     * A method to process the data of previous tasking, and add them to the
     * current TaskList.
     * @param content Data to be processed.
     */
    private void integrateList (String content) {
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

            default:
                break;
            }
        }
        s.close();
    }
}

