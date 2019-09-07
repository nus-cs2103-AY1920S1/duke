/**
 * Deals with loading/saving tasks from/to a file in the hard drive.
 */
public class Storage {
    protected String filePath;

    /**
     * Creates a Storage object with the file's file path as the argument.
     *
     * @param filePath String of file's filepath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }



    /**
     * Converts a String in the valid format into a task object.
     * Used in getTasksFromFile method to extract task objects from a file.
     *
     * @param text String input in required format.
     * @return Task object created from processing the input text.
     * @throws InvalidTaskArgumentDukeException Thrown when the String text is in an invalid format so
     *     the argument entered when creating a task object is invalid.
     */
    public Task stringToTask(String text) throws InvalidTaskArgumentDukeException {
        String[] textSplit = text.split("\\|");
        Task resultTask;

        if (textSplit[0].equals("T")) {
            resultTask = new ToDo(textSplit[2]);
        } else if (textSplit[0].equals("D")) {
            resultTask = new Deadline(textSplit[2], textSplit[3]);
        } else {
            resultTask = new Event(textSplit[2], textSplit[3]);
        }

        if (textSplit[1].equals("1")) {
            resultTask.markAsDone();
        }

        return resultTask;
    }

    /**
     * Converts a task object into its String representation to be saved into a file.
     * Used in loadTasksToFile method to save tasks objects into a file as Strings.
     *
     * @param task Task object to be represented as a String.
     * @return String representation of the argument Task object.
     */
    public String taskToString(Task task) {
        String taskType = "";
        String description = task.getDescription();
        String isDone = "0";

        if (task.isDone()) {
            isDone = "1";
        }

        if (task instanceof ToDo) {
            taskType = "T";
            return taskType + "|" + isDone + "|" + description;
        } else { // event or deadline
            String time = "";

            if (task instanceof Event) {
                taskType = "E";
                time = ((Event) task).getAt();
            } else {
                taskType = "D";
                time = ((Deadline) task).getBy();
            }

            return taskType + "|" + isDone + "|" + description + "|" + time;

        }
    }
}
