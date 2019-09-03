package Storage;

import TaskList.TaskList;
import TaskList.Task;
import TaskList.ToDos;
import TaskList.Deadlines;
import TaskList.Events;

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

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter((filePath));
        fw.write(textToAdd);
        fw.close();
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void save(TaskList taskList) throws IOException {
        if(taskList.list.size() != 0) {
            writeToFile(FILEPATH, taskList.list.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < taskList.list.size(); i++) {
                appendToFile(FILEPATH, taskList.list.get(i).toString() + System.lineSeparator());
            }
        }
    }

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

