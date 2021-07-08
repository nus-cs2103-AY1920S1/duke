package duke.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Parser {
    /**
     * This function parses the date given by the user in the format "dd/Mm/yyyy HHmm".
     * @param input The input given by the user.
     * @return Date
     */
    public static Date getDateFromUser(String input) {
        return parseDate(input, "dd/MM/yyyy HHmm");
    }

    /**
     * This function parses the date given in the storage file in the format "EEE MMM dd HH:mm:ss Z yyyy".
     * @param input The date from the storage file.
     * @return Date
     */
    public static Date getDateFromFile(String input) {
        return parseDate(input, "EEE MMM dd HH:mm:ss Z yyyy");
    }

    /**
     * This function parses the date given in the input according to the pattern given.
     * @param input The input date to parse.
     * @param pattern The pattern to use to parse the input date.
     * @return Date
     */
    public static Date parseDate(String input, String pattern) {
        Date date = new Date();

        try {
            date = new SimpleDateFormat(pattern).parse(input);
        } catch (Exception e) {
            Ui.printError(e.getMessage());
            System.exit(1);
        }

        return date;
    }

    /**
     * This function parses a line in the storage file and returns a Task object.
     * @param input The line in the storage file to parse.
     * @return Task
     * @throws DukeException When the storage file is incorrectly formatted.
     */
    public static Task getTaskFromLine(String input) throws DukeException {
        char type = input.charAt(0);
        String[] parts = input.split("\\Q | \\E");
        Task output;

        if (type == 'T') {
            output = new Todo(parts[2]);
        } else if (type == 'D') {
            output = new Deadline(parts[2], getDateFromFile(parts[3]));
        } else if (type == 'E') {
            String[] dates = parts[3].split(" - ");
            output = new Event(parts[2], getDateFromFile(dates[0]), getDateFromFile(dates[1]));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        if (parts[1].equals("1")) {
            output.setDone(true);
        }

        return output;
    }

    /**
     * This function handles the "list" command by the user and returns all tasks in a human-readable format.
     * @param tasks The list of tasks in the form of a TaskList.
     * @return String
     */
    public static String handleList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        int size = tasks.getTasks().size();

        if (size == 0) {
            sb.append("There are no tasks in the database.");
            return sb.toString();
        }

        sb.append("Here are the list of tasks that you have:\n");

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            sb.append(i + 1);
            sb.append(".");
            sb.append(tasks.get(i));
            sb.append('\n');
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * This function handles the "done" command by the user and marks a particular task as done.
     * @param tasks The list of tasks in the form of a TaskList.
     * @param input The input given by the user and should contain the task ID to mark as done.
     * @return Task
     */
    public static Task handleDone(TaskList tasks, String input) {
        String[] parts = input.split(" ");
        int num = Integer.parseInt(parts[1]);
        Task task = tasks.get(num - 1);
        task.setDone(true);
        return task;
    }

    /**
     * This function handles the "delete" command by the user and deletes a particular task from the TaskList.
     * @param tasks The list of tasks in the form of a TaskList.
     * @param input The input given by the user and should contain the task ID to delete.
     * @return Task
     */
    public static Task handleDelete(TaskList tasks, String input) {
        String[] parts = input.split(" ");
        int num = Integer.parseInt(parts[1]);
        return tasks.remove(num - 1);
    }

    /**
     * This function handles the "find" command by the user and searches for the given keyword in the TaskList.
     * @param tasks The TaskList to find the tasks from.
     * @param input The input given by the user and should contain the search keyword.
     * @return String
     */
    public static String handleFind(TaskList tasks, String input) {
        String[] parts = input.split(" ");
        ArrayList<Task> arr = new ArrayList<>();

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (tasks.get(i).getDescription().contains(parts[1])) {
                arr.add(tasks.get(i));
            }
        }

        return handleList(new TaskList(arr));
    }

    /**
     * This function handles all other additions of new task objects.
     * @param tasks The list of tasks in the form of a TaskList.
     * @param input The input given by the user and should contain the other details needed for a particular Task.
     * @return Task
     * @throws DukeException When the user's input is incorrectly formatted or have missing elements.
     */
    public static Task handleItem(TaskList tasks, String input) throws DukeException {
        Task task;
        boolean toAdd = true;

        if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                task = new Todo(input.substring(5));
            }
        } else if (input.startsWith("deadline")) {
            String[] parts = input.split(" /by ");
            task = new Deadline(parts[0].substring(9), getDateFromUser(parts[1]));
        } else if (input.startsWith("event")) {
            String[] parts = input.split(" /at ");
            String[] fromTo = parts[1].split(" - ");
            task = new Event(parts[0].substring(6), getDateFromUser(fromTo[0]), getDateFromUser(fromTo[1]));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (task.getType().equals(tasks.get(i).getType()) && task.compareTo(tasks.get(i)) == 0) {
                // If we encounter a duplicate, we do not add the task
                toAdd = false;
                break;
            }
        }

        // The task created should not be done yet
        assert !task.isDone() : "Tasks that are just created should not be marked as done";

        if (toAdd) {
            tasks.add(task);
            return task;
        } else {
            return null;
        }
    }
}
