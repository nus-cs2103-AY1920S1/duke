package SerSnapsalot.taskList;
import SerSnapsalot.exception.DukeException;
import SerSnapsalot.task.Task;

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
     * @param command command.Command that includes the index of task to be marked.
     * @return output Message to indicate task that has been done.
     * @throws DukeException If unable to locate index of task to be marked as done.
     */
    public String markAsDone(String command) throws DukeException{
        int completedTaskNumber = Integer.parseInt(command);
        if (completedTaskNumber > tasks.size()){
            throw new DukeException("TaskList Exception: Index of task not found");
        }
        tasks.get(completedTaskNumber - 1).isDone = true;
        String markedDoneMessage = "Nice! I've marked this task as done:\n" +  tasks.get(completedTaskNumber - 1);
        return markedDoneMessage;
    }

    /**
     * Prints the entire task list with index.
     * @return output The printed task list.
     */
    public String printTaskList() {
        String output = "Here are the tasks in your list:";
        int index = 1;
        for (Task x : tasks) {
            output += ("\n" + index + ". " + x);
            index++;
        }
        return output;
    }

    /**
     * Clears the task list.
     * @return clearListMessage The message to indicate task list has been cleared.
     */
    public String clearTaskList() {
        tasks.clear();
        String clearListMessage =  "The task list has been cleared!";
        return clearListMessage;
    }

    /**
     * Deletes a task from the task list, and returns a string to update on remaining tasks.
     *
     * @param command The index of the task to be deleted, starts from 1.
     * @return output Message to update on remaining tasks.
     * @throws DukeException If unable to locate index of task to be deleted.
     */
    public String deleteTask(String command) throws DukeException{
        String output = "";
        int index = Integer.parseInt(command);
        if (index <= tasks.size() && index > 0) {
            Task removedTask = tasks.remove(index - 1);
            output +=  ("Noted. I've removed this task:\n" + removedTask);
            output += ("\nNow you have " + tasks.size() + " tasks left in the list");
            if (tasks.isEmpty()) {
                output += ("Congratulations, your last task has been deleted!");
            }
        } else {
            throw new DukeException("TaskList Exception: Index of task to be deleted not found.");
        }
        return output;
    }

    /**
     * Searches the task list with a keyword and prints all that matches.
     * Prints "There are no matching tasks in your list" if no match is found.
     *
     * @param command command.Command that includes the keyword to be searched for.
     * @return output The string containing the located task/s or error message.
     */
    public String searchByKeyword(String command) {
        String output = "";
        command = command.substring(1);
        boolean hasMatch = false;
        for (Task x : tasks) {
            if (x.description.contains(command)) {
                int index = tasks.indexOf(x) + 1;
                if (!hasMatch) {
                    output += "Here are the matching tasks in your list:";
                    hasMatch = true;
                }
                output += ("\n" + index + ". " + x);
            }
        }
        if (!hasMatch) {
            output += "There are no matching tasks in your list";
        }
        return output;
    }

}
