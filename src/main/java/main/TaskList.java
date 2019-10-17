package main;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of Task.
 */
public class TaskList {
    ArrayList<Task> arr = new ArrayList<Task>();

    /**
     * Creates the TaskList object with the given array.
     */
    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Returns the array of Task.
     *
     * @return Array of Task.
     */
    public ArrayList<Task> getList() {
        return this.arr;
    }

    /**
     * Adds ToDo Task object to the array.
     *
     * @param whatToAdd Task message.
     * @param isDone    Marks the task as done nor not done.
     */
    public void addToDo(String whatToAdd, boolean isDone) {
        String message;
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1);
        arr.add(new ToDos(message, isDone));
    }

    /**
     * Adds Event Task object to the array.
     *
     * @param whatToAdd Task message.
     * @param isDone    Marks the task as done nor not done.
     * @throws DukeException If the input message is not recognised.
     */
    public void addEvent(String whatToAdd, boolean isDone) throws DateTimeException {
        String message;
        LocalDateTime date;
        date = dateTimeParser(whatToAdd.substring(whatToAdd.indexOf("/at") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        arr.add(new Events(message, date, isDone));
    }

    /**
     * Adds Deadline Task object to the array.
     *
     * @param whatToAdd Task message.
     * @param isDone    Marks the task as done nor not done.
     * @throws DukeException If the input message is not recognised.
     */
    public void addDeadline(String whatToAdd, boolean isDone) throws DateTimeException {
        String message;
        LocalDateTime date;
        date = dateTimeParser(whatToAdd.substring(whatToAdd.indexOf("/by") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        arr.add(new Deadlines(message, date, isDone));
    }

    /**
     * Formats a string and returns a LocalDateTime object.
     *
     * @param input Date and Time formatted in the DD/MM/YY HHMM format.
     * @return LocalDateTime object.
     */
    public static LocalDateTime dateTimeParser(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Formats a LocalDateTime format to a String.
     *
     * @param dateTime LocalDateTime object.
     * @return String representation of the LocalDateTime object.
     */
    public static String localDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }

}
