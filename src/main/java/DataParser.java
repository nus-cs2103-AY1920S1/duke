import java.util.Scanner;

public class DataParser {

    private Scanner sc;
    private String input;

    public DataParser() {
        this.sc = new Scanner(System.in);
        this.input = "";
    }

    public boolean hasAnymoreData() {
        return sc.hasNextLine();
    }

    public void readInput() {
        this.input = sc.nextLine();
    }

    public Command findCommand() throws DukeException {
        if (shouldEndParsing()) {
            return new EndCommand();
        } else if (shouldListTasks()) {
            return new ListTaskCommand();
        } else if (shouldCompleteTask()) {
            return new CompleteTaskCommand();
        } else if (shouldDeleteTask()) {
            return new DeleteTaskCommand();
        } else if (shouldAddToDoTask()) {
            return new AddToDoTaskCommand();
        } else if (shouldAddDeadlineTask()) {
            return new AddDeadlineTaskCommand();
        } else if (shouldAddEventTask()) {
            return new AddEventTaskCommand();
        } else {
            throw new UnknownCommandException();
        }
    }

    public int getTaskIndex() throws InvalidTaskIndexException {
        String[] parsedData = input.split(" ");
        if (parsedData.length < 2) {
            throw new InvalidTaskIndexException();
        } else {
            String index = parsedData[1];
            int taskIndex = Integer.parseInt(index) - 1;
            return taskIndex;
        }
    }

    public boolean isEmptyTask(String data) {
        return data.equals("");
    }

    public boolean isDeadlineTaskValid(String data) {
        return data.contains("/by");
    }

    public boolean isEventTaskValid(String data) {
        return data.contains("/at");
    }

    public String parseToDoData() throws InvalidToDoException {
        String data = this.input.substring(4);
        if (isEmptyTask(data)) {
            throw new InvalidToDoException();
        }

        return data.trim();
    }

    public String[] parseDeadlineData() throws InvalidDeadlineException {
        String data = this.input.substring(8).trim();
        if (isEmptyTask(data)) {
            throw new InvalidDeadlineException("The description of a deadline cannot be empty.");
        } else if (!isDeadlineTaskValid(data)) {
            throw new InvalidDeadlineException("Please include the time of a deadline.");
        }

        String[] taskData = data.split(" /by ");
        if (taskData.length == 1) {
            throw new InvalidDeadlineException("The time of a deadline cannot be empty.");
        }

        return taskData;
    }

    public String[] parseEventDate() throws InvalidEventException {
        String data = this.input.substring(5).trim();
        if (isEmptyTask(data)) {
            throw new InvalidEventException("The description of an event cannot be empty.");
        } else if (!isEventTaskValid(data)) {
            throw new InvalidEventException("Please include the time of an event.");
        }

        String[] taskData = data.split(" /at ");
        if (taskData.length == 1) {
            throw new InvalidEventException("The time of an event cannot be empty.");
        }

        return taskData;
    }

    public boolean shouldEndParsing() {
        return input.equals("bye");
    }

    public boolean shouldListTasks() {
        return input.equals("list");
    }

    public boolean shouldCompleteTask() {
        return input.startsWith("done");
    }

    public boolean shouldDeleteTask() {
        return input.startsWith("delete");
    }

    public boolean shouldAddToDoTask() {
        return input.startsWith("todo");
    }

    public boolean shouldAddDeadlineTask() {
        return input.startsWith("deadline");
    }

    public boolean shouldAddEventTask() {
        return input.startsWith("event");
    }

}
