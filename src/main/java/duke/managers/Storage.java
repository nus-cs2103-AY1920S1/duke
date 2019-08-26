/**
 * This method deals with loading tasks from the file and saving tasks in the file. It contains methods that process
 * the information to and from the hard disk to the existing memory of the Duke chat bot.
 */
package duke.managers;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.ToDo;
import duke.tasks.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static ArrayList<Task> memory = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method is called when Duke starts in order to load previously saved information on the hard disk.
     * @exception DukeException is thrown when there is an error with the location where the information was stored
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNextLine()) {
                Task newTask = loadStringToTask(s.nextLine());
                memory.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found. A new TaskList will be loaded.");
        } finally {
            return memory;
        }
    }

    /**
     * This method converts the Task in the form it was saved into the hard drive to a Task object.
     * @param taskString a String read from the hard disk to be processed into a task
     */
    private static Task loadStringToTask(String taskString) {
        String[] allInfo = taskString.split("[|]", 4);
        Task toReturn;
        if (taskString.charAt(0) == 'T') {
            toReturn = remakeTodo(allInfo);
        } else if (taskString.charAt(0) == 'D') {
            allInfo = taskString.split("[|]", 5);
            toReturn = remakeDeadline(allInfo);
        } else {
            allInfo = taskString.split("[|]", 7);
            toReturn = remakeEvent(allInfo);
        }
        return toReturn;
    }

    /**
     * This method generates the TD task that was loaded from the hard drive.
     * @param info a String[] containing information to remake the task
     */
    private static ToDo remakeTodo(String[] info) {
        ToDo ret = new ToDo(info[2].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /**
     * This method generates the Deadline task that was loaded from the hard drive.
     * @param info a String[] containing information to remake the task
     */
    private static Deadline remakeDeadline(String[] info) {
        Deadline ret = new Deadline(info[2].trim(), info[3].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /**
     * This method generates the Event task that was loaded from the hard drive.
     * @param info a String[] containing information to remake the task
     */
    private static Event remakeEvent(String[] info) {
        Event ret = new Event(info[2].trim(), info[3].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /**
     * This method is called when Duke automatically saves the updated list into the hard drive. If there is nothing in
     * the memory, an empty string is added into the hard disk.
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public static void save() throws IOException {
        if (memory.size() > 0) {
            String text = "";
            Task firstTask = memory.get(0);
            if (firstTask instanceof ToDo) {
                ToDo first = (ToDo) firstTask;
                text = first.format();
            } else if (firstTask instanceof Deadline) {
                Deadline first = (Deadline) firstTask;
                text = first.format();
            } else {
                Event first = (Event) firstTask;
                text = first.format();
            }
            int numTasks = memory.size();
            for (int i = 1; i < numTasks; i++) {
                String text2 = "";
                Task specTask = memory.get(i);
                if (specTask instanceof ToDo) {
                    ToDo spec = (ToDo) specTask;
                    text2 = spec.format();
                } else if (specTask instanceof Deadline) {
                    Deadline spec = (Deadline) specTask;
                    text2 = spec.format();
                } else {
                    Event spec = (Event) specTask;
                    text2 = spec.format();
                }
                text += System.lineSeparator() + text2;
            }
            writeToFile(filePath, text);
        } else {
            writeToFile(filePath, "");
        }
    }

    /**
     * This method overwrites the information from Duke to the hard disk. This is done to automatically update all
     * tasks in the hard disk.
     * @param filePath a String containing the location of the saved file
     * @param textToAdd a String containing all information processed from the memory stored in Duke
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
