package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class defines the behaviour of a storage.
 * 
 * @author Joel Loong
 */
public class Storage {

    private static final String FILE_PATH = "src/main/java/duke/data/duke.txt";
    private final File file;

    public Storage() {
        this.file = createFile();
    }

    private File createFile() {
        File file = new File(Storage.FILE_PATH);

        if (!file.exists()) {
            file = new File("duke.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file;
    }

    /**
     * Reads data from data file.
     * 
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     * @throws FileNotFoundException Occurs when path of file is invalid.
     */
    public TaskList readData() throws FileNotFoundException {
        assert this.file != null : "File is null";
        Scanner sc = new Scanner(this.file);
        ArrayList<Task> task = new ArrayList<>();

        while (sc.hasNext()) {
            String taskLine = sc.nextLine();
            String[] taskLineSplit = taskLine.split(":");
            String description = taskLineSplit[2];
            int isDone = Integer.parseInt(taskLineSplit[1]);
            switch (taskLineSplit[0]) {
            case "T":
                int priorityTodo = Integer.parseInt(taskLineSplit[3]);
                task.add(new Todo(description, isDone, priorityTodo));
                break;
            case "E":
                String at = taskLineSplit[3];
                int priorityEvent = Integer.parseInt(taskLineSplit[4]);
                task.add(new Event(description, at, isDone, priorityEvent));
                break;
            case "D":
                String by = taskLineSplit[3];
                int priorityDeadline = Integer.parseInt(taskLineSplit[4]);
                task.add(new Deadline(description, by, isDone, priorityDeadline));
                break;
            default:
            }
        }

        // Close the scanner
        sc.close();

        return new TaskList(task);
    }

    /**
     * Writes data from taskList into data file.
     * 
     * @throws IOException Occurs when path of file is invalid.
     */
    public void writeData() throws IOException {
        ArrayList<Task> task = TaskList.getTasks();
        FileWriter fw = new FileWriter(this.file);
        assert fw != null : "FileWriter is null";
        String stringToWrite = "";
        int counter = task.size();

        for (int i = 0; i < counter; i++) {
            Task taskToWrite = task.get(i);
            int priority = taskToWrite.getPriority().getPriorityLevel();
            if (taskToWrite instanceof Todo) {
                stringToWrite += "T:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription();
                stringToWrite += ":" + priority;
            } else if (taskToWrite instanceof Event) {
                stringToWrite += "E:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription() + ":";
                Event e = (Event) taskToWrite;
                stringToWrite += e.getTime();
                stringToWrite += ":" + priority;
            } else if (taskToWrite instanceof Deadline) {
                stringToWrite += "D:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription() + ":";
                Deadline d = (Deadline) taskToWrite;
                stringToWrite += d.getTime();
                stringToWrite += ":" + priority;
            }

            if (i != counter - 1) {
                stringToWrite += System.lineSeparator();
            }
        }

        fw.write(stringToWrite);
        fw.close();
    }

    private String writeStatus(Task t) {
        String statusIcon = t.getStatusIcon();
        if (statusIcon.equals("O")) {
            return "1:";
        } else {
            return "0:";
        }
    }
}