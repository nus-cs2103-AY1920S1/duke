package misc;

import exception.DukeException;
import exception.IncorrectDukeCommand;
import exception.InvalidDukeCommand;
import exception.VoidDukeCommand;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Represents a Parser object that converts a form a data input into another so that the
 * program can function.
 */
public class Parser {
    
    /**
     * Writes Tasks as Strings that are saved into a local save file. The parsed String
     * contains information about the task's completion status, Task type and Date/Time (if any).
     * @param task The Task to be parsed into a String and saved into a local save file.
     * @return The parsed String of the Task's details.
     */
    public String writeTaskAsText(Task task) {
        return task.toEncodedString();
    }

    /**
     * Reads Strings (from a local save file) as Tasks. These tasks retain information of
     * all user tasks when the program last exits.
     * @param stringTask The String to be parsed into a Task.
     * @return A Task in the user's task list when the program last exits. 
     */
    Task readTextAsTask(String stringTask) {
        assert stringTask != null : "String task is empty";

        String[] taskContents = stringTask.split(" : ");

        String taskTag = taskContents[0];
        boolean isDone = taskContents[1].equals("1");
        String taskDetails = taskContents[2];

        String dateTime;
        Task outputTask;

        switch (taskTag) {
        case "T":
            outputTask = new ToDo(taskDetails, isDone);
            break;
        case "D":
            dateTime = taskContents[3];
            outputTask = new Deadline(taskDetails, dateTime, isDone);
            break;
        case "E":
            dateTime = taskContents[3];
            outputTask = new Event(taskDetails, dateTime, isDone);
            break;
        default:
            outputTask = null;
            break;
        }

        return outputTask;
    }

    /**
     * Parses an input String into a Date/Time format that can be easily deciphered.
     * @param stringDate The String to be parsed.
     * @param taskType The type of Task for the String to be parsed into.
     * @return A clearer String representation of the Date/Time.
     * @throws DateTimeParseException if the String cannot be parsed into a Date/Time format.
     */
    public String convertStringToTime(String stringDate, String taskType) throws DateTimeParseException {
        String convertedTime;

        switch (taskType) {
        case "deadline":
            convertedTime = formatDeadline(stringDate);
            break;
        case "event":
            String[] dateTime = splitDateTime(stringDate);

            String eventDate = dateTime[0];
            String eventTime = dateTime[1];

            String dateOutput = formatDeadlineDate(eventDate);

            checkEventStartEndExists(eventTime);
            String[] timeStartEnd = eventTime.split("-");
            String startTime = timeStartEnd[0];
            String endTime = timeStartEnd[1];

            convertedTime = formatDeadlineTime(dateOutput, startTime, endTime);
            break;
        default:
            convertedTime = null;
            break;
        } 

        return convertedTime;
    }

    private String formatDeadlineTime(String dateOutput, String startTime, String endTime) {
        DateTimeFormatter eventTimeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter eventTimeOutputFormatter = DateTimeFormatter.ofPattern("hh:mma");

        LocalTime startTimeLdt = LocalTime.parse(startTime, eventTimeInputFormatter);
        LocalTime endTimeLdt = LocalTime.parse(endTime, eventTimeInputFormatter);
        String startTimeOutput = startTimeLdt.format(eventTimeOutputFormatter);
        String endTimeOutput = endTimeLdt.format(eventTimeOutputFormatter);

        return String.format("%s %s to %s", dateOutput, startTimeOutput, endTimeOutput);
    }

    private void checkEventStartEndExists(String eventTime) {
        if (!eventTime.contains("-")) {
            String errorMessage = "You seem to be missing the Start/End time of your event!\n"
                    + "Please follow this format for an Event: 'dd/mm/yy hhmm-hhmm'";
            throw new IncorrectDukeCommand(errorMessage);
        }
    }

    private String formatDeadlineDate(String eventDate) {
        DateTimeFormatter eventDateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        DateTimeFormatter eventDateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate eventDateLdt = LocalDate.parse(eventDate, eventDateInputFormatter);
        return eventDateLdt.format(eventDateOutputFormatter);
    }

    private String[] splitDateTime(String stringDate) {
        String[] dateTime = stringDate.split(" ");

        if (dateTime.length != 2) {
            throw new IncorrectDukeCommand("The Date or Time is missing!");
        }
        return dateTime;
    }

    private String formatDeadline(String stringDate) {
        DateTimeFormatter deadlineInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(stringDate, deadlineInputFormatter);

        DateTimeFormatter deadlineOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return deadlineDateTime.format(deadlineOutputFormatter);
    }

