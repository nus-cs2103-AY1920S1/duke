public abstract class Task {
    private boolean isDone;
    private String details;

    protected Task(String details) {
        this.isDone = false;
        this.details = details;
    }

    /**
     * Creates the type of task to be added to the list based on user's command.
     * @param command The entire line of command input by the user into the program.
     * @return A Task object that follows the specifications of the input.
     */
    protected static Task create(String command) {
        String[] commandArray = command.split(" ");
        String taskType = commandArray[0];
        command = command.replaceFirst(taskType + " ", "");
        if (taskType.equals("event")) {
            commandArray = command.split(" /at ");
            return new Event(commandArray[0], commandArray[1]);
        } else if (taskType.equals("deadline")) {
            commandArray = command.split(" /by ");
            return new Deadline(commandArray[0], commandArray[1]);
        } else {
            return new ToDo(command);
        }
    }

    /**
     * Indicate that the task is done.
     */
    protected void setDone() {
        this.isDone = true;
    }

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
