import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contains the methods to execute user's commands and store the information while the program runs.
 */
public class TaskList {

    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     *Adds the task to the list.
     *
     *  @param t the task to be added.
     */
    protected void add(Task t) {
        taskList.add(t);
    }

    /**
     * Prints the addition of a task and number of tasks in the list after addition.
     */
    protected String addMessage() {



        if (taskList.size() == 1) {
            return " Got it. I've added this task: \n" + taskList.get(taskList.size() - 1) + "\n" + "Now you have 1 task in your list.";
        } else {
            return " Got it. I've added this task: \n" + taskList.get(taskList.size() - 1) + "\n" + "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    /**
     *Marks the task as done upon user's request and prints the message upon successful completion.
     *
     * @param x the position of the task in the list.
     */
    protected String markAsDone(int x) {
        taskList.get(x).markAsDone();
        return "Nice! I've marked this task as done:\n" + taskList.get(x);

    }

    /**
     *Deletes the task in the list upon user's request and prints the number of tasks remaining upon deletion.
     *
     * @param y the position of the task in the list
     */
    protected String deleteTask(int y) {
        if (taskList.size() == 0) {
            return "The task list is empty\n";
        } else {
            taskList.remove(y - 1);
            if (taskList.size() == 1) {
                return ("Noted. I've removed this task:\n" + taskList.get(y - 1) + "\n" + "Now you have 1 task in your list.");
            } else {
                return ("Noted. I've removed this task:\n" + taskList.get(y - 1) + "\n" + "Now you have "+ taskList.size() + " task in your list.");
            }
        }

    }

    /**
     *Finds and prints all the tasks containing the word requested by the user.
     *
     * @param s The word that the user wants to find.
     */
    protected String find(String s) {
        int count = 0;
        System.out.println();
        String relevant = "";
        for (Task t : taskList) {
            if (t.description.contains(s)) {
                count += 1;
                relevant = relevant + t +"\n";
            }
        }
        if (count == 0) {
            return ("There are no matching tasks in your list");
        }
        else {
            return "Here are the matching tasks in your list:\n" + relevant;
        }
    }

    /**
     *Prints all the tasks in the list.
     */
    protected String getList() {
        String list = "Here are the tasks in your list: \n";
        for (int i = 1; i <= taskList.size(); i += 1) {
            Task t = taskList.get(i-1);
            list = list + i + ". " + t.toString() + "\n";

        }
        return list ;
    }

    /**
     *Reads and processes the event given by the user.
     * Stores the information in the list after processing.
     *
     * @param b The information of the event being processed.
     * @throws ParseException If the data is not in the required format i.e. MM/dd/yyyy HH:mm.
     */
    protected String readEvent(String b) throws ParseException {
        String output = "";
        if (b.length() == 0) {
            output = (" OOPS!!! the description of a event cannot be empty.\n ");
        } else {
            int first = b.indexOf('/');
            String desc = b.substring(0, first - 1);
            String on = b.substring(first + 4);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date at = df.parse(on);
            Task t1 = new Event(desc, at, false);
            this.add(t1);
            output = this.addMessage();
        }
        return output;
    }

    /**
     *Reads and processes the deadline given by the user.
     * Stores the information in the list after processing.
     *
     * @param s the information of the Deadline input by the user.
     */
    protected String readDeadline(String s) {
        String output = "";
        if (s.length() == 0) {
            output = (" OOPS!!! the description of a deadline cannot be empty. \n");
        } else {
            int first = s.indexOf('/');
            String descr = s.substring(0, first - 1);
            String byTime = s.substring(first + 4);
            Task t1 = new Deadline(descr, byTime, false);
            this.add(t1);
            output = this.addMessage();
        }
        return output;
    }

    /**
     *Reads and processes the ToDo task given by the user.
     *
     * @param s The information regarding the todo task.
     */
    protected String readTodo(String s) {
        String output = "";
        if (s.length() == 0) {
            return (" OOPS!!! the description of a todo cannot be empty. \n");
        } else {
            Task t1 = new ToDo(s, false);
            this.add(t1);
           output = this.addMessage();
        }
        return output;
    }
}
