import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Deals with making sense of the user command.
 */

class Parser {

    /**
     * Parses the user input to execute the following intended command.
     *
     * @param input    The user input to execute command.
     * @param ui       User Interface in dealing with duke.
     * @param taskList Task list of the current file.
     * @throws DukeException If user input is in wrong format.
     */

    void parse(String input, Ui ui, TaskList taskList) throws DukeException {
        String command = input.split(" ")[0].trim();
        assert !command.isEmpty() : "Input must not be empty"; //added assert
        switch (command) {
        case "bye":
            if (!input.substring(3).isEmpty()) {
                throw new DukeException("☹ OOPS!!! Type only bye to terminate the program");
            }
            ui.setToFarewell();
            break;
        case "list":
            if (!input.substring(4).isEmpty()) {
                throw new DukeException("☹ OOPS!!! Type only list to display the Task list");
            }
            ui.setToList(taskList);
            break;
        case "todo":
            String todoDescription = input.substring(4).trim();
            if (todoDescription.isEmpty()) {
                throw new DukeException("☹ OOPS!!! todo cannot be empty");
            }
            Task todoTask = new Todo(todoDescription);
            taskList.getTaskList().add(todoTask);
            ui.setToTodo(todoTask, taskList);
            Storage.saveTaskList(taskList.getTaskList()); //saves file
            break;
        case "deadline":
            try {
                String deadlineDescription = input.substring(8).trim();
                String[] deadlineArray = deadlineDescription.split("/by");
                String deadlineName = deadlineArray[0].trim();
                String deadlineBy = deadlineArray[1].trim();
                if (deadlineDescription.isEmpty() || deadlineBy.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! deadline and time cannot be empty");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Task dlTask = new Deadline(deadlineName, sdf.parse(deadlineBy));
                taskList.getTaskList().add(dlTask);
                ui.setToDeadline(dlTask, taskList);
                Storage.saveTaskList(taskList.getTaskList()); //saves file
                break;
            } catch (ParseException e) {
                throw new DukeException("☹ OOPS!!! Please insert correct time format: dd/MM/yyyy HHmm");
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Wrong format. Use: deadline (description) /at dd/MM/yyyy HHmm");
            }
        case "event":
            try {
                String eventDescription = input.substring(5).trim();
                String[] eventArray = eventDescription.split("/at");
                String eventName = eventArray[0].trim();
                String eventAt = eventArray[1].trim();
                if (eventDescription.isEmpty() || eventAt.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! event and time cannot be empty");
                }
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Task eventTask = new Event(eventName, sdf2.parse(eventAt));
                taskList.getTaskList().add(eventTask);
                ui.setToEvent(eventTask, taskList);
                Storage.saveTaskList(taskList.getTaskList()); //save file
                break;
            } catch (ParseException e) {
                throw new DukeException("☹ OOPS!!! Please insert correct time format: dd/MM/yyyy HHmm");
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Wrong format. Use: event (description) /at dd/MM/yyyy HHmm");
            }
        case "done":
            try {
                int taskNum = Integer.parseInt(input.substring(5).trim());
                taskList.getTaskList().get(taskNum - 1).markAsDone();
                ui.setToDone(taskNum, taskList);
                Storage.saveTaskList(taskList.getTaskList()); //save file
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The task number is not in the list");
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Wrong format. Use: done (task number)");
            }
        case "delete":
            try {
                int taskNum2 = Integer.parseInt(input.substring(6).trim());
                ui.setToDelete(taskNum2, taskList);
                taskList.getTaskList().remove(taskNum2 - 1);
                Storage.saveTaskList(taskList.getTaskList()); //save file
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The task number is not in the list");
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Wrong format. Use: delete (task number)");
            }
        case "find":
            try {
                String findWord = input.substring(4).trim();
                ArrayList<Task> relatedTasks = new ArrayList<>();
                ui.setToFind(relatedTasks, taskList, findWord);
                break;
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Wrong format. Use: delete (task number)");
            }
        case "sort":
            try {
                String sortType = input.substring(4).trim();
                if (sortType.equals("event")) {
                    Collections.sort(taskList.getTaskList(), new EventSort());
                } else if (sortType.equals("deadline")) {
                    Collections.sort(taskList.getTaskList(), new DeadlineSort());
                } else if (sortType.equals("todo")) {
                    Collections.sort(taskList.getTaskList(), new TodoSort());
                } else {
                    throw new DukeException("☹ OOPS!!! There are only event/deadline/todo for type of sort");
                }
                ui.setToSort(taskList.getTaskList(), sortType);
                break;
            } catch (Exception e) {
                throw new DukeException("☹ OOPS!!! Your input format is wrong. Use: sort (type of sort)");
            }
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

