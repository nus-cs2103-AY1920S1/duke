package duke.task;

import duke.exception.DateTimeParseDukeException;

public abstract class Task {
    private String description;
    private String tagName = null;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, String tagName) {
        this.description = description;
        this.isDone = false;
        this.tagName = tagName;
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

    public String getTagName() {
        return tagName;
    }

    public abstract String getTaskTypeLetter();

    //@@author CarbonGrid. (cleanest and easiest method to print)
    @Override
    public String toString() {
        if (tagName == null) {
            return "[" + this.getStatusIcon() + "] " + this.description;
        } else {
            return "[" + this.getStatusIcon() + "] " + "<" + this.tagName + ">"
                    + this.description;
        }
    }

    /**
     * Parses a single string taken from saved data, and return Task of corresponding type. T/D/E
     * tasks with no tags are saved as "null"
     * @param savedTask String in format of "T | 1 | tagName | description (at: Time)"
     * @return Task duke.task.Deadline/Event/ToDo
     * @throws DateTimeParseDukeException when datetime is of invalid format
     */
    public static Task parseFileTask(String savedTask) throws DateTimeParseDukeException {
        Task t = null;
        savedTask = savedTask.trim();
        String taskType = savedTask.substring(0, 1);
        String isDone = savedTask.substring(4, 5).trim();

        //Format: "null | description (datetime)"
        String secondHalf = savedTask.substring(8);
        String tagName = secondHalf.substring(0, secondHalf.indexOf(" | "));
        boolean hasTag = true;
        if (tagName.equalsIgnoreCase("null")) {
            hasTag = false;
        }

        //Format: "description (datetime)"
        String lastPart = secondHalf.substring(secondHalf.indexOf(" | ") + 3);
        String description;
        String dateTime;

        switch (taskType) {
        case "T":
            description = lastPart.trim();
            if (hasTag) {
                t = new ToDo(description, tagName);
            } else {
                t = new ToDo(description);
            }
            break;
        case "E":
            description = lastPart.substring(0, lastPart.indexOf(" (at: "));
            dateTime = lastPart.substring(lastPart.indexOf("(at: ") + 5,lastPart.indexOf(")"));
            if (hasTag) {
                t = new Event(description, dateTime, tagName);
            } else {
                t = new Event(description, dateTime);
            }
            break;
        case "D":
            description = lastPart.substring(0, lastPart.indexOf(" (by: "));
            dateTime = lastPart.substring(lastPart.indexOf("(by: ") + 5,lastPart.indexOf(")"));
            if (hasTag) {
                t = new Deadline(description, dateTime, tagName);
            } else {
                t = new Deadline(description, dateTime);
            }
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
