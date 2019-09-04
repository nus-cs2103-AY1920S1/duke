import java.util.ArrayList;

/**
 * Represents a task
 * Contains a description of the task
 * Contains the list of tasks in an ArrayList
 */
public class TaskList {
    public static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a task as done
     *
     * @param command  Command that includes the index of task to be marked
     */
    public void markAsDone(String command) {
        int completedTaskNumber = Integer.parseInt(command);
        tasks.get(completedTaskNumber - 1).isDone = true;
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("      " + tasks.get(completedTaskNumber - 1));
    }

    /**
     * Prints the entire task list with index
     *
     */
    public void printTaskList() {
        System.out.println("    Here are the tasks in your list:\n");
        int index = 1;
        for (Task x : tasks) {
            System.out.println("    " + index + ". " + x);
            index++;
        }
    }

    /**
     * Clears the task list
     *
     */
    public void clearTaskList() {
        this.tasks.clear();
        System.out.println("    The task list has been cleared!");
    }
    /**
     * Deletes a task from the task list
     *
     */
    public void deleteTask(String command) {
        int index = Integer.parseInt(command);
        if (index <= tasks.size() && index >0) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("    Noted. I've removed this task:\n      " + removedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks left in the list");
        } else {
            System.out.println("     Index of task to be deleted not found");
        }
        if (tasks.isEmpty()) {
            System.out.println("    Congratulations, your last task has been deleted!");
        }
    }
    /**
     * Searches the task list with a keyword and prints all that matches
     * Prints "There are no matching tasks in your list" if no match is found
     *
     * @param command  Command that includes the keyword to be searched for
     */
    public void searchByKeyword(String command) {
        command = command.substring(1, command.length());
        boolean hasMatch = false;
        for (Task x : tasks) {
            if (x.description.contains(command)) {
                int index = tasks.indexOf(x) + 1;
                if (!hasMatch) {
                    System.out.println("    Here are the matching tasks in your list");
                    hasMatch = true;
                }
                System.out.println("      " + Integer.toString(index) + "." + x);
            }
        }
        if (!hasMatch) {
            System.out.println("    There are no matching tasks in your list");
        }
    }

}
