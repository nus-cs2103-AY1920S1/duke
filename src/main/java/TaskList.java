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

    public TaskList(){
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
    protected void addMessage() {
        System.out.println(" Got it. I've added this task: ");
        System.out.println(taskList.get(taskList.size() - 1));
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in your list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    /**
     *Marks the task as done upon user's request and prints the message upon successful completion.
     *
     * @param x the position of the task in the list.
     */
    protected void markAsDone(int x) {
        taskList.get(x).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println( taskList.get(x) );
    }

    /**
     *Deletes the task in the list upon user's request and prints the number of tasks remaining upon deletion.
     *
     * @param y the position of the task in the list
     */
    protected void deleteTask(int y) {
        if (taskList.size() == 0) {
            System.out.println("The task list is empty");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.get(y - 1));
            taskList.remove(y - 1);
            if (taskList.size() == 1) {
                System.out.println("Now you have 1 task in your list.");
            } else {
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }

    }

    /**
     *Finds and prints all the tasks containing the word requested by the user.
     *
     * @param s The word that the user wants to find.
     */
    protected void find(String s) {
        int count = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : taskList) {
            if (t.description.contains(s)) {
                System.out.println(t);
                count += 1;
            }
        }
        if (count == 0) {
            System.out.println("There are no matching tasks in your list");
        }
    }

    /**
     *Prints all the tasks in the list.
     */
    protected void getList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i+=1) {
            System.out.println(i + ". " + taskList.get(i-1) );
        }
    }

    /**
     *Reads and processes the event given by the user.
     * Stores the information in the list after processing.
     *
     * @param b The information of the event being processed.
     * @throws ParseException If the data is not in the required format i.e. MM/dd/yyyy HH:mm.
     */
    protected void readEvent(String b) throws ParseException {

        if (b.length() == 0) {
            System.out.println("\u2639" + " OOPS!!! the description of a event cannot be empty. ");
        } else {
            int first = b.indexOf('/');
            String desc = b.substring(0, first - 1);
            String on = b.substring(first + 4);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date at = df.parse(on);
            Task t1 = new Event(desc, at, false);
            this.add(t1);
            this.addMessage();
        }
    }

    /**
     *Reads and processes the deadline given by the user.
     * Stores the information in the list after processing.
     *
     * @param s the information of the Deadline input by the user.
     */
    protected void readDeadline(String s) {

        if (s.length() == 0) {
            System.out.println("\u2639" + " OOPS!!! the description of a deadline cannot be empty. ");
        } else {
            int first = s.indexOf('/');
            String descr = s.substring(0, first - 1);
            String byTime = s.substring(first + 4);
            Task t1 = new Deadline(descr, byTime, false);
            this.add(t1);
            this.addMessage();
        }
    }

    /**
     *Reads and processes the ToDo task given by the user.
     *
     * @param s The information regarding the todo task.
     */
    protected void readTodo(String s) {
        if (s.length() == 0) {
            System.out.println("\u2639" + " OOPS!!! the description of a todo cannot be empty. ");
        } else {
            Task t1 = new ToDo(s, false);
            this.add(t1);
            this.addMessage();
        }
    }
}
