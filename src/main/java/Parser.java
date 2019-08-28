import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Reads and processes the commands given to Duke and returns the appropriate action.
 * Generates either todo, deadline or event tasks.
 */
public class Parser {
    /**
     * Generates the appropriate task that matches the command.
     * @param task command to create the task.
     * @return Task that is generated.
     * @throws DukeException
     */
    public static Task generateNewTask(String task) throws DukeException {
        try {
            String type = task.substring(0, task.indexOf(' '));
            String taskDescription = task.substring(task.indexOf(' ') + 1);
            Task newTask = new Task("dummy");

            // Create the appropriate Task type
            if (type.equals("todo")) {
                newTask = new ToDo(taskDescription);
            }

            try {
                if (type.equals("deadline")) {
                    String[] sentence = taskDescription.split("/by");
                    String description = sentence[0];
                    String deadline = formatDateTime(sentence[1]);

                    newTask = new Deadline(description, deadline);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingDateTimeException("DateTime missing."
                        + "Please set a deadline. (Eg. deadline read book /by Sunday)");
            }

            try {
                if (type.equals("event")) {
                    String[] sentence = taskDescription.split("/at");
                    String description = sentence[0];
                    String time = sentence[1];
                    System.out.println(time);
                    newTask = new Event(description, time);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingDateTimeException("Event time period missing."
                        + "Please set a start and end time. (Eg. event dance /at Mon 2-4pm)");
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
        // maybe error here
        DateTimeFormatter dtf=  DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm a");
        String formatted = " " + dtf.format(actualDateTime);
        return formatted;
    }
}