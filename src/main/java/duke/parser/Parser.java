package duke.parser;

import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingInputException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a Parser object that deals with making sense of the user command.
 */
public class Parser {

    private Ui ui;

    /**
     * An empty constructor for a Parser object.
     */
    public Parser() {
        this.ui = new Ui();
    }

    /**
     * Executes the specified command by the user.
     * @param command The specified command given by the user.
     * @return the response to the specified user's input.
     * @throws InvalidCommandException if an invalid or recognisable command is given by the user.
     * @throws MissingInputException if there are missing inputs when creating a Deadline or Event task, such as the
     *     deadline or event time and day.
     * @throws MissingDescriptionException if a description is missing for the task that the user is trying to create
     */
    public String getResponseToCommand(String command) throws InvalidCommandException, MissingInputException,
            MissingDescriptionException {
        String[] commandWords = command.trim().toLowerCase().split(" ");
        String commandType = commandWords[0];
        String output;
        TaskList taskList = new TaskList();

        switch (commandType) {
        case "list":
            ArrayList<Task> tasks = taskList.getTaskList();
            output = ui.getListResponse(tasks);
            break;
        case "done":
            String taskNumberString = commandWords[1];
            int taskNumber = Integer.parseInt(taskNumberString);
            output = taskList.getResponseToMarkAsDone(taskNumber);
            break;
        case "delete":
            String taskNumberString1 = commandWords[1];
            int taskNumber1 = Integer.parseInt(taskNumberString1);
            output = taskList.getResponseToDeleteTask(taskNumber1);
            break;
        case "find":
            output = taskList.getResponseToFindTask(commandWords[1]);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            output = getResponseToAddTask(command, commandType);
            break;
        case "bye":
            output = this.ui.getByeResponse();
            break;
        case "help":
            output = this.ui.getHelpResponse();
            break;
        default:
            throw new InvalidCommandException("\tsowwie i dunno what tat means T~T");
        }

        return output;
    }

    /**
     * Adds a task to the task list.
     * @param command The specified command given by the user.
     * @param taskType The type of the task that the user wants to add to the tasks list.
     * @return the appropriate response to the user after the task is added.
     * @throws MissingDescriptionException if a description is missing for the task that the user is trying to create.
     * @throws MissingInputException if there are missing inputs when creating a Deadline or Event task, such as the
     *     deadline or event time and day.
     */
    private String getResponseToAddTask(String command, String taskType) throws MissingDescriptionException,
            MissingInputException {
        String fullDesc = command.substring(taskType.length());
        Task task = new Task();

        if (fullDesc.isEmpty()) {
            throw new MissingDescriptionException("o noes!! ur description of " + taskType + " is empty ( ._.)");
        }

        switch (taskType) {
        case ("todo"):
            task = new Todo(fullDesc);
            break;
        case ("deadline"):
            if (!fullDesc.contains("/by")) {
                throw new MissingInputException("o noes!! i kennut find your deadline cos '/by' is missinggg ( ._.)");
            }

            String[] splitDeadlineDesc = fullDesc.split("/by");
            // to separate the task description from the deadline
            String desc = splitDeadlineDesc[0].trim();

            String deadline;
            try {
                deadline = splitDeadlineDesc[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                // above exception will be thrown when the splitDeadlineDesc only has one element
                // this means that there is nothing after /by
                throw new MissingInputException("o noes!! i kennut find your deadline after '/by' ( ._.)");
            }

            task = new Deadline(desc, deadline);
            break;
        case ("event"):
            if (!fullDesc.contains("/at")) {
                throw new MissingInputException("o noes!! i kennut find ur event date and time cos '/at' is missinggg ( ._.)");
            }

            String[] splitEventDesc = fullDesc.split("/at");
            // to separate the task description from the event time and day
            desc = splitEventDesc[0].trim();

            String when;
            try {
                when = splitEventDesc[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                // above exception will be thrown when the splitEventDesc only has one element
                // this means that there is nothing after /at
                throw new MissingInputException("o noes!! i kennut find ur event date and time after '/at' ( ._.)");
            }

            task = new Event(desc, when);
            break;
        default:
            break;
        }

        TaskList taskList = new TaskList();
        return taskList.getResponseToAddTask(task);
    }

}
