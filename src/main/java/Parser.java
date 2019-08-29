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

    /**
     * Returns the name of the month specified by the 
     * string representation of its index in the calendar.
     * @param month The index of the month in the calendar in String form.
     * @return The name of the specified month.
     * @throws DukeException When the month index specified does not exist.
     */
    public static String getMonth(String month) throws DukeException {
        int monthIndex = Integer.parseInt(month);
        if (monthIndex > 12 || monthIndex < 1) {
            throw new DukeException("OOPS!!! The month you inputted is not valid.");
        }
        return new DateFormatSymbols().getMonths()[monthIndex - 1];
    }

    /**
     * Validates an hour input in the time string to determine if it is 
     * a valid hour. 
     * @param hour The hour being validated. 
     * @throws DukeException When the hour is not part of the 24 hour system.
     */
    public static void validateHour(int hour) throws DukeException {
        if (hour > 24 || hour < 1) {
            throw new DukeException("OOPS!!! The time you inputted is not valid.");
        }
    }

    /**
     * Validates a minute input in the time string to determine if it is 
     * a valid minute. 
     * @param minute The minute being validated. 
     * @throws DukeException When the minute is greater than 59 or less than 0.
     */
    public static void validateMinute(int minute) throws DukeException {
        if (minute > 59 || minute < 0) {
            throw new DukeException("OOPS!!! The time you inputted is not valid.");
        }
    }

    /**
     * Returns the time string in the 12 hour format after parsing its String 
     * representation in the 24 hour format.
     * @param timeDetail The String representation of the time as per the system.
     * @return Time in 12 hour format.
     * @throws DukeException If the format of the time is invalid.
     */
    public static String parseTime(String timeDetail) throws DukeException {
        int hour = (Integer.parseInt(timeDetail)/100);
        validateHour(hour);
        String actualHour = hour % 12 == 0 ? "12" : String.valueOf(hour % 12); 
        int minute = Integer.parseInt(timeDetail) % 100;
        validateMinute(minute);
        String meridiem = hour >= 12 && hour < 24 ? "pm" : "am";
        String processedMinute = minute == 0 ? "" : "." + String.valueOf(minute);
        return actualHour + processedMinute + meridiem;
    }

    /**
     * Returns the date string in (dd of mm, YYYY) format after parsing
     * its string representation in the dd/mm/yyyy format.
     * @param dateDetail The string representation of the date.
     * @return Date in (dd of mm, YYYY) format.
     * @throws DukeException When the dd/mm/yyyy format of the date 
     * is invalid.
     */
    public static String parseDate(String dateDetail) throws DukeException {
        String[] dateBreakup = dateDetail.split("/");
        String month = getMonth(dateBreakup[1]);    
        int lastDigitOfDay = Integer.parseInt(dateBreakup[0]) % 10;
        String dayEnding = lastDigitOfDay == 1 ? "st" : lastDigitOfDay == 2 ? "nd" : 
                lastDigitOfDay == 3 ? "rd" : "th";
        return dateBreakup[0] + dayEnding + " of " + month + " " + dateBreakup[2]; 
    }

    /**
     * Returns the deadline date and time for a deadline in the advanced date and time
     * format.
     * @param dateDetails Details of the due date and time of the deadline. 
     * @return String representation of the deadline date and time in advanced date
     * and time format. 
     * @throws DukeException If the due or time entered are invalid.
     */
    public static String processDeadlineDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        return parseDate(date) + ", " + parseTime(time);
    }

    /**
     * Returns the event date and time duration in the advanced date and time
     * format.
     * @param dateDetails Details of the due date and time duration of the event. 
     * @return String representation of the event date and time duration in advanced date
     * and time format.
     * @throws DukeException If the due or time duration entered are invalid.
     */
    public static String processEventDate(String dateDetails) throws DukeException {
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
     * @param storage The Storage object needed to load and write back to the 
     * tasks file. 
     * @throws Exception When any error occurs during the execution of the user command.
     */
    public void processCommand(String input, TaskList taskList, Ui ui, Storage storage) throws Exception {
        String[] command = input.split(" ");
        if (command[0].equals("list")) {
            ui.printTasks(taskList.getTaskList());
        } else if (command[0].equals("delete")) {
            deleteTask(input, taskList, storage);
        } else if (command[0].equals("done")) {
            taskDone(input, taskList, storage);
        } else {
            addTask(input, taskList, storage);
        }
    }

    /**
     * Deletes a particular task from the list of tasks based on the index
     * specified by the user.
     * @param input The user's delete command.
     * @param taskList The TaskList object holding all the tasks.
     * @param storage The Storage object needed to load and write back to the 
     * tasks file. 
     * @throws DukeException When the index the user entered is not a number.
     * @throws IOException When there is a problem writing back to the file.
     */
    public static void deleteTask(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        if (command.length == 1) {
            throw new DukeException("OOPS!!! The index of task to be deleted must be mentioned.");
        } else {
            try {
                taskList.deleteTask(Integer.parseInt(command[1]), storage);;
            } 
            catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! The index of task to be deleted must be a number.");
            }
        }
    }

    /**
     * Marks a particular task from the list of tasks as done based on the index
     * specified by the user.
     * @param input The user's done command.
     * @param taskList The TaskList object holding all the tasks.
     * @param storage The Storage object needed to load and write back to the 
     * tasks file. 
     * @throws DukeException When the index the user entered is not a number.
     * @throws IOException When there is a problem writing back to the file.
     */
    public static void taskDone(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        if (command.length == 1) {
            throw new DukeException("OOPS!!! The completed task's index must be mentioned.");
        } else {
            try {
                taskList.markAsDone(Integer.parseInt(command[1]), storage);
            } 
            catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! The completed task's index must be a number.");
            }
        }
    }

    /**
     * Creates a new Todo, stores it into the task list and writes it into 
     * the local file as well. 
     * @param details Details being used to create the event.
     * @param list The TaskList object holding all the tasks.
     * @param storage The Storage object needed to load and write back to the 
     * tasks file. 
     * @throws IOException When any error arises in writing back into storage in the file.
     */
    public static void addToDo(String details, TaskList list, Storage storage) throws IOException {
        Task task = new ToDos(details);
        list.addTask(task, storage);
        Ui.addSuccess(task);
    }

    /**
     * Creates a new deadline, stores it into the task list and writes it into 
     * the local file as well. 
     * @param details Details being used to create the event.
     * @param list The TaskList object holding all the tasks.
     * @param storage The Storage object needed to load and write back to the 
     * tasks file. 
     * @throws DukeException When any error arises in creating the event.
     * @throws IOException When any error arises in writing back into storage in the file.
     */
    public static void addDeadline(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /by ");
        Ui.validateDeadlineDetails(detAsArr);
        String deadline = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Deadline(deadline, processDeadlineDate(dueDetail));
        list.addTask(task, storage);
        Ui.addSuccess(task);
    }

    /**
     * Creates a new event and stores into the task list and writes it back
     * the local file. 
     * @param details Details being used to create the event.
     * @param list The TaskList object holding all the tasks.
     * @param storage The Storage object needed to load and write back to the 
     * tasks file. 
     * @throws DukeException When any error arises in creating the event.
     * @throws IOException When any error arises in writing back into storage in the file.
     */
    public static void addEvent(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /at ");
        Ui.validateEventDetails(detAsArr);
        String event = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Event(event, processEventDate(dueDetail));
        list.addTask(task, storage);
        Ui.addSuccess(task);
    }
    
    /**
     * Adds a task into the task list and writes it back into the local task 
     * file as well.
     */
    public static void addTask(String input, TaskList list, Storage storage) throws Exception {
        String[] inputAsArr = input.split(" ");
        Ui.validateDetail(inputAsArr);
        String command = inputAsArr[0];
        String rest = input.substring(input.indexOf(" ") + 1);
        if (command.equals("todo")) {
            addToDo(rest, list, storage);
        } else if (command.equals("deadline")) {
            addDeadline(rest, list, storage);
        } else {
            addEvent(rest, list, storage);
        }
        System.out.println("Now you have " + list.getTaskList().size() + " tasks in the list.");
    }

}