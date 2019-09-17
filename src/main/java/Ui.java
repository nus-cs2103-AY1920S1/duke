import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Ui is a class that designed to output the how the program
 * responds to the user inputs.
 */
public class Ui {
    public static String showWelcome() {
        String welcomeMessage = line()
                + indent() + "Hello! I'm Duke\n"
                + indent() + "What can I do for you?\n"
                + line();
        return welcomeMessage;
    }

    public String showGoodbye() {
        String exitMessage = line()
                + indent() + "Bye. Hope to see you again soon\n"
                + line();
        return exitMessage;
    }

    public static String indent() {
        return "    " ;
    }

    public static String line() {
        StringBuilder line = new StringBuilder();
        line.append(indent());

        for (int i = 0; i < 47; i++) {
            line.append("_");
        }

        line.append("\n");

        String stringLine = line.toString();

        return stringLine;
    }

    public String printTaskList(TaskList taskList) {
        String output;
        if (taskList.isEmpty()) {

            output = line()
                    + indent() + "There are no tasks in the list currently.\n"
                    + line();

            return output;

        } else {
            StringBuilder sb = new StringBuilder();
            LinkedList<Task> taskLinkedList = taskList.getList();
            ListIterator<Task> iter = taskLinkedList.listIterator();

            int count = 1;

            sb.append(line());

            sb.append(indent() + "Here are the tasks in your list:\n");

            while (iter.hasNext()) {
                String currentTask = iter.next().toString();
                sb.append(indent() + count + "." + currentTask + "\n");
                count++;
            }

            sb.append(line());
            return sb.toString();
        }
    }

    public String showTaskDone(Task task) {
        String output = line()
                + indent() + "Nice! I've marked this task as done:\n"
                + indent() + "  " + task.toString() + "\n"
                + line();
        return output;
    }

    public String showTaskAlreadyDone(Task task) {
        String output = line()
                + indent() + "This task has already been done.\n"
                + indent() + "  " + task.toString() + "\n"
                + line();
        return output;
    }

    public String showTaskAdded(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(line());
        sb.append(indent() + "Got it. I've added this task:\n");
        sb.append(indent() + "  " + task.toString() + "\n");
        sb.append(indent());

        if (taskList.size() == 1) {
            sb.append("Now you have 1 task in the list.\n");
        } else {
            sb.append("Now you have " + taskList.size() + " tasks in the list.\n");
        }

        sb.append(line());
        return sb.toString();
    }

    public String showTaskDeleted(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(line());
        sb.append(indent() + "Noted. I've removed this task:\n");
        sb.append(indent() + "  " + task.toString() + "\n");
        sb.append(indent());

        if (taskList.size() == 1) {
            sb.append("Now you have 1 task in your list.\n");
        } else if (taskList.isEmpty()) {
            sb.append("Now you have no tasks in your list.\n");
        } else {
            sb.append("Now you have " + (taskList.size()) + " tasks in your list.\n");
        }

        sb.append(line());
        return sb.toString();
    }

    /**
     * Prints the list of tasks which has descriptions
     * matching that of a target String.
     * @param taskList List of matching tasks
     */
    public String showFoundTasks(LinkedList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        ListIterator<Task> iter = taskList.listIterator();
        Task current;
        int count = 1;

        sb.append(line());

        if (taskList.isEmpty()) {
            sb.append(indent() + "There are no matching tasks in your list.\n");
        } else {
            sb.append(indent() + "Here are the matching tasks in your list:\n");
        }

        while(iter.hasNext()) {
            current = iter.next();
            sb.append(indent() + count + "." + current.toString() + "\n");
        }

        sb.append(line());
        return sb.toString();
    }

    public String showException(DukeException e) {
        String output = line()
                + indent() + e.getMessage() + "\n"
                + line();

        return output;
    }

    public String showLoadingError() {
        String output = line()
                + indent() + "You do not have a saved task list.\n"
                + line();

        return output;
    }
}