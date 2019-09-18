import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected String filePath;

    /**
     * Storage constructor that handles the saving, loading and updating of contents in the saved file.
     * @param filePath filepath of the stored data file
     * @throws DukeException when unable to create a save file
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        try {
            File dataDir = new File("data");
            dataDir.mkdir();
            new File(filePath).createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to create a new save file");
        }
    }

    /**
     * Converts lines of Strings in saved text file into tasks which are loaded into an ArrayList.
     * @return an ArrayList containing all the tasks in the saved file.
     * @throws DukeException if there is an error in loading the file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        File file = new File(filePath);
        Scanner s;

        try {
            s = new Scanner(file);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                Task task = evaluateLineAndGetTask(taskString);
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading Error");
        }
        return list;
    }

    /**
     * Updates the task that is completed in the saved file.
     * @param taskNum the line number of the task to be updated (marked as done)
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void updateTaskInFile(int taskNum) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;
        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum == taskNum) {
                s = s.substring(0, 4) + "1" + s.substring(5);
            }
            list.add(s);
            lineNum++;
        }
        writeBackToFile(list, filePath);
    }

    /**
     * Adds the task into the end of the text file.
     * @param textToAdd a line of string which can be saved in an understandable way
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void addToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        File file = new File(filePath);
        Scanner scn = new Scanner(file);
        if (scn.hasNext()) {
            fw.write(System.lineSeparator());
        }
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Deletes the task based on its line number in the text file.
     * @param taskNum the line number of the task to be deleted
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void deleteFromFile(int taskNum) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;

        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum != taskNum) {
                list.add(s);
            }
            lineNum++;
        }

        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i) + System.lineSeparator());
        }

        if (list.size() > 0) {
            fw.write(list.get(list.size() - 1));
        }

        fw.close();
    }

    //evaluates a line in the saved file to determine whether it is a todo, deadline or event,
    //and creates the respective task object to be returned so that it can be saved in the list
    private Task evaluateLineAndGetTask(String line) {
        String taskType = line.substring(0, 1);
        String isDone = line.substring(4, 5);
        if (taskType.equals("T")) {
            String desc = line.substring(8);
            ToDo task = new ToDo(desc);
            if (isDone.equals("1")) {
                task.markAsDone();
            }
            return task;
        } else {
            int i = line.lastIndexOf("|");
            String desc = line.substring(8, i - 1);
            String time = line.substring(i + 2, i + 12) + line.substring(i + 13);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(time, format);
            if (taskType.equals("D")) {
                Deadline task = new Deadline(desc, dateTime);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                return task;
            } else {
                Event task = new Event(desc, dateTime);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                return task;
            }
        }
    }

    /**
     * Edits the task in the saved file.
     * @param taskNum the line number of the task to be edited
     * @throws IOException if stream to file cannot be written to or closed.
     */
    public void editTaskInFile(int taskNum, String newText) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;
        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum == taskNum) {
                s = newText;
            }
            list.add(s);
            lineNum++;
        }
        writeBackToFile(list, filePath);
    }

    private void writeBackToFile(List<String> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        //write all but the last task to file to avoid adding extra line separator
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i) + System.lineSeparator());
        }
        //write last task to file
        fw.write(list.get(list.size() - 1));
        fw.close();
    }
}
