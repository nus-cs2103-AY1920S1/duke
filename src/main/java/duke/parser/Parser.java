package duke.parser;

import duke.exception.InvalidArgumentException;
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
     * Executes the specified command given by the user.
     * @param command The specified command given by the user.
     * @return the response to the specified user's input.
     * @throws InvalidCommandException if an invalid or recognisable command is given by the user.
     * @throws InvalidArgumentException if an invalid argument is given by the user.
     * @throws MissingInputException if there are missing inputs when creating a Deadline or Event task, such as the
     *     deadline or event time and day.
     * @throws MissingDescriptionException if a description is missing for the task that the user is trying to create
     */
    public String getResponseToCommand(String command) throws InvalidCommandException, InvalidArgumentException,
            MissingInputException, MissingDescriptionException {
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
            if (commandWords.length == 1) {
                throw new MissingInputException("huh?? i dunno wat task u want me to mark as done :((");
            }

            String taskNumberString = commandWords[1];
            int taskNumber = Integer.parseInt(taskNumberString);
            output = taskList.getResponseToMarkAsDone(taskNumber);
            if (output == null) {
                // task number given falls out of the range of the task list size
                throw new InvalidArgumentException("r u sure u gimme the correct task number?? i kennut find leh");
            }

            break;
        case "delete":
            if (commandWords.length == 1) {
                throw new MissingInputException("huh?? i dunno wat task u want me to delete :((");
            }

            String taskNumberString1 = commandWords[1];
            int taskNumber1 = Integer.parseInt(taskNumberString1);
            output = taskList.getResponseToDeleteTask(taskNumber1);
            if (output == null) {
                // task number given falls out of the range of the task list size
                throw new InvalidArgumentException("r u sure u gimme the correct task number?? i kennut find leh");
            }

            break;
        case "find":
            if (commandWords.length == 1) {
                throw new MissingInputException("huh?? u neh gimme ur keywords, i dunno wat to find :((");
            }

            output = taskList.getResponseToFindTask(commandWords[1]);
            if (output == null) {
                // no matching tasks found
                throw new InvalidArgumentException("eh?? i kennut find any matchy-matchy tasks!!1!");
            }
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
        String fullDesc = command.substring(taskType.length()).trim();
        Task task = new Task();

        if (fullDesc.isEmpty()) {
            throw new MissingDescriptionException("o noes!! ur description of " + taskType + " is empty ( ._.)");
        }

        switch (taskType) {
        case ("todo"):
            task = new Todo(fullDesc);
            break;
        case ("deadline"):
            task = createDeadline(fullDesc);
            break;
        case ("event"):
            task = createEvent(fullDesc);
            break;
        default:
            break;
        }

        TaskList taskList = new TaskList();
        return taskList.getResponseToAddTask(task);
    }

    /**
     * Creates a Deadline task with the specified description and due date and time.
     * @param descAndDueBy the specified string containing the description for the Deadline task and due date and time.
     * @return the created Deadline task.
     * @throws MissingInputException if either the description or due date and time is missing, or both.
     */
    private Task createDeadline(String descAndDueBy) throws MissingInputException {
        if (!descAndDueBy.contains("/by")) {
            throw new MissingInputException("o noes!! i kennut find your deadline cos '/by' is missinggg ( ._.)");
        }

        descAndDueBy = descAndDueBy.trim();
        if (descAndDueBy.startsWith("/by") && descAndDueBy.endsWith("/by")) {
            throw new MissingInputException("harro?! where is ur deadline description and due date and time!!!!1! tsktsk");
        }
        if (descAndDueBy.startsWith("/by")) {
            // deadline description is missing
            throw new MissingInputException("o noess!! i kennut find ur deadline description ( ._.)");
        }
        if (descAndDueBy.endsWith("/by")) {
            // dueBy is missing after "/by"
            throw new MissingInputException("o noes!! i kennut find your due date and time after '/by' ( ._.)");
        }

        String[] descAndDueBySplit = descAndDueBy.split("/by");

        String desc = descAndDueBySplit[0].trim();
        String dueBy = descAndDueBySplit[1].trim();

        return new Deadline(desc, dueBy);
    }

    /**
     * Creates a Event task with the specified description and event date and time.
     * @param descAndWhen the specified string containing the description for the Event task and event date and time.
     * @return the created Eeadline task.
     * @throws MissingInputException if either the description or event date and time is missing, or both.
     */
    private Task createEvent(String descAndWhen) throws MissingInputException{
        if (!descAndWhen.contains("/at")) {
            throw new MissingInputException("o noes!! i kennut find your event date and time cos '/at' is missinggg ( ._.)");
        }

        descAndWhen = descAndWhen.trim();
        if (descAndWhen.startsWith("/at") && descAndWhen.endsWith("/at")) {
            throw new MissingInputException("harro?! where is ur event description and event date and time!!!!1! tsktsk");
        }
        if (descAndWhen.startsWith("/at")) {
            // event description is missing
            throw new MissingInputException("o noess!! i kennut find ur event description ( ._.)");
        }
        if (descAndWhen.endsWith("/at")) {
            // event date and time is missing after "/at"
            throw new MissingInputException("o noes!! i kennut find your event date and time after '/at' ( ._.)");
        }

        String[] descAndWhenSplit = descAndWhen.split("/at");
        String desc = descAndWhenSplit[0].trim();
        String when = descAndWhenSplit[1].trim();
        return new Event(desc, when);
    }

}
