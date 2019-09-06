import java.util.ArrayList;

/**
 * Represents a task
 * Contains a description of the task
 * Contains the list of tasks in an ArrayList
 */
public class TaskList {
    public static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Marks a task as done.
     *
     * @param command Command that includes the index of task to be marked.
     */
    public String markAsDone(String command) {
        int completedTaskNumber = Integer.parseInt(command);
        tasks.get(completedTaskNumber - 1).isDone = true;
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("      " + tasks.get(completedTaskNumber - 1));
        return ("Nice! I've marked this task as done:\n" +  tasks.get(completedTaskNumber - 1));
    }

    /**
     * Prints the entire task list with index.
     */
    public String printTaskList() {
        System.out.println("    Here are the tasks in your list:\n");
        String output = "Here are the tasks in your list:";
        int index = 1;
        for (Task x : tasks) {
            System.out.println("    " + index + ". " + x);
            output += ("\n" + index + ". " + x);
            index++;
        }
        return output;
    }

    /**
     * Clears the task list.
     */
    public String clearTaskList() {
        tasks.clear();
        System.out.println("    The task list has been cleared!");
        return "The task list has been cleared!";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param command The index of the task to be deleted, starts from 1.
     */
    public String deleteTask(String command) {
        String output = "";
        int index = Integer.parseInt(command);
        if (index <= tasks.size() && index > 0) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("    Noted. I've removed this task:\n      " + removedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks left in the list");
            output +=  ("Noted. I've removed this task:\n      " + removedTask);
            output += ("\nNow you have " + tasks.size() + " tasks left in the list");
            if (tasks.isEmpty()) {
                System.out.println("    Congratulations, your last task has been deleted!");
                output += ("Congratulations, your last task has been deleted!");
            }
        } else {
            System.out.println("     Index of task to be deleted not found");
            output += ("Index of task to be deleted not found");
        }
        return output;
    }

    /**
     * Searches the task list with a keyword and prints all that matches.
     * Prints "There are no matching tasks in your list" if no match is found.
     *
     * @param command Command that includes the keyword to be searched for.
     */
    public String searchByKeyword(String command) {
        String output = "";
        command = command.substring(1);
        boolean hasMatch = false;
        for (Task x : tasks) {
            if (x.description.contains(command)) {
                int index = tasks.indexOf(x) + 1;
                if (!hasMatch) {
                    System.out.println("    Here are the matching tasks in your list");
                    output += "Here are the matching tasks in your list:";
                    hasMatch = true;
                }
                System.out.println("      " + index + "." + x);
                output += ("\n" + index + "." + x);
            }
        }
        if (!hasMatch) {
            System.out.println("    There are no matching tasks in your list");
            output += "There are no matching tasks in your list";
        }
        return output;
    }

}
