import java.io.IOException;
import java.text.DateFormatSymbols;

/**
 * The parser class processes user inputs into understandable commands
 * which are then executed by the Duke program. 
 */
public class Parser {

    /**
     * Creates a new Parser object.
     */
    public Parser() {

    }

    private static String getMonth(String month) throws DukeException {
        int monthIndex = Integer.parseInt(month);
        if (monthIndex > 12 || monthIndex < 1) {
            throw new DukeException("OOPS!!! The month you inputted is not valid.");
        }
        return new DateFormatSymbols().getMonths()[monthIndex - 1];
    }

    private static void validateHour(int hour) throws DukeException {
        if (hour > 24 || hour < 1) {
            throw new DukeException("OOPS!!! The time you inputted is not valid.");
        }
    }

    private static void validateMinute(int minute) throws DukeException {
        if (minute > 59 || minute < 0) {
            throw new DukeException("OOPS!!! The time you inputted is not valid.");
        }
    }

    private static String parseTime(String timeDetail) throws DukeException {
        int hour = (Integer.parseInt(timeDetail) / 100);
        validateHour(hour);
        String actualHour = hour % 12 == 0 ? "12" : String.valueOf(hour % 12); 
        int minute = Integer.parseInt(timeDetail) % 100;
        validateMinute(minute);
        String meridiem = hour >= 12 && hour < 24 ? "pm" : "am";
        String processedMinute = minute == 0 ? "" : "." + String.valueOf(minute);
        return actualHour + processedMinute + meridiem;
    }

    private static String parseDate(String dateDetail) throws DukeException {
        String[] dateBreakup = dateDetail.split("/");
        String month = getMonth(dateBreakup[1]);    
        int lastDigitOfDay = Integer.parseInt(dateBreakup[0]) % 10;
        String dayEnding = lastDigitOfDay == 1 ? "st" : lastDigitOfDay == 2 ? "nd" 
                : lastDigitOfDay == 3 ? "rd" : "th";
        return dateBreakup[0] + dayEnding + " of " + month + " " + dateBreakup[2]; 
    }

    private static String processDeadlineDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        return parseDate(date) + ", " + parseTime(time);
    }

    private static String processEventDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        String[] timeDetails = time.split("-");
        return parseDate(date) + ", " + parseTime(timeDetails[0]) + "-" + parseTime(timeDetails[1]);
    }

    /**
     * Processes the user's input into a valid command and takes an action 
     * accordingly based on the deciphered command.
     * @param input The user input.
     * @param taskList The taskList object handling all tasks.
     * @param ui The Ui object to display any messages to the user.
     * @param storage The Storage object needed to load and write back to the tasks file. 
     * @throws Exception When any error occurs during the execution of the user command.
     */
    public StringBuilder processCommand(String input, TaskList taskList, Storage storage) throws Exception {
        String[] command = input.split(" ");
        assert command.length > 0 : "command length should be greater than 0";
        if (command[0].equals("list")) {
            return taskList.printTasks();
        } else if (command[0].equals("delete")) {
            return deleteTask(input, taskList, storage);
        } else if (command[0].equals("done")) {
            return taskDone(input, taskList, storage);
        } else if (command[0].equals("find")) { 
            if (command.length == 1) {
                throw new DukeException("Find query must be specified!!!");
            } 
            return taskList.printMatchingTasks(command[1]);
        } else if (command[0].equals("bye")) {
            StringBuilder sb = new StringBuilder(showGoodbyeMessage());
            return sb;
        } else { 
            return addTask(input, taskList, storage);
        }
    }

    private static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    private static StringBuilder deleteTask(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        if (command.length == 1) {
            throw new DukeException("OOPS!!! The index of task to be deleted must be mentioned.");
        } else {
            try {
                return taskList.deleteTask(Integer.parseInt(command[1]), storage);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! The index of task to be deleted must be a number.");
            }
        }
    }

    private static StringBuilder taskDone(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        if (command.length == 1) {
            throw new DukeException("OOPS!!! The completed task's index must be mentioned.");
        } else {
            try {
                return taskList.markAsDone(Integer.parseInt(command[1]), storage);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! The completed task's index must be a number.");
            }
        }
    }

    private static StringBuilder addToDo(String details, TaskList list, Storage storage) throws IOException {
        Task task = new ToDos(details);
        list.addTask(task, storage);
        return addSuccess(task).append("\n" + "Now you have " + 
            list.getNumberOfTasks() + " tasks in the list.");
    }

    private static StringBuilder addDeadline(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /by ");
        validateDeadlineDetails(detAsArr);
        String deadline = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Deadline(deadline, processDeadlineDate(dueDetail));
        list.addTask(task, storage);
        return addSuccess(task).append("\n" + "Now you have " + list.getNumberOfTasks() 
            + " tasks in the list.");
    }

    private static StringBuilder addEvent(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /at ");
        validateEventDetails(detAsArr);
        String event = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Event(event, processEventDate(dueDetail));
        list.addTask(task, storage);
        return addSuccess(task).append("\n" + "Now you have " + list.getNumberOfTasks() 
            + " tasks in the list.");
    }
    
    private static StringBuilder addTask(String input, TaskList list, Storage storage) throws Exception {
        String[] inputAsArr = input.split(" ");
        validateDetail(inputAsArr);
        String command = inputAsArr[0];
        String rest = input.substring(input.indexOf(" ") + 1);
        if (command.equals("todo")) {
            return addToDo(rest, list, storage);
        } else if (command.equals("deadline")) {
            return addDeadline(rest, list, storage);
        } else {
            return addEvent(rest, list, storage);
        }
    }

    private static StringBuilder addSuccess(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append("\n");
        sb.append("\t" + task.toString());
        assert sb.length() == 0 : "The stringbuilder should not be empty.";
        return sb;
    }

    private static void validateDetail(String[] detail) throws DukeException {
        if (detail.length == 0) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! detail[0].equals("todo") && ! detail[0].equals("event") && ! detail[0].equals("deadline")) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (detail.length == 1) {
            throw new DukeException("OOPS!!! The description of a " + detail[0] + " cannot be empty.");
        }
    }

    private static void validateDeadlineDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("OOPS!!! The due date of a deadline must be specified.");
        }
    }

    private static void validateEventDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("OOPS!!! The timeline of an event must be specified.");
        }
    }

}