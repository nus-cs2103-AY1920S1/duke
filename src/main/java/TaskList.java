import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Contains the task list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates an instance of TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Creates an instance of TaskList and initialises.
     *
     * @param taskList list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns size of the list.
     *
     * @return size of the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the element at ith position.
     *
     * @param i stores the position from where the task is to be extracted.
     * @return the element at ith position.
     */
    public Task getElement(int position) {
        return this.taskList.get(position);

    }

    /**
     * Returns the information of time with a particular time format.
     *
     * @param n Stores the information of time in a String forma.
     * @return proper date format.
     */
    private static String getTimeFormat(int n) {
        if (n >= 11 && n <= 13) {
            return n + "th";
        }

        switch (n % 10) {
        case 1:
            return n + "st of";
        case 2:
            return n + "nd of";
        case 3:
            return n + "rd of";
        default:
            return n + "th of";
        }
    }

    /**
     * Converts command of users into Task objects with details.
     *
     * @param s String used by user for creating a new task.
     * @return Task object with extracted input details
     * @throws DukeException Exception thrown when input is in invalid format.
     */

    public Task add(String s) throws DukeException {
        String arr[] = s.split(" ");
        String tasksType = arr[0];
        String tasksDescr = "";
        String tasksTime = "";

        for (int i = 1; i < arr.length; i++) { //Gets task description
            if (arr[i].length() >= 1 && arr[i].charAt(0) == '/') {
                for (int j = i + 1; j < arr.length; j++) { //get task time
                    tasksTime += " " + arr[j];
                }
                break;
            } else {
                tasksDescr += " " + arr[i];
            }
        }
        tasksDescr = tasksDescr.trim(); //Remove extra spaces
        tasksTime = tasksTime.trim();

        //Handles exceptions
        if (!(tasksType.equals("todo") || tasksType.equals("deadline") || tasksType.equals("event"))) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n    ____________________________________________________________\n");
        }
        if (tasksDescr.equals("")) throw new DukeException(
                "    ____________________________________________________________\n     " +
                        "\u2639" + " OOPS!!! The description of a " + tasksType + " cannot be empty." +
                        "\n    ____________________________________________________________\n");
        if ((tasksType.equals("deadline") || tasksType.equals("event")) && tasksTime.equals(""))
            throw new DukeException(
                    "    ____________________________________________________________\n     " +
                            "\u2639" + " OOPS!!! The time of a " + tasksType + " cannot be empty." +
                            "\n    ____________________________________________________________\n");

        try {
            Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(tasksTime);
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
            tasksTime = formatter.format(date);
            String array[] = tasksTime.split(" ");
            array[0] = getTimeFormat(Integer.valueOf(array[0]));
            array[array.length - 1] = array[array.length - 1].toLowerCase();
            tasksTime = "";
            for (int i = 0; i < array.length; i++) {
                tasksTime += " " + array[i];
            }
            tasksTime = tasksTime.trim();
        } catch (ParseException e) {

        }

        Task task;
        if (tasksType.equals("todo")) {
            task = new ToDo(tasksDescr);
            taskList.add(task);
            return task;
        } else if (tasksType.equals("deadline")) {
            task = new Deadline(tasksDescr, tasksTime);
            taskList.add(task);
            return task;
        } else if (tasksType.equals("event")) {
            task = new Event(tasksDescr, tasksTime);
            taskList.add(task);
            return task;
        } else {
            return null;
        }
    }

    /**
     * Marks the nth task in the task list as done by changing status.
     *
     * @param n the position of task in the list of tasks.
     * @return task marked as done.
     */
    public Task done(int n) {
        Task task = this.taskList.get(n - 1);
        task.mark(); //Marks the corresponding task as done
        return task;
    }

    /**
     * Deletes nth task from the list of tasks.
     *
     * @param n the position of task in the list of tasks.
     * @return task deleted from the list.
     */
    public Task delete(int n) {
        Task task = this.taskList.get(n - 1);
        this.taskList.remove(task); //Deletes task from list
        return task;
    }

    /**
     * COnverts task into a required String to store in the data file.
     * @return String with details of the task.
     */
    public String genInfo() {
        //Convert Task object into a String which will be stored in the data file
        String taskData = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String curr;

            if (task.getTimeLabel().equals("")) {
                curr = task.getLabel() + " | " + task.getInfo() + " | " + task.getDescription();
            } else {
                curr = task.getLabel() + " | " + task.getInfo() + " | " + task.getDescription() + " | "
                        + task.getTimeLabel();
            }
            if (i != taskList.size() - 1) {
                taskData += curr + System.lineSeparator();
            } else {
                taskData += curr;
            }
        }

        return taskData;
    }
}
