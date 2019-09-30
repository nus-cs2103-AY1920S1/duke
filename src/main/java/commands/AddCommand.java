package commands;

import java.io.IOException;
import core.Storage;
import core.TaskList;
import exceptions.DukeException;
import exceptions.InvalidDukeDateException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDos;

/**
 * Handles user's commands concerning the addition of a 
 * new task into Core.Duke.
 */
public class AddCommand extends Command {

    AddCommandValidator validator = new AddCommandValidator();

    @Override
    public StringBuilder execute(String input, TaskList taskList, Storage storage) throws DukeException, IOException {
        String[] inputAsArr = input.split(" ");
        validator.validateDetail(inputAsArr);
        String command = inputAsArr[0];
        String rest = input.substring(input.indexOf(" ") + 1);
        if (command.equals("todo")) {
            return addTodo(rest, taskList, storage);
        } else if (command.equals("deadline")) {
            return addDeadline(rest, taskList, storage);
        } else {
            return addEvent(rest, taskList, storage);
        }
    }

    private static StringBuilder addTodo(String details, TaskList list, Storage storage) throws IOException {
        Task task = new ToDos(details);
        list.addTask(task, storage);
        return addSuccess(task).append("\n" + "Now you have " 
            + list.getNumberOfTasks() + " tasks in the list.");
    }

    private static String processDeadlineDate(String dateDetails) throws InvalidDukeDateException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        return DateHelper.parseDate(date) + ", " + DateHelper.parseTime(time);
    }

    private StringBuilder addDeadline(String details, TaskList list, Storage storage) 
            throws DukeException, IOException {
        String[] detAsArr = details.split(" /by ");
        validator.validateDeadlineDetails(detAsArr);
        String deadline = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Deadline(deadline, processDeadlineDate(dueDetail));
        list.addTask(task, storage);
        return addSuccess(task).append("\n" + "Now you have " + list.getNumberOfTasks() 
            + " tasks in the list.");
    }

    private static String processEventDate(String dateDetails) throws InvalidDukeDateException {
        String date = dateDetails.split(" ")[0];
        String time = dateDetails.split(" ")[1];
        String[] timeDetails = time.split("-");
        return DateHelper.parseDate(date) + ", " 
                + DateHelper.parseTime(timeDetails[0]) + "-" + DateHelper.parseTime(timeDetails[1]);
    }

    private StringBuilder addEvent(String details, TaskList list, Storage storage) throws DukeException, IOException {
        String[] detAsArr = details.split(" /at ");
        validator.validateEventDetails(detAsArr);
        String event = detAsArr[0];
        String dueDetail = detAsArr[1];
        Task task = new Event(event, processEventDate(dueDetail));
        list.addTask(task, storage);
        return addSuccess(task).append("\n" + "Now you have " + list.getNumberOfTasks() 
            + " tasks in the list.");
    }

    private static StringBuilder addSuccess(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append("\n");
        sb.append("\t" + task.toString());
        assert sb.length() == 0 : "The stringbuilder should not be empty.";
        return sb;
    }
}