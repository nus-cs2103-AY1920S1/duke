import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Michelle Yong
 */
public class Storage {
    private final String DIRECTORY_PATH = System.getProperty("user.dir") + "/data";
    private final String FILE_PATH = this.DIRECTORY_PATH + "/mytasks.txt";
    private File directory = new File(DIRECTORY_PATH);
    private File file = new File(FILE_PATH);
    /**
     * Creates the storage with the file of tasks.
     */
    public Storage() {}

    /**
     * Loads the task from the file into an array list.
     * Creates the file if it does not exists.
     *
     * @return The list of tasks to be loaded from the file.
     * @throws IOException If an input or output exception occurred.
     * @throws ParseException If a parse exception occurred.
     */
    public ArrayList<Task> load() throws ParseException, IOException {
        ArrayList<Task> list = new ArrayList<>();
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        Scanner fs = new Scanner(file);
        while (fs.hasNext()) {
            String line = fs.nextLine();
            assert (line.length() > 0);
            String[] taskArr = line.split(" \\| ");
            String type = taskArr[0];
            if (type.equals("T")) {
                loadTodoToList(taskArr, list);
            } else if (type.equals("D")) {
                loadDeadlineToList(taskArr, list);
            } else if (type.equals("E")) {
                loadEventToList(taskArr, list);
            }
        }
        fs.close();
        return list;
    }

    /**
     * Loads the todo to the list.
     *
     * @param taskArr The array with the command details.
     * @param list The list where the todo is to be added to.
     */
    public void loadTodoToList(String[] taskArr, ArrayList<Task> list) {
        Todo todo = new Todo(taskArr[2]);
        updateDone(todo, taskArr);
        updatePriority(todo, taskArr);
        list.add(todo);
    }


    /**
     * Loads the deadline to the list.
     *
     * @param taskArr The array with the command details.
     * @param list The list where the deadline is to be added to.
     * @throws ParseException If a parse exception occurred.
     */
    public void loadDeadlineToList(String[] taskArr, ArrayList<Task> list)
            throws ParseException {
        Deadline deadline = new Deadline(taskArr[2], getDate(taskArr));
        updateDone(deadline, taskArr);
        updatePriority(deadline, taskArr);
        list.add(deadline);
    }

    /**
     * Loads the event to the list.
     *
     * @param taskArr The array with the command details.
     * @param list The list where the event is to be added to.
     * @throws ParseException If a parse exception occurred.
     */
    public void loadEventToList(String[] taskArr, ArrayList<Task> list)
            throws ParseException {
        Event event = new Event(taskArr[2], getDate(taskArr));
        updateDone(event, taskArr);
        updatePriority(event, taskArr);
        list.add(event);
    }

    /**
     * Gets the date from the command details.
     *
     * @param taskArr The array with the command details.
     * @return The date of the task.
     * @throws ParseException If a parse exception occurred.
     */
    public Date getDate(String[] taskArr) throws ParseException {
        String date = taskArr[4].substring(8, 10) + " "
                + taskArr[4].substring(4, 7) + " "
                + taskArr[4].substring(24, 28) + " "
                + taskArr[4].substring(11, 16);
        return convertToDate(date);
    }

    /**
     * Updates the priority of the task.
     *
     * @param task The task which priority is to be updated.
     * @param taskArr The array with the command details.
     */
    public void updatePriority(Task task, String[] taskArr) {
        String priority = taskArr[3];
        task.setPriority(priority);
    }

    /**
     * Updates the task to be done.
     *
     * @param task The task to be marked done.
     * @param taskArr The array with the command details.
     */
    public void updateDone(Task task, String[] taskArr) {
        if (taskArr[1].equals("1")) {
            assert (taskArr[1].equals("1"));
            task.markAsDone();
        }
    }

    /**
     * Converts the string to a date.
     *
     * @param str The date in string format.
     * @return The date.
     * @throws ParseException If a parse exception occurred.
     */
    public Date convertToDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm");
        assert (str.length() == 17) : "Date should be in this format: \"dd MMM yyyy hh:mm\".";
        Date date = sdf.parse(str);
        return date;
    }

    /**
     * Loads the task from the file into an array list.
     *
     * @param textToAppend The text to be appended to the file.
     * @return The list of tasks to be loaded from the file.
     * @throws IOException If an input or output exception occurred.
     */
    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Converts the task to string format to be added to the file.
     *
     * @param task The task to be converted in string format.
     * @return The task in a string format.
     */
    private String convertTaskToFileFormat(Task task) {
        StringBuffer textToAdd = new StringBuffer();
        String type = task.getType();
        if (type.equals("T")) {
            textToAdd.append("T | ");
        } else if (type.equals("D")) {
            textToAdd.append("D | ");
        } else if (type.equals("E")) {
            textToAdd.append("E | ");
        }
        textToAdd.append(task.getStatusNum());
        textToAdd.append(" | ");
        textToAdd.append(task.getDescription());
        textToAdd.append(" | ");
        textToAdd.append(task.getPriority());
        if (type.equals("D") || type.equals("E")) {
            assert (type.equals("D") || type.equals("E"));
            textToAdd.append(" | ");
            textToAdd.append(task.getDate());
        }
        textToAdd.append("\n");
        return textToAdd.toString();
    }

    /**
     * Appends task to the file.
     *
     * @param task The task to be appended to the file.
     * @throws IOException If an input or output exception occurred.
     */
    public void appendTaskToFile(Task task) throws IOException {
        String textToAppend = convertTaskToFileFormat(task);
        appendToFile(textToAppend);
    }

    /**
     * Writes the current task to the file.
     *
     * @param textToAdd The task to be written to the file.
     * @throws IOException If an input or output exception occurred.
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates the current list of tasks to the file.
     *
     * @param list The list of tasks to be updated to the file.
     * @throws IOException If an input or output exception occurred.
     */
    public void updateTaskInFile(ArrayList<Task> list) throws IOException {
        StringBuffer textToAdd = new StringBuffer();
        for (Task task : list) {
            textToAdd.append(convertTaskToFileFormat(task));
        }
        writeToFile(textToAdd.toString());
    }
}