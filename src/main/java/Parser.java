import java.time.LocalDateTime;

/**
 * A class representing a parser.
 */
public class Parser {
    /**
     * Returns true if the command is going to terminate the program.
     * @param command the command to be checked
     * @return true if the command is going to terminate the program.
     */
    public boolean isTerminatingCommand(String command) {
        return command.equals("bye");
    }
    /**
     * Parses the command that typed by the user.
     * @param command the command typed by the user
     * @param taskList a list stores all the tasks
     * @param ui user interface to show messages
     * @throws IllegalDescriptionException If the description of the task is illegal.
     * @throws IllegalCommandException If the command is illegal.
     * @throws IllegalIndexOfTaskException If the index provided is illegal.
     */
    public void parseCommand(String command, TaskList taskList, Ui ui)
            throws IllegalIndexOfTaskException, IllegalDescriptionException, IllegalCommandException {
        if (command.equals("list")) {
            ui.showTasks(taskList);
        } else {
            int indexOfSpace = command.indexOf(' ');
            //if there is no space, assume that the string is a command type
            if (indexOfSpace == -1) {
                indexOfSpace = command.length();
            }
            //seperate command and description of the task
            String type = command.substring(0, indexOfSpace);
            String description = command.substring(indexOfSpace).strip();

            if (type.equals("done")) {
                try {
                    int index = Integer.valueOf(description);
                    taskList.setTaskAtIndexDone(index);
                    ui.showDoneTask(taskList.getTaskAtIndex(index));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new IllegalIndexOfTaskException(
                            "Please provide an valid index of the task.");
                }
            } else if (type.equals("delete")) {
                try {
                    int index = Integer.valueOf(description);
                    Task removedTask = taskList.removeTaskAtIndex(index);
                    ui.showRemovedTask(removedTask, taskList.getSize());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new IllegalIndexOfTaskException(
                            "Please provide an valid index of the task.");
                }
            } else if (type.equals("todo")) {
                Task task = new ToDo(description.strip());
                taskList.addTask(task);
                ui.showAddedTask(task, taskList.getSize());
            } else if (type.equals("deadline")) {
                int sep = description.indexOf("/by");
                if (sep == -1) {
                    throw new IllegalDescriptionException("The format of deadline task is wrong.");
                }
                Task task = new Deadline(description.substring(0, sep).strip(),
                                parseDate(description.substring(sep + 3).strip()));
                taskList.addTask(task);
                ui.showAddedTask(task, taskList.getSize());
            } else if (type.equals("event")) {
                int sep = description.indexOf("/at");
                if (sep == -1) {
                    throw new IllegalDescriptionException("The format of event task is wrong.");
                }
                Task task = new Event(description.substring(0, sep).strip(),
                                parseDate(description.substring(sep + 3).strip()));
                taskList.addTask(task);
                ui.showAddedTask(task, taskList.getSize());
            } else {
                throw new IllegalCommandException(
                        "I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Returns a LocalDateTime object representing the date and time of String date.
     * @param date a String that is to be parsed into date and time
     * @return a LocalDateTime object representing the date and time of String date.
     * @throws IllegalDescriptionException If the date or time is illegal.
     */
    private LocalDateTime parseDate(String date) throws IllegalDescriptionException {
        int indexOfSpace = date.indexOf(" ");
        String time = "";
        try {
            if (indexOfSpace != -1) {
                time = date.substring(indexOfSpace + 1).strip();
                date = date.substring(0, indexOfSpace).strip();
            } else {
                throw new IllegalDescriptionException("The format of date and time is wrong!");
            }

            String[] dayMonthYear = date.split("/");
            LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(dayMonthYear[2]), Integer.parseInt(dayMonthYear[1]),
                    Integer.parseInt(dayMonthYear[0]),
                    Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2)));

            return dateTime;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalDescriptionException("The format of date and time is wrong");
        }
    }
}
