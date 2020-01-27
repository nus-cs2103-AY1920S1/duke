package seedu.duke.Storage;

import seedu.duke.TaskList.TaskList;
import seedu.duke.TaskList.Task;
import seedu.duke.TaskList.ToDos;
import seedu.duke.TaskList.Deadlines;
import seedu.duke.TaskList.Events;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;

public class Storage {

    public final String FILEPATH;

    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * store text into a file
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter((filePath));
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * append the text in a file without over-writing the content inside
     * @param filePath
     * @param textToAppend
     * @throws IOException
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * save the tasks into the file
     * @param taskList
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException {
        if(taskList.list.size() != 0) {
            writeToFile(FILEPATH, taskList.list.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < taskList.list.size(); i++) {
                appendToFile(FILEPATH, taskList.list.get(i).toString() + System.lineSeparator());
            }
        }
    }

    /**
     * to load the list from the file
     * @return
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>(100);
        Path path = Paths.get(FILEPATH);
        Scanner scanner = new Scanner(path);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = getTask(line).get();
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * to get the task from a string of input
     * @param line
     * @return </Task>
     */
    private Optional<Task> getTask(String line) {
        try {
            List<String> data = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
            Task task;
            switch (data.get(0)) {
                case "T":
                    task = new ToDos(data.get(2));
                    task.setTaskType("T");
                    break;
                case "D":
                    task = new Deadlines(data.get(2));
                    task.setTaskType("D");
                    task.setTime(data.get(3));
                    break;
                case "E":
                    task = new Events(data.get(2));
                    task.setTaskType("E");
                    task.setTime(data.get(3));
                    break;
                default:
                    throw new ParseException(data.get(0) + " is not an acceptable argument", 0);
            }
            if (data.get(1).equals(1)) {
                task.markAsDone();
            }
            return Optional.of(task);
        } catch (ParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    }

