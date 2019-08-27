import customexceptions.*;
import tasks.TaskList;

public class Parser {
    public static void parseDeadline(String input, TaskList t, Ui ui, Storage s) throws DukeException {
        String deadlineDetails = input.substring(9);
        try {
            int index = deadlineDetails.indexOf("/");
            String name = deadlineDetails.substring(0,index);
            String details = deadlineDetails.substring(index+4);
            Deadline deadline = new Deadline(name, details);
            if (details.length() == 0) {
                throw new DeadlineDetailsEmptyException("OOPS!!! Deadline details cannot be empty");
            }
            t.addTask(deadline);
            ui.showAdded();
            s.writeListToFile(t);
            System.out.println(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.inputWrong();
        }
    }

    public static void parseEvent(String input, TaskList t,Ui ui,Storage s) throws DukeException {
        String eventDetails = input.substring(6);
        try {
            int index = eventDetails.indexOf("/");
            String name = eventDetails.substring(0,index);
            String details = eventDetails.substring(index+3);
            Event event = new Event(name, details);
            if (details.length() == 0) {
                throw new EventDetailsEmptyException("OOPS!!! Event details cannot be empty.");
            }
            t.addTask(event);
            ui.showAdded();
            s.writeListToFile(t);
            System.out.println(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.inputWrong();
        }
    }

    public static void parseToDo(String input, TaskList t,Ui ui,Storage s) throws DukeException {
        if (input.length() == 4) {
            throw new TodoEmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
        ToDo todo = new ToDo(input.substring(5));
        t.addTask(todo);
        ui.showAdded();
        s.writeListToFile(t);
        System.out.println(todo);
    }

    public static void parseDone(String input, TaskList t,Storage s) throws DukeException {
        String[] arr = input.split(" ");
        int number = Integer.parseInt(arr[1]);
        if (number > t.getCommandList().size() + 1) {
            throw new TaskNotFoundException("OOPS!!! tasks.Task number is incorrect");
        }
        t.getCommandList().get(number - 1).complete();
        s.writeListToFile(t);
    }

    public static void parseDelete(String input, TaskList t,Ui ui, Storage s) throws DukeException {
        String[] arr = input.split(" ");
        int number = Integer.parseInt(arr[1]);
        if (number > t.getCommandList().size() + 1) {
            throw new TaskNotFoundException("OOPS!!! tasks.Task number is incorrect");
        }
        ui.showDeleted();
        System.out.println(t.getCommandList().get(number - 1));
        t.deleteTask(number);
        s.writeListToFile(t);
    }
}

