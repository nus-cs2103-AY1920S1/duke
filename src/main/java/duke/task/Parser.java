package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import duke.task.Deadline;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Parser {
    public static Date getDateFromUser(String input) {
        return parseDate(input, "dd/MM/yyyy HHmm");
    }

    public static Date getDateFromFile(String input) {
        return parseDate(input, "EEE MMM dd HH:mm:ss Z yyyy");
    }

    public static Date parseDate(String input, String pattern) {
        Date date = new Date();

        try {
            date = new SimpleDateFormat(pattern).parse(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return date;
    }

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

    public static String handleList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            sb.append(i + 1);
            sb.append(".");
            sb.append(tasks.get(i));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static Task handleDone(TaskList tasks, String input) {
        String[] parts = input.split(" ");
        int num = Integer.parseInt(parts[1]);
        Task task = tasks.get(num - 1);
        task.setDone(true);
        return task;
    }

    public static Task handleDelete(TaskList tasks, String input) {
        String[] parts = input.split(" ");
        int num = Integer.parseInt(parts[1]);
        Task removed = tasks.remove(num - 1);
        return removed;
    }

    public static Task handleItem(TaskList tasks, String input) throws DukeException {
        Task task;

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

        tasks.add(task);
        return task;
    }
}
