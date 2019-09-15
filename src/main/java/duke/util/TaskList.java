package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * TaskList class to store list of tasks.
 */
class TaskList {
    private ArrayList<Task> taskList;
    private int count;

    TaskList() {
        this.taskList = new ArrayList<>();
        count = 0;
    }

    /**
     * Constructor for TaskList object.
     *
     * @param taskList List of tasks
     */
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    /**
     * Returns the tasklist.
     *
     * @return list of tasks
     */
    ArrayList<Task> getTaskAsArrayList() {
        return this.taskList;
    }

    /**
     * Displays the tasklist.
     */
    String getListAsString() {
        if (count == 0) {
            return "You have no tasks in your list!";
        }
        String result = "Here are the tasks in your list:\n";
        return IntStream.rangeClosed(0, count - 1)
                        .mapToObj(x -> (x + 1) + ". " + taskList.get(x).toString() + "\n")
                        .reduce(result, (a, b) -> a + b);
    }

    /**
     * Marks the task item as complete in the list.
     *
     * @param index Index of task in the list
     * @throws DukeException if invalid task number is passed to this method
     */
    String markItemComplete(int index) throws DukeException {
        if (index <= 0 || index > count) {
            throw new DukeException("Invalid task number!");
        }
        Task t = taskList.get(index - 1);
        boolean canComplete = t.complete();
        String result;
        if (canComplete) {
            result = "Nice! I've marked this task as done:\n";
            result += t;
        } else {
            result = "Oops! This task is already completed!\n";
            result += t;
        }
        return result;
    }

    /**
     * Deletes a task item from the list.
     *
     * @param index Index of task in the list
     * @return String representation of deleted task
     * @throws DukeException if invalid task number is passed to this method
     */
    String deleteItem(int index) throws DukeException {
        if (index <= 0 || index > count) {
            throw new DukeException("Invalid task number!");
        }
        index--;
        Task t = taskList.get(index);
        taskList.remove(index);
        count -= 1;
        return generateDeleteItemResponse(t);
    }

    private String generateDeleteItemResponse(Task t) {
        String result = "Noted. I've removed this task:\n";
        result += t + "\n";
        if (count == 1) {
            result += "Now you have 1 task in the list";
        } else {
            result += "Now you have " + count + " tasks in the list";
        }
        return result;
    }

    /**
     * Creates a new task from a given input.
     *
     * @param inputParts An array of <code>String</code> split into type of task, name of task and date (if required)
     * @throws DukeException if command is invalid
     */
    String registerNewTask(String[] inputParts) throws DukeException {
        checkCommandValidity(inputParts[0]);
        Task t = addToList(inputParts[1], inputParts[0]);
        return echo(t);
    }

