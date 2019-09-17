import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Contains the methods to execute user's commands and store the information while the program runs.
 *
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
     *
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
        assert x < taskList.size() : "There is no task corresponding to that number";
        taskList.get(x).markAsDone();
        return "Nice! I've marked this task as done:\n" + taskList.get(x);

    }

    /**
     *Deletes the task in the list upon user's request and prints the number of tasks remaining upon deletion.
     *
     * @param y the position of the task in the list
     */
    protected String deleteTask(int y) {
        assert y < taskList.size() : "There is no task corresponding to that number";
        if (taskList.size() < y) {
            return "The index of the task to be deleted exceeds the number of tasks in the list\n";
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
     * Deletes a certain task in the list and stores it in a different file for future reference.
     *
     * @param number the position of the task in the list
     * @return the details of the task archived
     * @throws IOException if the file to which data must be written to cannot be found
     */
    protected String archiveTask(int number) throws IOException {
        String showUser = "Got it, I've archived task " + number + " .\n";
        showUser = showUser + taskList.get(number - 1).toString() + "\n";
        FileWriter storeArchivedTask = new FileWriter("data/DukeArchive.txt",true);
        BufferedWriter out = new BufferedWriter(storeArchivedTask);
        out.write(taskList.get(number - 1).toString() + "\n");
        out.close();
        taskList.remove(number - 1);
        return showUser;
    }

    /**
     *Prints all the tasks in the list.
     *
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
     * @param eventDetails The information of the event being processed.
     * @throws ParseException If the data is not in the required format i.e. MM/dd/yyyy HH:mm.
     */
    protected String readEvent(String eventDetails) throws ParseException {
        String output = "";
        if (eventDetails.length() == 0) {
            output = (" OOPS!!! the description of a event cannot be empty.\n ");
        } else {
            int first = eventDetails.indexOf('/');
            String desc = eventDetails.substring(0, first - 1);
            String on = eventDetails.substring(first + 4);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH mm");
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
     * @param deadlineDetails the information of the Deadline input by the user.
     */
    protected String readDeadline(String deadlineDetails) {
        String output = "";
        if (deadlineDetails.length() == 0) {
            output = (" OOPS!!! the description of a deadline cannot be empty. \n");
        } else {
            int first = deadlineDetails.indexOf('/');
            String description = deadlineDetails.substring(0, first - 1);
            String byTime = deadlineDetails.substring(first + 4);
            Task t1 = new Deadline(description, byTime, false);
            this.add(t1);
            output = this.addMessage();
        }
        return output;
    }

    /**
     *Reads and processes the To Do task given by the user.
     *
     * @param todoDetails The information regarding the to do task.
     */
    protected String readTodo(String todoDetails) {
        String output = "";
        if (todoDetails.length() == 0) {
            return (" OOPS!!! the description of a todo cannot be empty. \n");
        } else {
            Task t1 = new ToDo(todoDetails, false);
            this.add(t1);
           output = this.addMessage();
        }
        return output;
    }

    /**
     * Reads data from the specified file.
     *
     * @param userData the file where user data is stored
     * @param parser parser object to process commands
     * @throws FileNotFoundException if the file specified in userData cannot be found
     * @throws ParseException if the date in the Event tasks cannot be parsed by Duke.
     */
    public void readDataFromFile(File userData, Parser parser) throws FileNotFoundException, ParseException {
        Scanner loadData = new Scanner(userData);
        while (loadData.hasNextLine()) {
            String tasks = loadData.nextLine();
            parser.readTask(tasks,this);
        }
    }
}
