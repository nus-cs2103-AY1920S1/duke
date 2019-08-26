/*
This method deals with loading tasks from the file and saving tasks in the file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static String filePath;
    static ArrayList<Task> memory = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
    This method is called when Duke starts in order to load previously saved information on the hard disk.
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

    /*
    This method converts the Task in the form it was saved into the hard drive to a Task object.
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

    /*
    This method generates the TD task that was loaded from the hard drive.
     */
    private static ToDo remakeTodo(String[] info) {
        ToDo ret = new ToDo(info[2].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /*
    This method generates the Deadline task that was loaded from the hard drive.
     */
    private static Deadline remakeDeadline(String[] info) {
        Deadline ret = new Deadline(info[2].trim(), info[3].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /*
    This method generates the Event task that was loaded from the hard drive.
     */
    private static Event remakeEvent(String[] info) {
        Event ret = new Event(info[2].trim(), info[3].trim());
        if (info[1].equals(" 1 ")) {
            ret.recordDone();
        }
        return ret;
    }

    /*
    This method is called when Duke automatically saves the updated list into the hard drive. If there is nothing in the memory, an empty string is added into the hard disk.
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

    /*
    This method overwrites the information from Duke to the hard disk. This is done to automatically update all tasks in the hard disk.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
