package tasks;

import customexceptions.*;
import tasks.*;


/**
 * Represents a parser that parses the information that is input by the user.
 */
public class Parser {
    /**
     * Parses a deadline that is input by the user.
     *
     * @param input Input given by the user.
     * @param t     List of tasks input by the user.
     * @param ui    Ui object handling interactions with the user.
     * @param s     Storage object storing data input by the user.
     * @throws DukeException if user input is wrong.
     */
    public static String parseDeadline(String input, TaskList t, Ui ui, Storage s) throws DukeException {
        String deadlineDetails = input.substring(9);
        assert (input.length() >= 8) : "deadline input too short";
        StringBuilder sb = new StringBuilder();
        try {
            int index = deadlineDetails.indexOf("/");
            String name = deadlineDetails.substring(0, index);
            String details = deadlineDetails.substring(index + 4);
            Deadline deadline = new Deadline(name, details);
            if (details.length() == 0) {
                throw new DeadlineDetailsEmptyException("OOPS!!! tasks.Deadline details cannot be empty");
            }
            t.addTask(deadline);
            sb.append(ui.showAdded() + "\n");
            s.writeListToFile(t);
            sb.append(deadline.toString());
            return sb.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.inputWrong();
        }
    }

    /**
     * Parses a event that is input by the user.
     *
     * @param input Input given by the user.
     * @param t     List of tasks input by the user.
     * @param ui    Ui object handling interactions with the user.
     * @param s     Storage object storing data input by the user.
     * @throws DukeException if user input is wrong.
     */
    public static String parseEvent(String input, TaskList t, Ui ui, Storage s) throws DukeException {
        String eventDetails = input.substring(6);
        assert (input.length() > 5) : "input length too short";
        StringBuilder sb = new StringBuilder();
        try {
            int index = eventDetails.indexOf("/");
            String name = eventDetails.substring(0, index);
            String details = eventDetails.substring(index + 3);
            Event event = new Event(name, details);
            if (details.length() == 0) {
                throw new EventDetailsEmptyException("OOPS!!! tasks.Event details cannot be empty.");
            }
            t.addTask(event);
            sb.append(ui.showAdded() + "\n");
            s.writeListToFile(t);
            sb.append(event.toString());
            return sb.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.inputWrong();
        }
    }

    /**
     * Parses a toDo that is input by the user.
     *
     * @param input Input given by the user.
     * @param t     List of tasks input by the user.
     * @param ui    Ui object handling interactions with the user.
     * @param s     Storage object storing data input by the user.
     * @throws DukeException if user input is wrong.
     */
    public static String parseToDo(String input, TaskList t, Ui ui, Storage s) throws DukeException {
        StringBuilder sb = new StringBuilder();
        if (input.length() == 4) {
            throw new TodoEmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
        assert input.length() > 4 : "input length too short";
        ToDo todo = new ToDo(input.substring(5));
        t.addTask(todo);
        sb.append(ui.showAdded() + "\n");
        s.writeListToFile(t);
        sb.append(todo.toString());
        return sb.toString();
    }


    /**
     * Parses a done command that is input by the user.
     *
     * @param input Input given by the user.
     * @param t     List of tasks input by the user.
     * @param s     Storage object storing data input by the user.
     * @throws DukeException if user input is wrong.
     */
    public static String parseDone(String input, TaskList t, Storage s) throws DukeException {
        String[] arr = input.split(" ");
        int number = Integer.parseInt(arr[1]);
        if (number > t.getCommandList().size() + 1) {
            throw new TaskNotFoundException("OOPS!!! tasks.Task number is incorrect");
        }
        String completed = t.getCommandList().get(number - 1).complete();
        s.writeListToFile(t);
        return completed;
    }

    /**
     * Parses a delete command that is input by the user.
     *
     * @param input Input given by the user.
     * @param t     List of tasks input by the user.
     * @param ui    Ui object handling interactions with the user.
     * @param s     Storage object storing data input by the user.
     * @throws DukeException if user input is wrong.
     */
    public static String parseDelete(String input, TaskList t, Ui ui, Storage s) throws DukeException {
        StringBuilder sb = new StringBuilder();
        String[] arr = input.split(" ");
        int number = Integer.parseInt(arr[1]);
        if (number > t.getCommandList().size() + 1) {
            throw new TaskNotFoundException("OOPS!!! tasks.Task number is incorrect");
        }
        sb.append(ui.showDeleted());
        sb.append(t.getCommandList().get(number - 1));
        t.deleteTask(number);
        s.writeListToFile(t);
        return sb.toString();
    }

    public static String parseFind(String input, TaskList t, Ui ui, Storage s) {
        String[] arr = input.split(" ");
        String keyword = arr[1];
        return ui.printMatching(t, keyword);
    }
}

