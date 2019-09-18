import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Reads and processes the commands given to Duke and returns the appropriate action.
 * Generates either todo, deadline or event tasks.
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a Parser object with the given TaskList, Ui, Storage.
     * @param taskList TaskList that duke keeps track of.
     * @param ui Generates the output string.
     * @param storage Accesses the given file.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Processes the Command given by the user to Duke.
     * @param command String representing the command given to Duke.
     * @return A string representing the reply from Duke.
     */
    public String processCommand(String command) {
        String reply = "";
        String commandType = command; //default value
        if (command.contains(" ")) {
            commandType = command.substring(0, command.indexOf(" "));
        }

        switch (commandType) {
        case("list"):
            String list = taskList.printAllTasks();
            reply = "Woof! Here are the tasks in your list:" + "\n" + list;
            break;
        case("done"):
            reply = executeDone(command);
            break;
        case("delete"):
            reply = executeDelete(command);
            break;
        case("find"):
            reply = executeFind(command);
            break;
        case("bye"):
            reply = ui.showFarewell();
            break;
        default:
            if (containsTask(commandType)) {
                // Processes task command, not the same as generateNewTask()
                reply = executeNewTask(command);
            } else {
                // Unknown command
                reply = ui.showErrorMessage(new UnknownTaskTypeException());
            }
        }

        return reply;
    }

    /**
     * Processes the command to generate a new task. If command is not empty, it calls generateNewTask() and saves the
     * list automatically.
     * @param command string given
     * @return the response from duke, either successful or error.
     */
    private String executeNewTask(String command) {
        String reply;

        try {
            if (!command.isEmpty()) {
                Task newTask = Parser.generateNewTask(command);
                taskList.addTask(newTask);

                // Save the new list to storage
                try {
                    saveToStorage();
                } catch (IOException e) {
                    reply = ui.showLoadingError();
                    return reply;
                }
                reply = ui.showAddTask(newTask, taskList.numTasks);
            } else {
                throw new UnknownTaskTypeException();
            }
        } catch (DukeException err) {
            reply = ui.showErrorMessage(err);
        }

        return reply;
    }

    /**
     * Generates the appropriate task that matches the command.
     * @param task command to create the task.
     * @return Task that is generated.
     * @throws DukeException Exceptions specific to Duke
     */
    public static Task generateNewTask(String task) throws DukeException {
        try {
            String type = task.substring(0, task.indexOf(' '));
            String taskDescription = task.substring(task.indexOf(' ') + 1);
            boolean hasTags = false;
            ArrayList<String> tags = new ArrayList<>();

            // Process all tags
            if (taskDescription.contains("#")) {
                hasTags = true;
                String[] processed = taskDescription.split("#");
                taskDescription = processed[0];

                // Add all tags
                for (int i = 1; i < processed.length; i++) {
                    String tag = processed[i];
                    tags.add(tag);
                }
            }

            Task newTask = new Task("dummy");

            // Create the appropriate Task type
            try {
                switch (type) {
                case("todo"):
                    newTask = generateNewToDo(taskDescription);
                    break;
                case("deadline"):
                    newTask = generateNewDeadline(taskDescription);
                    break;
                case("event"):
                    newTask = generateNewEvent(taskDescription);
                    break;
                default:
                    // Should not enter this as the type is already filtered and must be one of todo/deadline/event.
                }
            } catch (DukeException e) {
                String taskWithIssue = e.getMessage();
                throw new MissingDateTimeException("Woof Woof!! Date Time missing."
                        + "Please set a date and time. (Eg. " + taskWithIssue +  " read book /by Sunday)");
            }

            // Add all tags to the new task.
            if (hasTags) {
                for (String tag : tags) {
                    newTask.addTag(tag);
                }
            }

            return newTask;

        } catch (StringIndexOutOfBoundsException rootError) {
            // If task type is correct, the error is due to empty description
            // else the task type is unknown
            if (task.equals("todo") | task.equals("deadline") | task.equals("event")) {
                throw new EmptyDescriptionException(task, rootError);
            } else {
                throw new UnknownTaskTypeException();
            }
        }
    }

    /**
     * Executes the command for "done".
     * @param command string to be carried out.
     * @return the reply from Duke.
     */
    private String executeDone(String command) {
        String[] sentence = command.split(" ");
        String reply;

        try {
            if (sentence[0].equals("done")) { // Check if the first word is done
                String taskIndexAsString = sentence[1];
                assert isInteger(taskIndexAsString) : "Wrong format. Done command needs an integer index of task.";
                int taskIndexAsInteger = Integer.parseInt(sentence[1]);

                // If it wasn't marked before, this would print out a notification saying it is now marked.
                reply = taskList.markAsDone(taskIndexAsInteger);

                // Save new list to storage
                try {
                    saveToStorage();
                } catch (IOException e) {
                    reply = ui.showLoadingError();
                }

            } else {
                throw new UnknownTaskTypeException();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            reply = ui.promptDoneCompletion(); // if task index is not given (Eg "done")
        } catch (IndexOutOfBoundsException e) {
            reply = ui.promptList();           // if the index given exceeds the number of tasks on the list
        } catch (NumberFormatException e) {
            reply = ui.promptDoneFormat();     // if the string after done is not an integer (Eg "done two")
        } catch (UnknownTaskTypeException e) {
            reply = ui.showErrorMessage(e);    // if the command is unknown (Eg "done22")
        }

        return reply;
    }

    /**
     * Executes the command for "delete".
     * @param command string to be carried out.
     * @return the reply from Duke.
     */
    private String executeDelete(String command) {
        String[] sentence = command.split(" ");
        String reply;

        try {
            if (sentence[0].equals("delete")) {
                String taskIndexAsString = sentence[1];
                assert isInteger(sentence[1]) : "Wrong format. Delete command needs an integer index of task";
                int taskIndex = Integer.parseInt(taskIndexAsString);
                Task deletedTask = taskList.deleteTask((taskIndex - 1));
                reply = ui.showDeletedTask(deletedTask, taskList.numTasks);

                // Save new list to storage
                try {
                    saveToStorage();
                } catch (IOException e) {
                    reply = ui.showLoadingError();
                }

            } else {
                throw new UnknownTaskTypeException();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            reply = ui.promptDeleteCompletion();    // if task index is not given (Eg "delete")
        } catch (IndexOutOfBoundsException e) {
            reply = ui.promptList();                // if index of task to be deleted does not exist
        } catch (NumberFormatException e) {
            reply = ui.promptDeleteFormat();        // if the string after delete is not an integer (Eg "delete two")
        } catch (UnknownTaskTypeException e) {
            reply = ui.showErrorMessage(e);         // if the command is unknown (Eg "deleted")
        }

        return reply;
    }

    /**
     * Executes the command for "find".
     * @param command string to be carried out.
     * @return the reply from duke.
     */
    private String executeFind(String command) {
        String[] sentence = command.split(" ");
        String reply;
        String keyword = "";

        try {
            if (sentence[0].equals("find")) {
                keyword = sentence[1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            reply = ui.promptFindKeyword();
            return reply;
        }

        reply = ui.showSearchList(taskList.searchFor(keyword));
        return reply;
    }

    /**
     * Generate new ToDo object with the given description.
     * @param taskDescription of the ToDo task.
     * @return new ToDo object.
     */
    private static ToDo generateNewToDo(String taskDescription) {
        return new ToDo(taskDescription);
    }

    /**
     * Generates new Deadline object with the given description.
     * @param taskDescription of the Deadline task.
     * @return new Deadline object.
     * @throws DukeException of incorrect Date Time format.
     */
    private static Deadline generateNewDeadline(String taskDescription) throws DukeException {
        String description;
        String deadline;

        try {
            // If the given Date Time is in the correct format.
            String[] sentence = taskDescription.split("/by");
            description = sentence[0];
            try {
                deadline = formatDateTime(sentence[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (!sentence[1].isEmpty()) {
                    deadline = sentence[1];           // If the Date Time is in another format.
                } else {
                    throw e;                          // No Date Time given for deadline.
                }
            }
            return new Deadline(description, deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Deadline");
        }
    }

    /**
     * Generates new Event object with the given description.
     * @param taskDescription of the Event task.
     * @return new Event object.
     * @throws DukeException of incorrect DateTime format.
     */
    private static Event generateNewEvent(String taskDescription) throws DukeException {
        String description;
        String time;

        try {
            // If the given Date Time is in the correct format.
            String[] sentence = taskDescription.split("/at");
            description = sentence[0];
            try {
                time = formatDateTime(sentence[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (!sentence[1].isEmpty()) {
                    time = sentence[1];           // If the Date Time is in another format.
                } else {
                    throw e;                      // No Date Time given for event.
                }
            }
            return new Event(description, time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Event");
        }
    }

    /**
     * Formats the date time given as a string.
     * @param deadline to be formatted.
     * @return String representing the formatted date time.
     */
    private static String formatDateTime(String deadline) {
        // Split to individual components
        deadline = deadline.trim();
        String[] dd = deadline.split(" ");
        String[] date = dd[0].split("/");
        String time = dd[1];
        int hours = Integer.valueOf(time.substring(0,2));
        int minutes = Integer.valueOf(time.substring(2));

        LocalDateTime actualDateTime = LocalDateTime.of(Integer.valueOf(date[2]), Integer.valueOf(date[1]),
                Integer.valueOf(date[0]), hours, minutes);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm a");
        String formatted = " " + dtf.format(actualDateTime);
        return formatted;
    }

    /**
     * Determines if a string is an integer.
     * @param ss to check if it is an integer.
     * @return true or false
     */
    private boolean isInteger(String ss) {
        try {
            Integer.parseInt(ss);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }

        // Only reaches here if input string is an integer and no exceptions are thrown.
        return true;
    }

    /**
     * Saves the newest version of the list to the file.
     * @throws IOException if the save fails.
     */
    private void saveToStorage() throws IOException {
        storage.saveToFile(taskList.toString());
    }

    /**
     * Checks if the command contains any tasks.
     * @param command string to be checked.
     * @return true is the given string contains tasks, false otherwise.
     */
    private boolean containsTask(String command) {
        boolean containsToDo = command.contains("todo");
        boolean containsDeadline = command.contains("deadline");
        boolean containsEvent = command.contains("event");
        boolean containsAnyTask = (containsToDo | containsDeadline | containsEvent);
        return containsAnyTask;
    }

}