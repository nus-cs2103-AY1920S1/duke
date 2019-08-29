package misc;

import exception.DukeException;
import exception.IncorrectDukeCommand;
import exception.InvalidDukeCommand;
import exception.VoidDukeCommand;

import task.*;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Parser {
    String writeTaskAsText(Task task) {
        int isDoneStatus = task.isDone ? 1 : 0;
        
        if (task instanceof ToDo) {
            return String.format("T : %d : %s", isDoneStatus, task.description);
        } else if (task instanceof Deadline) {
            return String.format("D : %d : %s : %s", isDoneStatus, task.description,
                    ((Deadline)task).unformattedDateTime);
        } else if (task instanceof Event) {
            return String.format("E : %d : %s : %s", isDoneStatus, task.description,
                    ((Event)task).unformattedDateTime);
        }

        return null;
    }

    Task readTextAsTask(String stringTask) {
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

    public String convertStringToTime(String stringDate, String taskType) throws DateTimeParseException {
        String convertedTime;

        switch (taskType) {
        case "deadline":
            DateTimeFormatter deadlineInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(stringDate, deadlineInputFormatter);

            DateTimeFormatter deadlineOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
            convertedTime = deadlineDateTime.format(deadlineOutputFormatter);
            break;
        case "event":
            String[] dateTime = stringDate.split(" ");

            if (dateTime.length != 2) {
                throw new IncorrectDukeCommand("The Date or Time is missing!");
            }

            String eventDate = dateTime[0];
            String eventTime = dateTime[1];

            DateTimeFormatter eventDateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            DateTimeFormatter eventDateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            LocalDate eventDateLDT = LocalDate.parse(eventDate, eventDateInputFormatter);
            String dateOutput = eventDateLDT.format(eventDateOutputFormatter);

            if (!eventTime.contains("-")) {
                String errorMessage = "You seem to be missing the Start/End time of your event!\n"
                        + Ui.spaces(5) + "Please follow this format for an task.Event: 'dd/mm/yy hhmm-hhmm'";
                throw new IncorrectDukeCommand(errorMessage);
            }

            String[] timeStartEnd = eventTime.split("-");

            String startTime = timeStartEnd[0];
            String endTime = timeStartEnd[1];

            DateTimeFormatter eventTimeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter eventTimeOutputFormatter = DateTimeFormatter.ofPattern("hh:mma");

            LocalTime startTimeLDT = LocalTime.parse(startTime, eventTimeInputFormatter);
            LocalTime endTimeLDT = LocalTime.parse(endTime, eventTimeInputFormatter);
            String startTimeOutput = startTimeLDT.format(eventTimeOutputFormatter);
            String endTimeOutput = endTimeLDT.format(eventTimeOutputFormatter);

            convertedTime = String.format("%s %s to %s", dateOutput, startTimeOutput, endTimeOutput);
            break;
        default:
            convertedTime = null;
            break;
        } 

        return convertedTime;
    }

    public void parseInstruction(String instruction, TaskList taskList) throws VoidDukeCommand,
            IncorrectDukeCommand, InvalidDukeCommand, IOException, DukeException {
        System.out.println(Ui.HORIZONTAL_LINE);

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
            if (userInput.hasNext()) {
                errorMessage = "The command \"list\" should not have anything after!\n"
                        + Ui.spaces(5) + "Please remove any additional words!";
            } else {
                taskList.listAllTasks("list");
            }
            break;
        case "bye":
            if (userInput.hasNext()) {
                errorMessage = "The command \"bye\" should not have anything after!\n"
                        + Ui.spaces(5) + "Do you really intend to quit?";
            } else {
                userInput.close();
                return;
            }
            break;
        case "done":
            int taskNumberDone;

            if (userInput.hasNextInt()) {
                taskNumberDone = userInput.nextInt();

                if (taskList.temporarySearchList != null) {
                    taskList.markTaskAsDone(taskNumberDone, true);
                    taskList.temporarySearchList = null;
                } else {
                    taskList.markTaskAsDone(taskNumberDone, false);
                }
            }
            break;
        case "delete":
            int taskNumberDelete;

            if (userInput.hasNextInt()) {
                taskNumberDelete = userInput.nextInt();

                if (taskList.temporarySearchList != null) {
                    taskList.delete(taskNumberDelete, true);
                    taskList.temporarySearchList = null;
                } else {
                    taskList.delete(taskNumberDelete, false);
                }

            }
            break;
        case "find":
            if (userInput.hasNext()) {
                String keyword = userInput.nextLine().strip();
                taskList.find(keyword);
            } else {
                errorMessage = "I need a keyword to look for matching tasks!";
            }
            break;
        case "todo":
            if (userInput.hasNext()) {
                String details = userInput.nextLine().strip();
                taskList.makeNewTask(details, null, "todo");
            } else {
                errorMessage = "The description of a todo cannot be empty!";
            }
            break;
        case "deadline":
            if (userInput.hasNext()) {
                if (instruction.contains(" by ")) {
                    String[] contentDateTime = userInput.nextLine().strip().split(" by ");

                    if (contentDateTime.length == 0 || contentDateTime.length == 1) {
                        errorMessage = "You are missing the details/date&time of your task.Deadline!";
                    } else if (contentDateTime[0].isBlank()) {
                        errorMessage = "The details of your deadline cannot be empty!";
                    } else if (contentDateTime[1].isBlank()) {
                        errorMessage = "The date/time of your deadline cannot be empty!";
                    } else {
                        String taskDescription = contentDateTime[0].strip();
                        String dateTime = contentDateTime[1].strip();

                        taskList.makeNewTask(taskDescription, dateTime, "deadline");
                    }
                } else {
                    errorMessage = "Sorry but I can't seem to detect the due date of the deadline!";
                }
            } else {
                errorMessage = "The description of a task.Deadline cannot be empty!";
            }
            break;
        case "event":
            if (userInput.hasNext()) {
                if (instruction.contains(" at ")) {
                    String[] contentDateTime = userInput.nextLine().strip().split(" at ");

                    if (contentDateTime.length == 0 || contentDateTime.length == 1) {
                        errorMessage = "You are missing the details/date&time of your task.Event!";
                    } else if (contentDateTime[0].isBlank()) {
                        errorMessage = "The details of your task.Event cannot be empty!";
                    } else if (contentDateTime[1].isBlank()) {
                        errorMessage = "The date/time of your task.Event cannot be empty!";
                    } else {
                        String taskDescription = contentDateTime[0].strip();
                        String dateTime = contentDateTime[1].strip();

                        taskList.makeNewTask(taskDescription, dateTime, "event");
                    }
                } else {
                    errorMessage = "Sorry but I can't seem to detect the Date & Time of the event!";
                }
            } else {
                errorMessage = "The description of an task.Event cannot be empty!";
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
        System.out.println(Ui.HORIZONTAL_LINE);
    }
}
