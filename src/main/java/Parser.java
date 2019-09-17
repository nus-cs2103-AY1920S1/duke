import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Platform;

/**
 * Represents a Parser which is used to parse the user input depending on their commands.
 */
public class Parser {

    /**
     * Parses user input based on different type of command.
     * For each case, different parse requirement is needed
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    public static void parse(TaskList taskList, Ui ui, String userInput) throws DukeException {
        String inputType = userInput.split(" ")[0].trim();

        switch (inputType) {
        case "todo":
            handleTodoInput(taskList, ui, userInput);
            break;
        case "list":
            handleListInput(taskList, ui, userInput);
            break;
        case "deadline":
            handleDeadlineInput(taskList, ui, userInput);
            break;
        case "event":
            handleEventInput(taskList, ui, userInput);
            break;
        case "done":
            handleDoneInput(taskList, ui, userInput);
            break;
        case "delete":
            handleDeleteInput(taskList, ui, userInput);
            break;
        case "find":
            handleFindInput(taskList, ui, userInput);
            break;
        case "sortby":
            handleSortInput(taskList, ui, userInput);
            break;
        case "bye":
            handleByeInput();
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Converts string to dateTime object in java.
     *
     * @param str String that will be converted.
     * @return Date Time object if str can be converted.
     * @throws ArrayIndexOutOfBoundsException If string can not be converted
     */
    public static LocalDateTime dateTimeConverter(String str) throws ArrayIndexOutOfBoundsException {
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("/");
        String time = dateTime[1];
        return LocalDateTime.of(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]), Integer.parseInt(date[0]),
                        Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2)));

    }

    /**
     * Parses "todo" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleTodoInput(
            TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            String description = userInput.substring(4).trim();
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task newTodo = new Todo(description, false);
            taskList.addTask(newTodo);
            ui.printAddTask(taskList, newTodo);
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: todo [task description]");
        }
    }

    /**
     * Parses "deadline" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleDeadlineInput(
            TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(8).trim();
            String[] statement = userInput.split("/by");
            String taskDescription = statement[0].trim();
            String taskBy = statement[1].trim();

            if (taskDescription.isBlank() || taskBy.isBlank()) {
                throw new DukeException("OOPS!!! Task description/Task by can not be empty");
            }

            Task newDeadline = new Deadline(taskDescription, false, dateTimeConverter(taskBy));
            taskList.addTask(newDeadline);
            ui.printAddTask(taskList, newDeadline);

        } catch (Exception e) {
            throw new DukeException("OOPS!!! Format is wrong. "
                + "Use: deadline [task description] /by [dd/mm/yyyy HHmm]");
        }
    }

    /**
     * Parses "event" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    public static void handleEventInput(
            TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(5).trim();
            String[] statement = userInput.split("/at");
            String taskDescription = statement[0].trim();
            String taskAt = statement[1].trim();

            if (taskDescription.isBlank() || taskAt.isBlank()) {
                throw new DukeException("OOPS!!! Task description/Task at can not be empty");
            }


            Task newEvent = new Event(taskDescription, false, dateTimeConverter(taskAt));
            taskList.addTask(newEvent);
            ui.printAddTask(taskList, newEvent);

        } catch (Exception e) {
            throw new DukeException("OOPS!!! Format is wrong. Use:event [task description] /at [dd/mm/yyyy HHmm]");
        }
    }

    /**
     * Parses "list" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleListInput(
            TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(4);
            if (!userInput.isBlank()) {
                throw new IllegalArgumentException();
            }
            ui.printTaskList(taskList);
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: list");
        }
    }

    /**
     * Parses "done" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleDoneInput(
        TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(4).trim();
            int taskNumber = Integer.parseInt(userInput.trim()) - 1;
            taskList.getListOfTasks().get(taskNumber).markAsDone();
            ui.printDoneTask(taskList, taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number you specified is not in the list.");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: done [task number]");
        }
    }

    /**
     * Parses "delete" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleDeleteInput(
        TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(6).trim();
            int taskNumber = Integer.parseInt(userInput.trim()) - 1;
            Task deletedTask = taskList.getListOfTasks().get(taskNumber);
            taskList.getListOfTasks().remove(taskNumber);
            ui.printDeleteTask(taskList, deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number you specified is not in the list.");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: delete [task number]");
        }
    }

    /**
     * Parses "find" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleFindInput(
        TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(4).trim();
            String keyword = userInput.trim();
            ArrayList<Task> filterTasks = new ArrayList<>();
            for (Task task : taskList.getListOfTasks()) {
                if (task.getTaskDescription().contains(keyword)) {
                    filterTasks.add(task);
                }
            }
            ui.printFindTasks(filterTasks);
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: find [task description]");
        }
    }

    /**
     * Parses "sortby" user input type.
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param userInput The user input to be parsed.
     * @throws DukeException If user input is not in the format
     */
    private static void handleSortInput(
            TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(6).trim();
            if (userInput.equals("deadline")) {
                Collections.sort(taskList.getListOfTasks(), new SortByDeadline());
            } else {
                throw new DukeException("OOPS!!! Category that you specified does not exist");
            }
            ui.printSortTasks(taskList.getListOfTasks(), userInput);
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: sortby [type of sort]");
        }
    }

    private static void handleByeInput() {
        Platform.exit();
    }
}