package main;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> arr = new ArrayList<Task>();

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public ArrayList<Task> getList() {
        return this.arr;
    }

    public void addToDo(String whatToAdd, boolean isDone) {
        String message;
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1);
        arr.add(new ToDos(message, isDone));
    }

    public void addEvent(String whatToAdd, boolean isDone) throws DukeException, NumberFormatException {
        String message;
        DateTime date;
        date = new DateTime(whatToAdd.substring(whatToAdd.indexOf("/") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        arr.add(new Events(message, date, isDone));
    }

    public void addDeadline(String whatToAdd, boolean isDone) throws DukeException, NumberFormatException {
        String message;
        DateTime date;
        date = new DateTime(whatToAdd.substring(whatToAdd.indexOf("/") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        arr.add(new Deadlines(message, date, isDone));
    }

}
