package duke.module;

import duke.date.DukeDate;

import duke.exception.DukeDateFormatException;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.io.IOException;

public class Parser {

    private static final String ERROR_DATE_FORMAT = "☹ OOPS!!! Date must be in MM/DD/YYYY HHMM format.";
    private static final String ERROR_SAVE_FILE_FORMAT = "☹ OOPS!!! Saved file contains illegal formatting.";

    public static DukeDate parseToDate(String date) throws DukeDateFormatException {
        // Date format : MM/DD/YYYY HHMM
        String[] dateAndTime = date.split(" ");
        String[] dateFormat = dateAndTime[0].split("/");
        try {
            return new DukeDate(Integer.parseInt(dateFormat[2]),
                                Integer.parseInt(dateFormat[0]),
                                Integer.parseInt(dateFormat[1]),
                                Integer.parseInt(dateAndTime[1].substring(0, 2)),
                                Integer.parseInt(dateAndTime[1].substring(2)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeDateFormatException(ERROR_DATE_FORMAT);
        }
    }

    static Task parseToTask(String line) throws IOException {
        try {
            String[] taskComponents = line.split("-");
            switch (taskComponents[0]) {
            case "T":
                return new TodoTask(taskComponents[2],
                        taskComponents[1].equals("1"));
            case "D":
                return new DeadlineTask(taskComponents[2],
                        taskComponents[1].equals("1"),
                        parseToDukeDate(taskComponents[3]));
            case "E":
                return new EventTask(taskComponents[2],
                        taskComponents[1].equals("1"),
                        parseToDukeDate(taskComponents[3]));
            default:
                throw new IOException(ERROR_SAVE_FILE_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IOException(ERROR_SAVE_FILE_FORMAT);
        }
    }

    private static DukeDate parseToDukeDate(String date) throws IOException {
        // dd MM, yyyy, hh:mm a
        try {
            String[] parsed = date.split(" |, |:");
            int hour = parsed[5].equals("AM")
                    ? Integer.parseInt(parsed[3])
                    : Integer.parseInt(parsed[3]) + 12;
            return new DukeDate(Integer.parseInt(parsed[2]),
                    DukeDate.Month.valueOf(parsed[1].toUpperCase()),
                    Integer.parseInt(parsed[0]),
                    hour,
                    Integer.parseInt(parsed[4]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeDateFormatException e) {
            throw new IOException(ERROR_SAVE_FILE_FORMAT);
        }
    }

}
