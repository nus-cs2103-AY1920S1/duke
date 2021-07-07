import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the class used to read data from the file and process it.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Contains the methods to read the input and process it into the form required for the algorithm.
     *
     * @param userInput The information to be read and processed
     * @param tasks The list where the information is stored after being processed
     * @throws ParseException If date for event is not in the specified format i.e. MM/dd/yyyy HH:mm
     */

    public void readTask(String userInput, TaskList tasks) throws ParseException {
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");
        assert userInput.length() > 0 : "Enter something";
        //T stands for to do type of tasks, E stands for event and D stands for deadline tasks.
        if (userInput.contains("[T]")) {
            //verify the status of the task before loading into task list.
            if (userInput.contains("[" + "1" + "]")) {
             //if task is already done, instantiate with true
                tasks.add(new ToDo(userInput.substring(7), true));
            } else {
                tasks.add(new ToDo(userInput.substring(7)));
            }
        } else if (userInput.contains("[E]")) {
            int start = userInput.indexOf('(');
            String event = userInput.substring(7, start - 1);
            //every event task is associated with a date and time.
            String date = userInput.substring(start + 5, start + 27);
            Date at = df.parse(date);
            if (userInput.contains("[" + "1" + "]")) {
                tasks.add(new Event(event, at, true));
            } else {
                tasks.add(new Event(event, at));
            }
        } else {
            int sl = userInput.indexOf('(');
            String d = userInput.substring(7, sl - 1);
            int sec = userInput.indexOf(')');
            String by = userInput.substring(sl + 5, sec);
            if (userInput.contains("[" + "1" + "]")) {
                tasks.add(new Deadline(d, by, true));
            } else {
                tasks.add(new Deadline(d, by));
            }
        }
    }

    /**
     * Method to read and process user input.
     *
     * @param ui      the UI of the Duke object that interacts with the user
     * @param list    the task list of the Duke object which stores the tasks
     * @param storage the object responsible for reading file data and saving data to file when user exits
     * @throws IOException    if File not found or other input output exceptions
     * @throws ParseException if thee data for event type of tasks not in the specified format
     */
    public String readUserCommand(String command, Ui ui, TaskList list, Storage storage) throws IOException, ParseException {
        String output = "";
        assert command.length() > 0 : "Please enter something";
        if (command.equals("bye")) {
            output = ui.exit();
        } else if (command.equals("hello")) {
            output = ui.greet();
        } else if (command.equals("list")) {
            // returns all the tasks in the list.
            output = list.getList();
        } else if (command.startsWith("done")) {
            int numberToBeMarked = Integer.parseInt(command.substring(5));
            output = list.markAsDone(numberToBeMarked - 1);
        } else if (command.startsWith("event")) {
            String eventGiven = command.substring(6);
            //reads and stores the event in the list
            output = list.readEvent(eventGiven);
        } else if (command.startsWith("deadline")) {
            String deadlineGiven = command.substring(9);
            output = list.readDeadline(deadlineGiven);
        } else if (command.startsWith("todo")) {
            String todoDetails = command.substring(5);
            output = list.readTodo(todoDetails);
        } else if (command.startsWith("delete")) {
            int numberToBeDeleted = Integer.parseInt(command.substring(7));
            output = list.deleteTask(numberToBeDeleted);
        } else if (command.startsWith("find")) {
            String requiredWord = command.substring(5);
            output = list.find(requiredWord);
        } else if (command.startsWith("archive")) {
            int taskToBeArchived = Integer.parseInt(command.substring(8));
            output = list.archiveTask(taskToBeArchived);
        } else {
            output = (" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        String writeDataToFile = "";
        for (Task t : list.taskList) {
            writeDataToFile = writeDataToFile + t.toString() + "\n";
        }
        storage.writeFile(writeDataToFile);
        return output;
    }
}



