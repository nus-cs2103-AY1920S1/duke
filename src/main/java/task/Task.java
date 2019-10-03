package task;

import exceptions.InvalidInputException;
import exceptions.MissingInputException;

public abstract class Task {

    boolean done;
    private int no;
    String task;
    String type;
    DukeTime time;
    DukeDate date;
    public static String MISSING_DESC_ERROR_MESSAGE = "Task description is incomplete!";
    public static String MISSING_DATE_TIME_MESSAGE = "Task date/time is missing!";

    /**
     * Creates Task with an item number, task description, task type and done status.
     *
     * @param num Task number in the list.
     * @param task Task description
     * @param type Task type (can be Todo, Event or Deadline).
     * @param done Indicates whether task is done.
     */
    Task(int num, String task, String type, boolean done) throws MissingInputException {
        this.no = num;
        this.done = done;
        this.task = task;
        this.type = type;
        if (task.length() < 1) {
            throw new MissingInputException(MISSING_DESC_ERROR_MESSAGE);
        }
    }

    /**
     * Creates Task with an item number, task description, task type and done status.
     * Done status is set as not done by default.
     *
     * @param num Task number in the list.
     * @param task Task description
     * @param type Task type (can be Todo, Event or Deadline).
     */
    Task(int num, String task, String type) throws MissingInputException {
        this.no = num;
        this.done = false;
        this.task = task;
        this.type = type;
        if (task.length() < 1) {
            throw new MissingInputException(MISSING_DESC_ERROR_MESSAGE);
        }
    }

    Task(int num, String task, DukeDate date, DukeTime time, String type, boolean done) throws MissingInputException {
        this.no = num;
        this.done = done;
        this.task = task;
        this.type = type;
        this.time = time;
        this.date = date;

        if (task.length() < 1) {
            throw new MissingInputException(MISSING_DESC_ERROR_MESSAGE);
        }

        if (date == null || time == null) {
            throw new MissingInputException(MISSING_DATE_TIME_MESSAGE);
        }
    }

    Task(int num, String task, DukeDate date, DukeTime time, String type) throws MissingInputException {
        this.no = num;
        this.done = false;
        this.task = task;
        this.type = type;
        this.time = time;
        this.date = date;

        if (task.length() < 1) {
            throw new MissingInputException(MISSING_DESC_ERROR_MESSAGE);
        }

        if (date == null || time == null) {
            throw new MissingInputException(MISSING_DATE_TIME_MESSAGE);
        }
    }

    void updateTaskDesc(String newDesc) {
        this.task = newDesc;
    }

    void updateTaskDate(String dateString) throws MissingInputException, InvalidInputException {
        this.date = DukeDate.processDate(dateString);
    }

    void updateTaskTime(String timeString) throws MissingInputException, InvalidInputException {
        this.time = DukeTime.processTime(timeString);
    }

    void setDone() {
        this.done = true;
    }

    String getTaskInfo() {
        return this.task;
    }

    /**
     * Formats task to be written in given file.
     *
     * @return formatted string for writing in file.
     */
    public abstract String fileFormat();

}
