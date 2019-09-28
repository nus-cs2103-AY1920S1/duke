/**
 * Object representing a Task in the TaskList.
 */
public abstract class Task {
    protected boolean isDone;
    protected String details;

    protected Task(String details) {
        this.isDone = false;
        this.details = details;
    }

    /**
     * Indicate that the task is done.
     */
    protected void setDone() {
        this.isDone = true;
    }

    /**
     * Creates the type of task to be added to the list based on user's command.
     *
     * @param command The entire line of command input by the user into the program.
     * @return A Task object that follows the specifications of the input.
     * @throws IceBearException Exception thrown if the format is invalid.
     */
    protected static Task create(String command) throws IceBearException {
        String[] commandArray = command.split(" ");
        String taskType = commandArray[0];
        command = command.replaceFirst(taskType + " ", "");
        if (taskType.equals("event")) {
            return createEventTask(command);
        } else if (taskType.equals("deadline")) {
            return createDeadlineTask(command);
        } else {
            return createToDoTask(command);
        }
    }

    /**
     * Helper function to create an Event task.
     *
     * @param command the original command by the user without the first parameter.
     * @return An event task, if the command is valid.
     * @throws IceBearException Exception thrown if the format is invalid.
     */
    private static EventTask createEventTask(String command) throws IceBearException {
        String[] commandArray = command.split(" /at ");
        String details = commandArray[0];
        if (commandArray.length != 2) {
            throw new IceBearException("Invalid command format! Proper usage: "
                    + "'event <details> /at <timing>'");
        } else if (details.equals("")) {
            throw new IceBearException("The description of an event cannot be empty.");
        } else {
            return new EventTask(commandArray[0], TimedTask.parseDateTime(commandArray[1]));
        }
    }

    /**
     * Helper function to create a Deadline task.
     *
     * @param command the original command by the user without the first parameter.
     * @return A deadline task, if the command is valid.
     * @throws IceBearException Exception thrown if the format is invalid.
     */
    private static DeadlineTask createDeadlineTask(String command) throws IceBearException {
        String[] commandArray = command.split(" /by ");
        String details = commandArray[0];
        if (commandArray.length != 2) {
            throw new IceBearException("Invalid command format! Proper usage: "
                    + "'deadline <details> /by <timing>'");
        } else if (details.equals("")) {
            throw new IceBearException("The description of a deadline cannot be empty.");
        } else {
            return new DeadlineTask(commandArray[0], TimedTask.parseDateTime(commandArray[1]));
        }
    }

    /**
     * Helper function to create a to do task.
     *
     * @param command the original command by the user without the first parameter.
     * @return A to do task, if the command is valid.
     * @throws IceBearException Exception thrown if the format is invalid.
     */
    private static ToDoTask createToDoTask(String command) throws IceBearException {
        if (command.equals("")) {
            throw new IceBearException("The description of a todo cannot be empty.");
        } else {
            return new ToDoTask(command);
        }
    }

    /**
     * Creates the type of task to be added to the list based on the line in the save file.
     *
     * @param item The entire line of command input by the user into the program.
     * @return A Task object according to the line in the save file.
     * @throws IceBearException Exception thrown if the line does not follow the format for some reason.
     */
    protected static Task createFromFile(String item) throws IceBearException {
        String[] args = item.split(" \\| ");
        assert args.length > 1;
        String taskType = args[0];
        boolean isAlreadyDone = Integer.parseInt(args[1]) == 1;
        Task newTask;
        if (taskType.equals("D")) {
            assert args.length == 4;
            newTask = new DeadlineTask(args[2], TimedTask.parseDateTime(args[3]));
        } else if (taskType.equals("E")) {
            assert args.length == 4;
            newTask = new EventTask(args[2], TimedTask.parseDateTime(args[3]));
        } else {
            assert args.length == 3;
            newTask = new ToDoTask(args[2]);
        }
        if (isAlreadyDone) {
            newTask.setDone();
        }
        return newTask;
    }

    /**
     * String representation of the Task object that can be processed by the program.
     *
     * @return A String that can be written to the save file.
     */
    protected abstract String toFileString();

    /**
     * String representing the Task object.
     *
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        char statusIcon = isDone ? (char) 0x2713 : (char) 0x2718;
        return "[" + statusIcon + "] " + details;
    }
}
