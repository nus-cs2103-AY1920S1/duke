package duke.util;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy HHmm");

    // this method parse string from txt file and creates task objects when duke is initiated
    public static Task parseTaskString(String str) throws Exception {
        String[] strs = str.split("\\|");
        switch (strs[0]) {
        case ("ToDo"):
            Task todo = new ToDo(strs[2]);
            if (strs[1].equals("1")) {
                todo.toggleState();
            }
            return todo;
        case ("Deadline"):
            Date date = FORMATTER.parse(strs[3]);
            Task deadline = new Deadline(strs[2], date);
            if (strs[1].equals("1")) {
                deadline.toggleState();
            }
            return deadline;
        case ("Event"):
            Date time = FORMATTER.parse(strs[3]);
            Task event = new Event(strs[2], time);
            if (strs[1].equals("1")) {
                event.toggleState();
            }
            return event;
        default:
            return new Task("shouldn't come to here");
        }
    }

    // this method takes in string from user and decide which type it is and respective information
    // needed to create an object
    // return a string arr of length 3, index 0 is type of task, index 1 is content of task, index 2 is datetime
    // of task if the task requires datetime if not it will be empty string
    public static Command parseUserInput(String str) throws Exception {
        if (str.equals("list")) {
            return new ListCommand();
        } else if (str.contains("done")) {
            String[] afterSplit = str.trim().split(" ");
            if (afterSplit.length == 1) {
                throw new DukeException("\u2639 OOPS!!! Please input a number for done.");
            }
            return new DoneCommand(str);
        } else if (str.contains("delete")) {
            String[] afterSplit = str.trim().split(" ");
            if (afterSplit.length == 1) {
                throw new DukeException("\u2639 OOPS!!! Please input a number for delete.");
            }
            return new DeleteCommand(str);
        } else if (str.trim().equals("bye")) {
            return new ExitCommand();
        } else {
            // info splits the string into description and datetime if available
            String[] info = str.trim().split(" ", 2);

            boolean isAddCommand = info[0].equals("todo") || info[0].equals("deadline") || info[0].equals("event");
            if (!isAddCommand) {
                throw new DukeException("\u2639 OOPS!!! I do not understand what did you just typed.");
            }
            // if info has length 1 it means that only the type of item is there but no description
            if (info.length == 1 && isAddCommand) {
                throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
            }

            String[] result = new String[2];
            Date date = null;

            if (info[0].equals("todo")) {
                result[0] = "todo";
                result[1] = info[1];
            } else if (info[0].equals("deadline")) {
                String[] dateTimeArr = info[1].split("/by");
                // if it is a deadline event but no slash the info will be just 1 string after split
                if (dateTimeArr.length <= 1) {
                    throw new DukeException("\u2639 OOPS!!! You need a /by to separate out the date time for this task.");
                }
                result[0] = "deadline";
                result[1] = dateTimeArr[0].trim();
                date = FORMATTER.parse(dateTimeArr[1].trim());
            } else if (info[0].equals("event")) {
                String[] dateTimeArr = info[1].split("/at");
                // if it is a deadline event but no slash the info will be just 1 string after split
                if (dateTimeArr.length <= 1) {
                    throw new DukeException("\u2639 OOPS!!! You need a /at to separate out the date time for this task.");
                }
                result[0] = "event";
                result[1] = dateTimeArr[0].trim();
                date = FORMATTER.parse(dateTimeArr[1].trim());
            } else {
                throw new DukeException("There is an unknown error parsing your message");
            }
            return date == null ? new AddCommand(result) : new AddCommand(result, date);
        }
    }
}
