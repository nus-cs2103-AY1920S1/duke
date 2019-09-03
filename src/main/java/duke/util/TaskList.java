package duke.util;
/**
 * TaskList class to store list of tasks
 */

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int count;

    /**
     * Constructor for TaskList object
     * @param taskList List of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    /**
     * Returns the tasklist
     * @return list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Displays the tasklist
     */
    public String getListAsString() {
        if (count == 0) {
            return "You have no tasks in your list!";
        }
        String result = "Here are the tasks in your list:\n";
        for (Task t : taskList) {
            String current = t.getIndex() + "." + t + "\n";
            result += current;
        }
        return result;
    }

    /**
     * Marks the task item as complete in the list
     * @param index Index of task in the list
     * @throws DukeException if invalid task number is passed to this method
     */
    public String markItemComplete(int index) throws DukeException {
        if (index <= 0 || index > count) {
            throw new DukeException("Invalid task number!");
        }
        Task t = taskList.get(index - 1);
        t.complete();
        String result = "Nice! I've marked this task as done:\n";
        result += t;
        return result;
    }

    /**
     * Deletes a task item from the list
     * @param index Index of task in the list
     * @throws DukeException if invalid task number is passed to this method
     */
    public String deleteItem(int index) throws DukeException {
        if (index <= 0 || index > count) {
            throw new DukeException("Invalid task number!");
        }
        index--;
        Task t = taskList.get(index);
        taskList.remove(index);
        String result = "Noted. I've removed this task:\n";
        result += t + "\n";
        count -= 1;
        if (count == 1) {
            result += "Now you have 1 task in the list";
        } else {
            result += "Now you have " + count + " tasks in the list";
        }
        return result;
    }

    /**
     * Creates a new task from a given input
     * @param inputParts An array of <code>String</code> split into type of task, name of task and date (if required)
     * @throws DukeException if command is invalid
     */
    public String registerNewTask(String[] inputParts) throws DukeException {
        checkCommandValidity(inputParts[0]);
        Task t = addToList(inputParts[1], inputParts[0]);
        return echo(t);
    }

    /**
     * Checks if a given command is valid
     * @param type Type of command
     * @throws DukeException if command is invalid
     */
    static void checkCommandValidity(String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("I don't know what that means :(");
        }
    }

    /**
     * Adds a task item to the tasklist
     * @param s name of task
     * @param type type of task
     * @return Task object to be appended to the tasklist
     * @throws DukeException if description of task is empty or if format is incorrect
     */
    public Task addToList(String s, String type) throws DukeException {
        String trimmed = s.replaceAll("^\\s+", "");
        if (trimmed.equals("")) {
            throw new DukeException("Description cannot be empty!");
        }
        if (type.equals("todo")) {
            taskList.add(new Todo(s));
        } else if (type.equals("deadline")) {
            String[] parts = s.split("\\/" + "by");
            if (parts.length < 2) {
                String message = "Date required!\n";
                message += "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
                throw new DukeException(message);
            } else if (parts.length != 2) {
                String message = "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
                throw new DukeException(message);
            }
            taskList.add(new Deadline(
                    parts[0].substring(0, parts[0].length() - 1),
                    createDateAndTime(parts[1].substring(1))
            ));
        } else if (type.equals("event")) {
            String[] parts = s.split("\\/" + "at");
            if (parts.length < 2) {
                String message = "Date required!\n";
                message += "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
                throw new DukeException(message);
            } else if (parts.length != 2) {
                String message = "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
                throw new DukeException(message);
            }
            taskList.add(new Event(
                    parts[0].substring(0, parts[0].length() - 1),
                    createDateAndTime(parts[1].substring(1))
            ));
        }
        count += 1;
        return taskList.get(count - 1);
    }

    /**
     * Creates fixed date/time format from given string
     * @param s string to be interpreted as date/time formate
     * @return string in the fixed format
     */
    static String createDateAndTime(String s) {
        String[] parts = s.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("/")) {
                parts[i] = createDate(parts[i]);
            } else if (is24hrFormat(parts[i])) {
                parts[i] = createTime(parts[i]);
            }
        }
        String result = "";
        for (String part : parts) {
            result += " " + part;
        }
        return result.substring(1);
    }

    /**
     * Checks if the input time is in 24 hour format
     * @param time input time
     * @return true if time is 24 hour format, false otherwise
     */
    static boolean is24hrFormat(String time) {
        return isInteger(time) && time.length() == 4 && Integer.parseInt(time) < 2400;
    }

    /**
     * Creates a fixed time format
     * @param time input time
     * @return fixed time format as a <code>String</code>
     */
    static String createTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        String min = time.substring(2, 4);
        String timeOfDay = hour > 11 ? "pm" : "am";
        hour = (hour > 12)
                ? (hour - 12)
                : ((hour == 0) ? 12 : hour);
        return hour + ":" + min + timeOfDay;
    }
    /**
     * Creates a fixed date format
     * @param date input time
     * @return fixed date format as a <code>String</code>
     */
    static String createDate(String date) {
        String[] parts = date.split("/");
        String[] month = {
            "",
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        };
        boolean validDate = true;
        if (parts.length == 3) {
            for (int i = 0; i < 3; i++) {
                if (!isInteger(parts[i])) {
                    validDate = false;
                } else if (i == 1
                        && (Integer.parseInt(parts[i]) < 1 || Integer.parseInt(parts[i]) > 12)) {
                    validDate = false;
                }
            }
        } else {
            validDate = false;
        }
        if (validDate) {
            if (parts[2].length() == 4) {
                return parts[0] + " " + month[Integer.parseInt(parts[1])] + " " + parts[2];
            } else {
                return parts[2] + " " + month[Integer.parseInt(parts[1])] + " " + parts[0];
            }
        } else {
            return date;
        }
    }

    /**
     * Checks if a given string is an integer
     * @param n input string
     * @return true if given string is an integer, false otherwise
     */
    static boolean isInteger(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Echos the task in a fixed format
     * @param t Task object
     */
    public String echo(Task t) {
        String result = "Got it. I've added this task:\n";
        result += t + "\n";
        if (count == 1) {
            result += "Now you have 1 task in the list.";
        } else {
            result += "Now you have " + count + " tasks in the list.";
        }
        return result;
    }

    public String findItem(String name) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getName().contains(name)) {
                results.add(t);
            }
        }
        String result = "Here are the matching tasks in your list:\n";
        for (Task t : results) {
            String current = t.getIndex() + "." + t + "\n";
            result += current;
        }
        return result;
    }
}
