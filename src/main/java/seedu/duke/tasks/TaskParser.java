package seedu.duke.tasks;

import seedu.duke.tasks.exceptions.InvalidTaskException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TaskParser {
    private static SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");

    static Task parseTask(String s) throws InvalidTaskException {
        String[] components = s.split(" \\| ", 6);
        if (components.length < 6) {
            throw new InvalidTaskException("Unsupported format of task representation in String, "
                    + "number of components: " + components.length);
        }

        long taskID = parseTaskID(components[0]);
        boolean taskIsDone = parseTaskStatus(components[2]);
        Date creationDate = parseTaskDate(components[3]);
        Date updateDate = parseTaskDate(components[4]);

        if (components[1].equals(TaskType.TODO.getTaskIcon())) {
            return new ToDoTask(taskID, taskIsDone, creationDate, updateDate, components[5]);

        } else if (components[1].equals(TaskType.DEADLINE.getTaskIcon())) {
            String[] deadlineInfo = components[5].split(" \\| ", 2);
            if (deadlineInfo.length < 2) {
                throw new InvalidTaskException("Invalid DeadlineTask task format, doesn't contain time description");
            }
            return new DeadlineTask(taskID, taskIsDone, creationDate, updateDate, deadlineInfo[0], deadlineInfo[1]);

        } else if (components[1].equals(TaskType.EVENT.getTaskIcon())) {
            String[] eventInfo = components[5].split(" \\| ", 2);
            if (eventInfo.length < 2) {
                throw new InvalidTaskException("Invalid EventTask task format, doesn't contain time description");
            }
            return new EventTask(taskID, taskIsDone, creationDate, updateDate, eventInfo[0], eventInfo[1]);

        } else {
            throw new InvalidTaskException("Unsupported task type: " + components[0]);
        }
    }

    private static long parseTaskID(String str) throws InvalidTaskException {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task ID");
        }
    }

    private static boolean parseTaskStatus(String str) throws InvalidTaskException {
        switch (str) {
        case "X":
            return false;
        case "O":
            return true;
        default:
            throw new InvalidTaskException("Unsupported task completion status: " + str);
        }
    }

    private static Date parseTaskDate(String str) throws InvalidTaskException {
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            throw new InvalidTaskException("Invalid task date format: " + str);
        }
    }
}
