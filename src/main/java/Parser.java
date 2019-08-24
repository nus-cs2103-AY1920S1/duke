import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public static final String FILEPATH = "data/duke.txt";
    private List<Task> taskList;

    public static final String DATETIME_PATTERN = "dd MMMM yyyy, hh:mm a";

    public static final int DONE_INDEX = 2;
    public static final int TASK_INDEX = 0;
    public static final int START_ARGUMENTS_INDEX = 4;

    public List<Task> setup() throws FileNotFoundException, IOException, DukeException {
        taskList = new ArrayList<>();
        readFile();
        return taskList;
    }

    public void readFile() throws FileNotFoundException, IOException, DukeException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
        try {
            while ((line = br.readLine()) != null) {
                decodeLine(line);
            }
        } finally {
            br.close();
        }
    }

    public void decodeLine(String line) throws DukeException {
        String[] inputLines = line.split("\\s+");
        Task task = null;
        if (inputLines[TASK_INDEX].equals("T")) {
            task = toDoParser(inputLines);
        } else if (inputLines[TASK_INDEX].equals("E")) {
            task = eventParser(inputLines);
        } else if (inputLines[TASK_INDEX].equals("D")) {
            task = deadlineParser(inputLines);
        }
        taskList.add(task);
    }

    public Task toDoParser(String[] inputLines) {
        Task toDoTask = null;
        String taskName = String.join(" ", Arrays.copyOfRange(inputLines, START_ARGUMENTS_INDEX,
                inputLines.length));
        if (inputLines[DONE_INDEX].equals("1")) {
            toDoTask = new Todo(taskName, true);
        } else if (inputLines[DONE_INDEX].equals("0")) {
            toDoTask = new Todo(taskName, false);
        }
        return toDoTask;
    }

    public Task eventParser(String[] inputLines) throws DukeException {
        Task eventTask = null;
        String[] args = GetArgumentsUtil.getTwoCommandArgs(0,"|",
                Arrays.copyOfRange(inputLines, START_ARGUMENTS_INDEX, inputLines.length));
        if (inputLines[DONE_INDEX].equals("1")) {
            eventTask = new Event(args[0], args[1], true);
        } else if (inputLines[DONE_INDEX].equals("0")) {
            eventTask = new Event(args[0], args[1], false);
        }
        return eventTask;
    }

    public Task deadlineParser(String[] inputLines) throws DukeException {
        Task deadlineTask = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        String[] args = GetArgumentsUtil.getTwoCommandArgs(0, "|",
                Arrays.copyOfRange(inputLines, START_ARGUMENTS_INDEX, inputLines.length));
        LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
        if (inputLines[DONE_INDEX].equals("1")) {
            deadlineTask = new Deadline(args[0], dateTime, true);
        } else if (inputLines[DONE_INDEX].equals("0")) {
            deadlineTask = new Deadline(args[0], dateTime, false);
        }
        return deadlineTask;
    }

    public void saveData(List<Task> taskList) throws IOException {
        StringBuilder myStringBuilder = new StringBuilder();
        for (Task task : taskList) {
            if (task instanceof Todo) {
                myStringBuilder.append(task.getShortForm()).append(" | ").append(task.getStatus() ? 1 : 0).append(" | ")
                        .append(task.getName()).append("\n");
            } else if (task instanceof Event) {
                myStringBuilder.append(task.getShortForm()).append(" | ").append(task.getStatus() ? 1 : 0).append(" | ")
                        .append(task.getName()).append(" | ").append(((Event) task).getTiming()).append("\n");
            } else if (task instanceof Deadline) {
                myStringBuilder.append(task.getShortForm()).append(" | ").append(task.getStatus() ? 1 : 0).append(" | ")
                        .append(task.getName()).append(" | ").append(((Deadline) task).getFormattedDateTime()).append("\n");
            }
        }
        myStringBuilder.deleteCharAt(myStringBuilder.length() - 1);
        writeToFile(myStringBuilder.toString());
    }

    public void writeToFile(String linesToWrite) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILEPATH));
        bufferedWriter.append(linesToWrite);
        bufferedWriter.close();
    }
}
