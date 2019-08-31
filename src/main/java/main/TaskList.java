package main;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

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
    public void addEvent(String whatToAdd, boolean isDone) throws DukeException {
        String message;
        DateTime date;
        date = new DateTime(whatToAdd.substring(whatToAdd.indexOf("/") + 4));
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
    public void addDeadline(String whatToAdd, boolean isDone) throws DukeException {
        String message;
        DateTime date;
        date = new DateTime(whatToAdd.substring(whatToAdd.indexOf("/") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        arr.add(new Deadlines(message, date, isDone));
    }

}
