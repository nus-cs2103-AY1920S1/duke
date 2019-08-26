import java.text.ParseException;
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
                    try {
                        tasks.addTask(new Deadline(deadlineText, deadlineParts[1], isCompleted), false);
                    } catch (ParseException e) {
                        System.out.println(e.getMessage() + ". Please make sure date from file is in this format DD/MM/YYYY HHMM");
                    }
                    break;
                case "EVENT":
                    String[] eventParts = taskString.split(" \\(at: ");
                    String eventText = eventParts[0];
                    try {
                        tasks.addTask(new Event(eventText, eventParts[1], isCompleted), false);
                    } catch (ParseException e) {
                        System.out.println(e.getMessage() + ". Please enter date from file in this format DD/MM/YYYY HHMM - DD/MM/YYYY HHMM");
                    }

                    break;
            }
        } catch (DukeException e) {
            System.out.println("\t ☹ OOPS!!! " + e.getMessage());
        }

    }
}
