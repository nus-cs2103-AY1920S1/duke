package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import duke.task.*;

public class TaskList {
    private static String tasklist_header = "\t Here are the tasks in your list:\n";
    private static String done_message = "\t Nice! I've marked this task as done:\n";
    private static String task_added_message = "\t Got it. I've added this task:\n";
    private static String delete_message = "\t Noted. I've removed this task:\n";

    private ArrayList<Task> taskList;
    private Storage storage;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public TaskList(Storage storage) {
        this.storage = storage;
        taskList = storage.loadFromFile();
    }

    public String list() throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("There are no tasks to display.");
        }
        StringBuilder s = new StringBuilder(tasklist_header);
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\t ").append(i + 1).append(".").append(taskList.get(i));
        }

        return s.toString();
    }

    public String markAsDone(int index) throws DukeException {
        Task current;
        try {
            current = taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task with that ID.");
        }
        boolean status = current.markAsComplete();
        if (!status) {
            throw new DukeException("Action already marked as done!");
        }
        String s = done_message + "\t   " + current;
        save();
        return s;
    }

    public String delete(int index) throws DukeException {
        Task current;
        try {
            current = taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task with that ID.");
        }
        String s = delete_message + "\t   " + current;
        save();
        return s;
    }

    public String createTodo(String[] params) throws DukeException {
        String task = Parser.joinStrings(params);
        if (task.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task current = new ToDo(task);
        taskList.add(current);
        String s = task_added_message + "\t   " + current + totalNoOfTasks();
        save();
        return s;
    }

    public String createDeadline(String[] params) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("The description and due date of a deadline cannot be empty.");
        }
        String[] details = Parser.splitByIdentifier(params, "/by");
        if (details[0].isEmpty() && details[1].isEmpty()) {
            throw new DukeException("You need to specify a due date, denoted by /by");
        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        Task current = new Deadline(details[0], LocalDateTime.parse(details[1], formatter));
        taskList.add(current);
        String s = task_added_message + "\t   " + current + totalNoOfTasks();
        save();
        return s;
    }

    public String createEvent(String[] params) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("The description and due date of an event cannot be empty.");
        }
        String[] details = Parser.splitByIdentifier(params, "/at");
        if (details[0].isEmpty() && details[1].isEmpty()) {
            throw new DukeException("You need to specify a due date, denoted by /at");
        } else if (details[0].isEmpty() && !details[1].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        Task current = new Event(details[0], LocalDateTime.parse(details[1], formatter));
        taskList.add(current);
        String s = task_added_message + "\t   " + current + totalNoOfTasks();
        save();
        return s;
    }

    private String totalNoOfTasks() {
        int noOfTasks = taskList.size();
        return "\t Now you have " + (noOfTasks) + (noOfTasks == 1 ? " task" : " tasks") + " in the list.\n";
    }

    private void save() {
        try {
            storage.saveToFile(taskList);
        } catch (IOException e) {
            System.err.println("Error writing task to disk. Your changes were not saved. Check your file permissions?");
        }
    }

}
