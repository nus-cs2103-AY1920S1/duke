import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /**
     * Enumerate task type options.
     */
    private static final String ADD_TYPE_TODO = "todo";
    private static final String ADD_TYPE_DEADLINE = "deadline";
    private static final String ADD_TYPE_EVENT = "event";

    private PrintWriter writer;
    /**
     * Directory location for file storage and retrieval.
     */
    private String filename;

    /**
     * Constructor for new Storage object with directory information loaded.
     * @param filename Location for loaded/new file
     */
    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * Loads tasks from file into an array of tasks to be passed into TaskList.
     * @return ArrayList of tasks loaded from file
     * @throws FileNotFoundException No file is found in the file directory indicated, loading failed.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasklist = new ArrayList<>();
        assert tasklist.size() == 0 : "Initialised ArrayList is not empty.";
        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            scanner.next();
            if (!scanner.hasNext()) {
                break;
            }
            String[] inputArr = scanner.nextLine().trim().split(" ", 3);
            char taskType = inputArr[0].charAt(1);
            boolean isTaskComplete = (inputArr[1].equals("[o]")) ? true : false;
            if (taskType == 'T') {
                tasklist.add(new Task(inputArr[2], ADD_TYPE_TODO, isTaskComplete));
            } else if (taskType == 'D') {
                String deadlineDetails = inputArr[2].split("\\(")[0];
                String deadline = inputArr[2].split("\\(")[1].split(" ", 2)[1];
                assert deadline.split("/")[0].length() == 2 : "Day is incorrect";
                assert deadline.split("/")[1].length() == 2 : "Month is incorrect";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm)");
                LocalDateTime deadlineByDateTime = LocalDateTime.parse(deadline, formatter);
                tasklist.add(new Task(deadlineDetails, ADD_TYPE_DEADLINE, deadlineByDateTime, isTaskComplete));
            } else {
                String eventDetails = inputArr[2].split("\\(")[0];
                String eventDateTime = inputArr[2].split("\\(")[1].split(" ", 2)[1];
                assert eventDateTime.split("/")[0].length() == 2 : "Day is incorrect";
                assert eventDateTime.split("/")[1].length() == 2 : "Month is incorrect";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm)");
                LocalDateTime eventDateTimeByDateTime = LocalDateTime.parse(eventDateTime, formatter);
                tasklist.add(new Task(eventDetails, ADD_TYPE_EVENT, eventDateTimeByDateTime, isTaskComplete));
            }
        }
        return tasklist;
    }

    /**
     * Update the loaded file based on changes to the todo file.
     * @param todoString String of tasks based on TaskList
     * @throws FileNotFoundException File directory cannot be located
     * @throws UnsupportedEncodingException File type is not supported
     */
    public void updateTodoFile(String todoString) throws IOException {
        try {
            writer = new PrintWriter(filename, "UTF-8");
            writer.printf(todoString);
            writer.close();
        } catch (FileNotFoundException err) {
            File f = new File(filename);
            f.getParentFile().mkdir();
            f.createNewFile();
            updateTodoFile(todoString);
        }
    }
}
