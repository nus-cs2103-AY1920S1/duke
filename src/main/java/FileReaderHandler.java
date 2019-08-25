import java.util.List;

public class FileReaderHandler {
    TaskList tasks;

    public FileReaderHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    public void readLineFromFileToList(String taskType, boolean isCompleted, String taskString) {

        try {
            switch (taskType) {
                case "TODO":
                    tasks.addTask(new Todo(taskString, isCompleted), false);
                    break;
                case "DEADLINE":
                    String[] deadlineParts = taskString.split(" \\(by: ");
                    String deadlineText = deadlineParts[0];
                    String deadlineDetails = "(by: " + deadlineParts[1];
                    tasks.addTask(new Deadline(deadlineText, deadlineDetails, isCompleted), false);
                    break;
                case "EVENT":
                    String[] eventParts = taskString.split(" \\(at: ");
                    String eventText = eventParts[0];
                    String eventDetails = "(at: " + eventParts[1];
                    tasks.addTask(new Event(eventText, eventDetails, isCompleted), false);
                    break;
            }
        } catch (DukeException e) {
            System.out.println("\t ☹ OOPS!!! " + e.getMessage());
        }

    }
}
