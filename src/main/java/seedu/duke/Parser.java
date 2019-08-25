package seedu.duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    public Parser() {

    }

    public static Date toDate(String s) throws Exception {
        Date date = dateFormat.parse(s);
        return date;
    }

    public static Date toTime(String s) throws Exception {
        Date time = timeFormat.parse(s);
        return time;
    }

    public static Task readInFileLine(String line) throws Exception {
        String[] stringArr = line.split(" [|] ", 0);
        if (stringArr[0].equals("E")) {
            String[] dateTimeArr = (stringArr[3]).split(" ", 2);
            Date date = toDate(dateTimeArr[0]);
            Date time = toTime(dateTimeArr[1]);
            Event event = new Event(stringArr[2], date, time);
            if (stringArr[1] == "1") {
                event.markAsDone();
            }
            return event;
        } else if (stringArr[0].equals("T")) {
            Todo td = new Todo(stringArr[2]);
            if (stringArr[1] == "1") {
                td.markAsDone();
            }
            return td;
        } else {
            String[] dateTimeArr = (stringArr[3]).split(" ", 2);
            Date date = toDate(dateTimeArr[0]);
            Date time = toTime(dateTimeArr[1]);
            Deadline dl = new Deadline(stringArr[2], date, time);
            if (stringArr[1] == "1") {
                dl.markAsDone();
            }
            return dl;
        }
    }

    public static boolean isDeleteCommand(String command) {
        return (command.length() >= 6 && command.substring(0, 6).equals("delete"));
    }

    public static int taskToDelete(String command) {
        return Integer.parseInt(command.substring(7));
    }

    public static boolean isTodo(String command) {
        return (command.length() >= 4 && command.substring(0, 4).equals("todo"));
    }

    public static Todo createTodo(String command) {
        return new Todo(command.substring(5));
    }

    public static boolean isEvent(String command) {
        return (command.length() >= 5 && command.substring(0, 5).equals("event"));
    }

    public static Event createEvent(String command) throws ParseException {
        String[] arr = command.split(" /at ", 2);
        String[] dateTimeArr = (arr[1]).split(" ", 2);
        Date date = dateFormat.parse(dateTimeArr[0]);
        Date time = timeFormat.parse(dateTimeArr[1]);
        return new Event(arr[0].substring(6), date, time);

    }
    public static boolean isDeadline(String command) {
        return (command.length() >= 8 && command.substring(0, 8).equals("deadline"));
    }
    public static Deadline createDeadline(String command) throws ParseException {
        String[] arr = command.split(" /by ", 2);
        String[] dateTimeArr = (arr[1]).split(" ", 2);
        //throw error if user never input time or date...
        Date date = dateFormat.parse(dateTimeArr[0]);
        Date time = timeFormat.parse(dateTimeArr[1]);
        return new Deadline(arr[0].substring(9), date, time);
    }

    public static boolean isMarkDone(String command) {
        return (command.length() >= 4 && command.substring(0, 4).equals("done"));
    }

    public static int taskToMarkDone(String command) {
        int curr = Integer.parseInt(command.substring(5));
        return curr;
    }

    public static boolean isListCommand(String command) {
        return (command.length() == 4 && command.equals("list"));
    }

    public static Command parse(String command, Ui ui) throws DukeException {
        if (command.equals("bye")) {
            return new ByeCommand(command);
        }
        if (command.length() < 4) {
            throw new DukeException(ui.noSuchCommand());
        }
        if (Parser.isDeleteCommand(command)) {
            return new DeleteCommand(command);
        } else if (!Parser.isListCommand(command) && !Parser.isMarkDone(command)) {
            if (Parser.isTodo(command)) {
               return new TodoCommand(command);
            } else if (Parser.isEvent(command)) {
               return new EventCommand(command);
            } else if (Parser.isDeadline(command)) {
                return new DeadlineCommand(command);
            } else {
                //throw exception for wrong command
                throw new DukeException(ui.noSuchCommand());
            }
        } else if (Parser.isMarkDone(command)) {
        //marking task as done
            return new MarkDoneCommand(command);
        } else if (Parser.isListCommand(command)) {
            //listing tasks out
            return new ListCommand(command);
        } else {
            throw new DukeException(ui.noSuchCommand());
        }
    }
}
