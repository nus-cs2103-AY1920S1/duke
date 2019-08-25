package main.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;

    private TaskList() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    public static TaskList newInstance() {
        return new TaskList();
    }

    Task get(int idx) {
        return tasks.get(idx);
    }

    List<Task> getTasks() {
        return tasks;
    }

    void clear() {
        tasks.clear();
    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }

    int size() {
        return tasks.size();
    }

    Task addNewTodoTask(String taskName, boolean isDone) {
        Task newTask = new Todo(taskName);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    Task addNewEventTask(String taskName, String additionalInfo, boolean isDone) {
        Task newTask = new Event(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    Task addNewDeadlineTask(String taskName, String additionalInfo, boolean isDone) {

        Task newTask = new Deadline(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    Task deleteTask(int idx) {
        Task task = tasks.remove(idx);
        return task;
    }

    void markAsDone(int idx) {
        tasks.get(idx).setDone();
    }

    void markAsNotDone(int idx) {
        tasks.get(idx).setNotDone();
    }
}