    /**
     * Parses user input instruction into a command that Duke can act on.
     * @param instruction The user instruction to be parsed.
     * @param taskList The TaskList object that stores all of user's tasks.
     * @return A corresponding String to indicate to user that the instruction has been executed successfully.
     * @throws VoidDukeCommand if there is no user input.
     * @throws IncorrectDukeCommand if the input command exists, but Duke cannot understand parts of it.
     * @throws InvalidDukeCommand if the input command does not exist.
     * @throws IOException if local save file cannot be updated to reflect changes.
     * @throws DukeException if an exception is thrown but not caught by any of the other exceptions.
     */
    public String parseInstruction(String instruction, TaskList taskList) throws VoidDukeCommand,
            IncorrectDukeCommand, InvalidDukeCommand, IOException, DukeException {

        Scanner userInput = new Scanner(instruction);
        String command;

        if (userInput.hasNext()) {
            command = userInput.next();
        } else {
            userInput.close();
            throw new VoidDukeCommand();
        }

        String errorMessage = null;

        switch (command) {
        case "list":
            taskList.temporarySearchList = null;

            if (userInput.hasNext()) {
                errorMessage = "The command \"list\" should not have anything after!\n"
                        + "Please remove any additional characters!";
            } else {
                userInput.close();
                return taskList.listAllTasks("list");
            }
            break;
        case "bye":
            if (userInput.hasNext()) {
                errorMessage = "The command \"bye\" should not have anything after!\n"
                        + "Do you really intend to quit?";
            } else {
                userInput.close();
                return Ui.exit();
            }
            break;
        case "done":
            if (userInput.hasNextInt()) {
                int taskNumberDone = userInput.nextInt();
                
                userInput.close();
                return taskMarkedDoneMessage(taskList, taskNumberDone);
            } else {
                errorMessage = "You need to tell me which task to mark as done!";
            }
            break;
        case "delete":
            if (userInput.hasNextInt()) {
                int taskNumberDelete = userInput.nextInt();

                userInput.close();
                return taskDeletedMessage(taskList, taskNumberDelete);
            } else {
                errorMessage = "You need to tell me which task to delete!";
            }
            break;
        case "find":
            if (userInput.hasNext()) {
                String keyword = userInput.nextLine().strip();

                userInput.close();
                return taskList.find(keyword);
            } else {
                errorMessage = "I need a keyword to look for matching tasks!";
            }
            break;
        case "todo":
            taskList.temporarySearchList = null;

            if (userInput.hasNext()) {
                String details = userInput.nextLine().strip();
    
                userInput.close();
                return taskList.makeNewTask(details, null, "todo");
            } else {
                errorMessage = "The description of a todo cannot be empty!";
            }
            break;
        case "deadline":
            taskList.temporarySearchList = null;

            if (userInput.hasNext()) {
                if (instruction.contains(" by ")) {
                    String[] contentDateTime = userInput.nextLine().strip().split(" by ");

                    if (contentDateTime.length == 0 || contentDateTime.length == 1) {
                        errorMessage = "You are missing the details/date&time of your Deadline!";
                    } else if (contentDateTime[0].isBlank()) {
                        errorMessage = "The details of your deadline cannot be empty!";
                    } else if (contentDateTime[1].isBlank()) {
                        errorMessage = "The date/time of your deadline cannot be empty!";
                    } else {
                        String taskDescription = contentDateTime[0].strip();
                        String dateTime = contentDateTime[1].strip();

                        userInput.close();
                        return taskList.makeNewTask(taskDescription, dateTime, "deadline");
                    }
                } else {
                    errorMessage = "Sorry but I can't seem to detect the due date of the deadline!";
                }
            } else {
                errorMessage = "The description of a Deadline cannot be empty!";
            }
            break;
        case "event":
            taskList.temporarySearchList = null;

            if (userInput.hasNext()) {
                if (instruction.contains(" at ")) {
                    String[] contentDateTime = userInput.nextLine().strip().split(" at ");

                    if (contentDateTime.length == 0 || contentDateTime.length == 1) {
                        errorMessage = "You are missing the details/date&time of your Event!";
                    } else if (contentDateTime[0].isBlank()) {
                        errorMessage = "The details of your Event cannot be empty!";
                    } else if (contentDateTime[1].isBlank()) {
                        errorMessage = "The date/time of your Event cannot be empty!";
                    } else {
                        String taskDescription = contentDateTime[0].strip();
                        String dateTime = contentDateTime[1].strip();

                        userInput.close();
                        return taskList.makeNewTask(taskDescription, dateTime, "event");
                    }
                } else {
                    errorMessage = "Sorry but I can't seem to detect the Date & Time of the event!";
                }
            } else {
                errorMessage = "The description of an Event cannot be empty!";
            }
            break;
        default:
            userInput.close();
            throw new InvalidDukeCommand();
        }

        if (errorMessage != null) {
            userInput.close();
            throw new IncorrectDukeCommand(errorMessage);
        }

        userInput.close();
        return null;
    }

    private String taskDeletedMessage(TaskList taskList, int taskNumberDelete) throws IOException {
        assert taskNumberDelete > 0 : "Index for task deleting is negative";
        assert taskNumberDelete % 1 == 0 : "Index for task deleting is non-integer";

        if (taskList.temporarySearchList != null) {
            return taskList.delete(taskNumberDelete, true);
        } else {
            return taskList.delete(taskNumberDelete, false);
        }
    }

    private String taskMarkedDoneMessage(TaskList taskList, int taskNumberDone) throws DukeException, IOException {
        assert taskNumberDone > 0 : "Index for marking task as done is negative";
        assert taskNumberDone % 1 == 0 : "Index for marking task as done is non-integer";

        if (taskList.temporarySearchList != null) {
            return taskList.markTaskAsDone(taskNumberDone, true);
        } else {
            return taskList.markTaskAsDone(taskNumberDone, false);
        }
    }
}
