package duke.storage;

import duke.exception.DukeException;
import duke.shared.GetArgumentsUtil;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads and save data from text file.
 */
public class Storage {

    private String filePath;
    private List<Task> taskList;

    public static final String DATETIME_PATTERN = "dd MMMM yyyy, hh:mm a";

    public static final int DONE_INDEX = 2;
    public static final int TASK_INDEX = 0;
    public static final int START_ARGUMENTS_INDEX = 4;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and loads the data from the text file.
     * @return list of past tasks saved in the text file
     * @throws IOException throws by {@link #readFile()}
     * @throws DukeException throws by {@link #eventParser(String[])} and {@link #deadlineParser(String[])}
     */
    public List<Task> load() throws IOException, DukeException {
        taskList = new ArrayList<>();
        readFile();
        return taskList;
    }

    /**
     * Reads and decode the text in the text file line-by-line.
     * @throws IOException file is not found / unable to read the file
     * @throws DukeException throws by {@link #eventParser(String[])} and {@link #deadlineParser(String[])}
     */
    public void readFile() throws IOException, DukeException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            while ((line = br.readLine()) != null) {
                decodeLine(line);
            }
        } finally {
            br.close();
        }
    }

    /**
     * Decode the line and adds to the tasklist.
     * @param line line read from the text file
     * @throws DukeException throws by {@link #eventParser(String[])} and {@link #deadlineParser(String[])}
     */
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

    /**
     * Convert the decoded line to ToDo object.
     * @param inputLines String array of input line
     * @return ToDo task after decoding
     */
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

    /**
     * Convert the decoded line to Event object.
     * @param inputLines String array of input line
     * @return Event task after decoding
     * @throws DukeException if {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])} unable to retrieve the descriptions
     */
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

    /**
     * Convert the decoded line to Deadline Object.
     * @param inputLines String array of input line
     * @return Deadline task after decoding
     * @throws DukeException if {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])} unable to retrieve the descriptions
     */
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

    /**
     * Formats the tasklist and save into the defined text file.
     * @param taskList latest list of tasks
     * @throws IOException throws by {@link #writeToFile(String)}
     */
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

    /**
     * Writes data to text file.
     * @param linesToWrite string to be written to the text file
     * @throws IOException if the file is not found
     */
    public void writeToFile(String linesToWrite) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.append(linesToWrite);
        bufferedWriter.close();
    }
}
