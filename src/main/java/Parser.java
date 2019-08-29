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
        boolean isDone = taskContents[1].equals("0") ? false : true;
        String taskDetails = taskContents[2];
        String dateTime;

        Task outputTask;

        switch (taskTag) {
        case "T":
            ToDo existingTodo = new ToDo(taskDetails, isDone);
            outputTask = existingTodo;
            break;
        case "D":
            dateTime = taskContents[3];
            Deadline existingDeadline = new Deadline(taskDetails, dateTime, isDone);

            outputTask = existingDeadline;            
            break;
        case "E":
            dateTime = taskContents[3];
            Event existingEvent = new Event(taskDetails, dateTime, isDone);

            outputTask = existingEvent;            
            break;
        default:
            outputTask = null;
            break;
        }

        return outputTask;
    }

    String convertStringToTime(String stringDate, String taskType) throws DateTimeParseException {
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
                        + Ui.spaces(5) + "Please follow this format for an Event: 'dd/mm/yy hhmm-hhmm'";
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

    void parseInstruction(String instruction, TaskList taskList) throws VoidDukeCommand,
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

        if (command.equals("list")) {

            if (userInput.hasNext()) {
                errorMessage = "The command \"list\" should not have anything after!\n"
                        + Ui.spaces(5) + "Please remove any additional words!";
            } else {
                taskList.listAlltasks();
            }

        } else if (command.equals("bye")) {

            if (userInput.hasNext()) {
                errorMessage = "The command \"bye\" should not have anything after!\n"
                        + Ui.spaces(5) + "Do you really intend to quit?";
            } else {
                userInput.close();
                return;
            }

        } else if (command.equals("done") || command.equals("delete")) {
            int taskNumber;

            if (userInput.hasNextInt()) {
                taskNumber = userInput.nextInt();

                if (taskNumber <= 0) {
                    errorMessage = String.format("%sNumber cannot be negative!", Ui.spaces(5));
                } else if (taskList.size() == 0) {
                    errorMessage = String.format("%sYou don't have any tasks yet!", Ui.spaces(5));
                } else if (taskNumber > taskList.size()) {
                    errorMessage = String.format("%sYou don't have that many tasks!", Ui.spaces(5));
                } else {
                    switch (command) {
                    case "done":
                        taskList.markTaskAsDone(taskNumber - 1);
                        break;
                    case "delete":
                        taskList.delete(taskNumber - 1);
                        break;
                    default:
                        break;
                    }
                }

            } else {
                errorMessage = "Please input a non-negative Integer after the \"done\" command!\n"
                            + Ui.spaces(5) + "Also do ensure that the Integer is not out of range!";
            }

        } else if (command.equals("todo")) {

            if (userInput.hasNext()) {
                String details = userInput.nextLine().strip();
                taskList.makeNewTodo(details);
            } else {
                errorMessage = "The description of a todo cannot be empty!";
            }

        } else if (command.equals("deadline")) {
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

                        taskList.makeNewDeadline(taskDescription, dateTime);
                    }
                } else {
                    errorMessage = "Sorry but I can't seem to detect the due date of the deadline!";
                }

            } else {
                errorMessage = "The description of a Deadline cannot be empty!";
            }

        }  else if (command.equals("event")) {
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

                        taskList.makeNewEvent(taskDescription, dateTime);
                    }
                } else {
                    errorMessage = "Sorry but I can't seem to detect the Date & Time of the event!";
                }

            } else {
                errorMessage = "The description of an Event cannot be empty!";
            }
        } else {
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
