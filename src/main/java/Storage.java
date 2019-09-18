import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
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
    private String filePath;

    /**
     * Creates the storage with the file of tasks.
     *
     * @param filePath The path to the file of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task from the file into an array list.
     *
     * @return The list of tasks to be loaded from the file.
     * @throws FileNotFoundException If file is not found.
     * @throws ParseException If a parse exception occurred.
     */
    public ArrayList<Task> load() throws FileNotFoundException, ParseException {
        ArrayList<Task> list = new ArrayList<Task>();
        File file = new File(filePath);
        Scanner fs = new Scanner(file);
        while (fs.hasNext()) {
            String line = fs.nextLine();
            assert (line.length() > 0);
            String[] taskArr = line.split(" \\| ");
            String type = taskArr[0];
            if (type.equals("T")) {
                assert (type.equals("T"));
                Todo todo = new Todo(taskArr[2]);
                if (taskArr[1].equals("1")) {
                    todo.markAsDone();
                }
                list.add(todo);
            } else if (type.equals("D")) {
                assert (type.equals("D"));
                String date = taskArr[3].substring(8, 10) + " "
                        + taskArr[3].substring(4, 7) + " "
                        + taskArr[3].substring(24, 28) + " "
                        + taskArr[3].substring(11, 16);
                Deadline deadline = new Deadline(taskArr[2],
                        convertToDate(date));
                if (taskArr[1].equals("1")) {
                    assert (taskArr[1].equals("1"));
                    deadline.markAsDone();
                }
                list.add(deadline);
            } else if (type.equals("E")) {
                assert (type.equals("E"));
                String date = taskArr[3].substring(8, 10) + " "
                        + taskArr[3].substring(4, 7) + " "
                        + taskArr[3].substring(24, 28) + " "
                        + taskArr[3].substring(11, 16);
                Event event = new Event(taskArr[2], convertToDate(date));
                if (taskArr[1].equals("1")) {
                    assert (taskArr[1].equals("1"));
                    event.markAsDone();
                }
                list.add(event);
            }
        }
        fs.close();
        return list;
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
        FileWriter fw = new FileWriter(filePath, true);
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
            assert (type.equals("T"));
            textToAdd.append("T | ");
        } else if (type.equals("D")) {
            assert (type.equals("D"));
            textToAdd.append("D | ");
        } else if (type.equals("E")) {
            assert (type.equals("E"));
            textToAdd.append("E | ");
        }
        textToAdd.append(task.getStatusNum());
        textToAdd.append(" | ");
        textToAdd.append(task.getDescription());
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
        FileWriter fw = new FileWriter(filePath);
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