package duke;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.DoAfter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Duke.TaskList class contains the task list, and has operations to
 * add/delete the Duke.tasks in the list.
 */
public class TaskList {
    
    protected static ArrayList<Task> tasks;
    
    /**
     * Class constructor.
     *
     * @param tasks an array list of Duke.tasks.
     * @throws DukeException which includes any exceptions when operating on the task list.
     */
    public TaskList(ArrayList<Task> tasks) throws DukeException {
        this.tasks = tasks;
    }
    
    /**
     * This method retrieves the task list.
     *
     * @return the array list of Duke.tasks that keep tracks of the Duke.tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
    /**
     * Add the to do task into the task list.
     *
     * @param input description of the to do task to be added into the task list.
     */
    public String addTodo(String input) {
        try {
            if (!input.substring(4).isEmpty()) {
                String description = input.substring(4);
                Todo todo = new Todo(description);
                tasks.add(todo);
                String result = responseOut(todo);
                return result;
            } else {
                throw new EmptyDescriptionException("todo");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    /**
     * Add the deadline task into the task list.
     *
     * @param input description of the deadline task together with the deadline date to be added into the task list.
     */
    public String addDeadline(String input) {
        try {
            if (input.contains("/by")) {
                int index = input.lastIndexOf("/by");
                String description = input.substring(8, index);
                String by = input.substring(index + 3);
                Date byDeadline = convertStringToDate(by);
                if (description.isBlank()) {
                    throw new EmptyDescriptionException("deadline");
                }
                if (by.isBlank()) {
                    throw new InvalidDescriptionException("deadline");
                }
                Deadline deadline = new Deadline(description, byDeadline);
                tasks.add(deadline);
                String result = responseOut(deadline);
                return result;
            } else {
                throw new InvalidDescriptionException("deadline");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    /**
     * Add the event task into the task list.
     *
     * @param input description of the event task together with the event date to be added into the task list.
     */
    public String addEvent(String input) {
        try {
            if (input.contains("/at")) {
                int index = input.lastIndexOf("/at");
                String description = input.substring(5, index);
                String at = input.substring(index + 3);
                Date atEvent = convertStringToDate(at);
                if (description.isBlank()) {
                    throw new EmptyDescriptionException("event");
                }
                if (at.isBlank()) {
                    throw new InvalidDescriptionException("event");
                }
                Event event = new Event(description, atEvent);
                tasks.add(event);
                String result = responseOut(event);
                return result;
            } else {
                throw new InvalidDescriptionException("event");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public String addDoAfter(String input) {
        try {
            int index = input.indexOf("doAfter");
            if ((input.substring(0, index).isEmpty()) || (input.substring(index + 7).isBlank())) {
                throw new InvalidDescriptionException("addAfter");
            } else {
                String doAfterTask = input.substring(index + 8);
                String before = input.substring(0, index);
                DoAfter doAfter = new DoAfter(before, doAfterTask);
                tasks.add(doAfter);
                String result = responseOut(doAfter);
                return result;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
    
    /**
     * Deletes the task as requested by the user input and removes this task from the
     * task list.
     *
     * @param input Duke.command of the user input which includes the index of the task that
     *              user wants to delete.
     */
    public String deleteTask(String input) {
        String[] inputs = input.split(" ");
        int index = Integer.parseInt(inputs[1]) - 1;
        Task removedTask = tasks.remove(index);
        String result = "";
        result = "Noted. I've removed this task:\n" + removedTask.toString() + "\nNow you have " + tasks.size() + " "
            + "tasks in the list.";
        return result;
    }
    
    /**
     * Completes the task as requested by the user input.
     *
     * @param input Duke.command of the user input which includes the index of the task that
     *              user wants to complete
     */
    public String completeTask(String input) {
        String[] inputs = input.split(" ");
        int index = Integer.parseInt(inputs[1]) - 1;
        tasks.get(index).complete();
        String result = "";
        result = "Nice! I've marked this task as done:\n" + tasks.get(index);
        return result;
    }
    
    /**
     * Find the task according to the keyword given by the user input.
     *
     * @param input Duke.command of the user input which includes the keyword that the user wants to find.
     */
    public String findTask(String input) {
        String keyword = input.substring(5);
        ArrayList<Task> filtered = copy(this.tasks);
        String result = "";
        result += "Here are the matching tasks in your list.\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                result += tasks.get(i).toString() + "\n";
            }
        }
        return result;
    }
    
    /**
     * This method copies the content of an array list to another independent array list.
     *
     * @param tasks the source of content in which will be copied to the other array list.
     * @return an array list which has the copied content.
     */
    public ArrayList<Task> copy(ArrayList<Task> tasks) {
        ArrayList<Task> duplicate = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            duplicate.add(tasks.get(i));
        }
        return duplicate;
    }
    
    /**
     * Static method which prints out the addition of task message each time
     * a task is successfully added into the task list.
     *
     * @param task that has been successfully added into the task list.
     */
    public static String responseOut(Task task) {
        String result = "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size() + " "
            + "tasks in the list.";
        return result;
    }
    
    /**
     * Static method which converts the user input date into a Date instead of a String value.
     *
     * @param input a string input to be converted into the date following a fixed format.
     * @return Date value which has been converted from a string.
     * @throws ParseException if user did not key in the date as the requested format.
     */
    public static Date convertStringToDate(String input) throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        return result;
    }
}
