public abstract class Task {
    protected boolean isDone;
    protected String details;

    protected Task(String details) {
        this.isDone = false;
        this.details = details;
    }

    /**
     * Creates the type of task to be added to the list based on user's command.
     * @param command The entire line of command input by the user into the program.
     * @return A Task object that follows the specifications of the input.
     * @throws DukeException Exception thrown if the format is invalid.
     */
    protected static Task create(String command) throws DukeException {
        String[] commandArray = command.split(" ");
        String taskType = commandArray[0];
        command = command.replaceFirst(taskType + " ", "");
        if (taskType.equals("event")) {
            commandArray = command.split(" /at ");
            String details = commandArray[0];
            if (commandArray.length != 2) {
                throw new DukeException("Invalid command format! Proper usage: "
                        + "'event <details> /at <timing>'");
            } else if (details.equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                return new EventTask(commandArray[0], commandArray[1]);
            }
        } else if (taskType.equals("deadline")) {
            commandArray = command.split(" /by ");
            String details = commandArray[0];
            if (commandArray.length != 2) {
                throw new DukeException("Invalid command format! Proper usage: "
                        + "'deadline <details> /by <timing>'");
            } else if (details.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                return new DeadlineTask(commandArray[0], commandArray[1]);
            }
        } else {
            return new ToDoTask(command);
        }
    }

    protected static Task createFromFile(String item) {
        String[] args = item.split(" \\| ");
        String taskType = args[0];
        boolean isAlreadyDone = Integer.parseInt(args[1]) == 1;
        Task newTask;
        if (taskType.equals("D")) {
            newTask = new DeadlineTask(args[2], args[3]);
        } else if (taskType.equals("E")) {
            newTask = new EventTask(args[2], args[3]);
        } else {
            newTask = new ToDoTask(args[2]);
        }
        if (isAlreadyDone) {
            newTask.setDone();
        }
        return newTask;
    }

    /**
     * Indicate that the task is done.
     */
    protected void setDone() {
        this.isDone = true;
    }

    /**
     * String representation of the Task object that can be processed by the program.
     * @return A String that can be written to the save file.
     */
    protected abstract String toFileString();

    /**
     * String representing the Task object.
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        char statusIcon = isDone ? (char) 0x2713 : (char) 0x2718;
        return "[" + statusIcon + "] " + details;
    }
}
