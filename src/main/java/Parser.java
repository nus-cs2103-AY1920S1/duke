import java.io.IOException;
import java.text.DateFormatSymbols;

public class Parser {


    public Parser() {

    }

    public static String getMonth(String month) throws DukeException {
        int monthIndex = Integer.parseInt(month);
        if (monthIndex > 12) {
            throw new DukeException("☹ OOPS!!! The month you inputted is not valid.");
        }
        return new DateFormatSymbols().getMonths()[monthIndex - 1];
    }

    public static void validateHour(int hour) throws DukeException {
        if (hour > 24 || hour < 1) {
            throw new DukeException("☹ OOPS!!! The time you inputted is not valid.");
        }
    }

    public static void validateMinute(int minute) throws DukeException {
        if (minute > 59 || minute < 0) {
            throw new DukeException("☹ OOPS!!! The time you inputted is not valid.");
        }
    }

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

    public static String parseDate(String dateDetail) throws DukeException {
        String[] dateBreakup = dateDetail.split("/");
        String month = getMonth(dateBreakup[1]);    
        int lastDigitOfDay = Integer.parseInt(dateBreakup[0]) % 10;
        String dayEnding = lastDigitOfDay == 1 ? "st" : lastDigitOfDay == 2 ? "nd" 
                : lastDigitOfDay == 3 ? "rd" : "th";
        return dateBreakup[0] + dayEnding + " of " + month + " " + dateBreakup[2]; 
    }

    public static String processDeadlineDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        return parseDate(date) + ", " + parseTime(time);
    }

    public static String processEventDate(String dateDetails) throws DukeException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        String[] timeDetails = time.split("-");
        return parseDate(date) + ", " + parseTime(timeDetails[0]) + "-" + parseTime(timeDetails[1]);
    }

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

    public static void deleteTask(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        if (command.length == 1) {
            throw new DukeException("☹ OOPS!!! The index of task to be deleted must be mentioned.");
        } else {
            try {
                taskList.deleteTask(Integer.parseInt(command[1]), storage);;
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! The index of task to be deleted must be a number.");
            }
        }
    }

    public static void taskDone(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] command = input.split(" ");
        if (command.length == 1) {
            throw new DukeException("☹ OOPS!!! The completed task's index must be mentioned.");
        } else {
            try {
                taskList.markAsDone(Integer.parseInt(command[1]), storage);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! The completed task's index must be a number.");
            }
        }
    }

    public static void addToDo(String details, TaskList list, Storage storage) throws IOException {
        Task task = new ToDos(details);
        list.addTask(task, storage);
        Ui.addSuccess(task);
    }

    public static void addDeadline(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /by ");
        Ui.validateDeadlineDetails(detAsArr);
        String deadline = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Deadline(deadline, processDeadlineDate(dueDetail));
        list.addTask(task, storage);
        Ui.addSuccess(task);
    }

    public static void addEvent(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /at ");
        Ui.validateEventDetails(detAsArr);
        String event = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Event(event, processEventDate(dueDetail));
        list.addTask(task, storage);
        Ui.addSuccess(task);
    }

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