    /**
     * Checks if a given command is valid.
     *
     * @param type Type of command
     * @throws DukeException if command is invalid
     */
    private void checkCommandValidity(String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("I don't know what that means :(");
        }
    }

    private Task addToList(String s, String type) throws DukeException {
        errorIfDescriptionIsEmpty(removeWhitespaces(s));
        switch (type) {
        case "todo":
            taskList.add(new Todo(s));
            break;
        case "deadline":
            checkDeadlineFormat(s);
            this.addDeadlineToList(s);
            break;
        case "event":
            checkEventFormat(s);
            this.addEventToList(s);
            break;
        default:
            throw new DukeException("Invalid task!");
        }
        count += 1;
        return taskList.get(count - 1);
    }

    private String removeWhitespaces(String s) {
        return s.replaceAll("^\\s+", "");
    }

    private void errorIfDescriptionIsEmpty(String desc) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException("Description cannot be empty!");
        }
    }

    private void checkDeadlineFormat(String desc) throws DukeException {
        String[] parts = desc.split("/by");
        if (parts.length < 2) {
            String message = "Date required!\n";
            message += "Format: deadline {task_name} /by {date}";
            throw new DukeException(message);
        } else if (parts.length != 2) {
            String message = "Format: deadline {task_name} /by {date}";
            throw new DukeException(message);
        }
    }

    private void addDeadlineToList(String desc) {
        String[] parts = desc.split("/by");
        taskList.add(new Deadline(
                parts[0].substring(0, parts[0].length() - 1),
                createDateAndTime(parts[1].substring(1))
        ));
    }

    private void checkEventFormat(String desc) throws DukeException {
        String[] parts = desc.split("/at");
        if (parts.length < 2) {
            String message = "Date required!\n";
            message += "Format: event {task_name} /at {date}";
            throw new DukeException(message);
        } else if (parts.length != 2) {
            String message = "Format: event {task_name} /at {date}";
            throw new DukeException(message);
        }
    }

    private void addEventToList(String desc) {
        String[] parts = desc.split("/at");
        taskList.add(new Event(
                parts[0].substring(0, parts[0].length() - 1),
                createDateAndTime(parts[1].substring(1))
        ));
    }

    private String createDateAndTime(String s) {
        String[] parts = s.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("/")) {
                parts[i] = createDate(parts[i]);
            } else if (is24hrFormat(parts[i])) {
                parts[i] = createTime(parts[i]);
            }
        }
        return Arrays.stream(parts)
                     .reduce("", (a, b) -> a + " " + b)
                     .substring(1);
    }

    private boolean is24hrFormat(String time) {
        return isInteger(time) && time.length() == 4 && Integer.parseInt(time) < 2400;
    }

    private String createTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        String min = time.substring(2, 4);
        String timeOfDay = hour > 11 ? "pm" : "am";
        hour = (hour > 12)
                ? (hour - 12)
                : ((hour == 0) ? 12 : hour);
        assert (hour >= 0 && hour <= 12) : "Invalid Time";
        return hour + ":" + min + timeOfDay;
    }

    private String createDate(String date) {
        try {
            String[] parts = date.split("/");
            checkDateFormat(parts);
            isAllIntegers(parts);
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];
            checkDayAndMonth(day, month, year);
            checkYear(year);
            return createFixedDateFormat(day, month, year);
        } catch (DukeException e) {
            return date;
        }
    }

    private String createFixedDateFormat(String day, String month, String year) {
        String[] months = {"", "Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"
        };
        return day + " " + months[Integer.parseInt(month)] + " " + year;
    }

    private void checkYear(String year) throws DukeException {
        if (year.length() != 4) {
            throw new DukeException("Invalid year!");
        }
    }

    private void checkDayAndMonth(String day, String month, String year) throws DukeException {
        int d = Integer.parseInt(day);
        int m = Integer.parseInt(month);
        if (monthHas31Days(m)) {
            //month has 31 days
            withinRange(d, 31);
        } else if (monthHas30Days(m)) {
            //month has 30 days
            withinRange(d, 30);
        } else if (m == 2) {
            //february
            int y = Integer.parseInt(year);
            if (isLeapYear(y)) {
                withinRange(d, 29);
            } else {
                withinRange(d, 28);
            }
        }
        assert false : "Invalid month!";
    }

    private boolean monthHas31Days(int m) {
        int[] have31Days = {1, 3, 5, 7, 8, 10, 12};
        return Arrays.stream(have31Days).filter(x -> x == m).count() == 1;
    }

    private boolean monthHas30Days(int m) {
        int[] have30Days = {4, 6, 9, 11};
        return Arrays.stream(have30Days).filter(x -> x == m).count() == 1;
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    private void withinRange(int target, int high) throws DukeException {
        if ((target <= 0) || (target > high)) {
            throw new DukeException("Invalid day!");
        }
    }

    private void checkDateFormat(String[] parts) throws DukeException {
        if (parts.length != 3) {
            throw new DukeException("Wrong number of arguments for date");
        }
    }

    private void isAllIntegers(String[] parts) throws DukeException {
        for (String part : parts) {
            if (!isInteger(part)) {
                throw new DukeException("Wrong date format");
            }
        }
    }

    private boolean isInteger(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String echo(Task t) {
        String result = "Got it. I've added this task:\n";
        result += t + "\n";
        if (count == 1) {
            result += "Now you have 1 task in the list.";
        } else {
            result += "Now you have " + count + " tasks in the list.";
        }
        return result;
    }

    /**
     * Returns a list of results from a given search query.
     *
     * @param name Search query
     * @return String of all tasks containing the search query
     */
    String findItem(String name) {
        String initial = "Here are the matching tasks in your list:\n";
        return IntStream.rangeClosed(0, count - 1)
                .mapToObj(x -> (x + 1) + "." + taskList.get(x).toString() + "\n")
                .filter(task -> task.contains(name))
                .reduce(initial, (a, b) -> a + b);
    }
}
