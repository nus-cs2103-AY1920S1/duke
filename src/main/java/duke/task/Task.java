package duke.task;

import duke.exception.DateTimeParseDukeException;

public abstract class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void markIsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getTaskTypeLetter();

    //@@author CarbonGrid. (cleanest and easiest method to print)
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Parses a string taken from saved data, and return Task of corresponding type. T/D/E
     * @param savedTask String in format of "T | 1 | description | Time"
     * @return Task duke.task.Deadline/Event/ToDo
     * @throws DateTimeParseDukeException when datetime is of invalid format
     */
    public static Task parseFileTask(String savedTask) throws DateTimeParseDukeException {
        Task t = null;
        savedTask = savedTask.trim();
        String taskType = savedTask.substring(0, 1);
        String isDone = savedTask.substring(4, 5).trim();
        savedTask = savedTask.substring(8);
        String description;
        String dateTime;

        switch (taskType) {
        case "T":
            description = savedTask.trim();
            t = new ToDo(description);
            break;
        case "E":
            description = savedTask.substring(0, savedTask.indexOf(" (at: "));
            dateTime = savedTask.substring(savedTask.indexOf("(at: ") + 5,savedTask.indexOf(")"));
            t = new Event(description,dateTime);
            break;
        case "D":
            description = savedTask.substring(0, savedTask.indexOf(" (by: "));
            dateTime = savedTask.substring(savedTask.indexOf("(by: ") + 5,savedTask.indexOf(")"));
            t = new Deadline(description,dateTime);
            break;
        default:
            break;
        }

        if (isDone.equals("1")) {
            t.markIsDone();
        }
        return t;
    }
}
