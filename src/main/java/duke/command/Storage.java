package duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import duke.command.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

public class Storage {

    private File file;
    TaskList taskList;
    ArrayList<Task> list;
    HashSet<Task> set = new HashSet<Task>();

    /**
     * Constructs a storage object that takes in the file path as parameter.
     * @param filePath The path at which the file is stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.list = new ArrayList<Task>();
    }

    /**
     * Loads the content in the file into the ArrayList of Task.
     * @return The ArrayList of type Task which has been filled with the tasks from
     *     the content of the file.
     * @throws DukeException if file does not exist in the fiven file path.
     * @throws FileNotFoundException if no file can be found in the given file path.
     * @throws ParseException if format of the file does not fit the expected format.
     * @throws IOException if file path cannot be found.
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException, ParseException, IOException {
        if (this.file.exists()) {
            Scanner scannerTask = new Scanner(this.file);
            while (scannerTask.hasNext()) {
                String taskText = scannerTask.nextLine();
                parseTextToTask(taskText, this.list);
            }
            return this.list;
        } else {
            file.createNewFile();
            throw (new DukeException("File not found"));
        }
    }

    /**
     * Set the field of the ArrayList of task to the list of task from TaskList.
     * @param taskList The TaskList from which the ArrayList of task is to be assigned
     *                 to the class's ArrayList field.
     */
    public void setList(TaskList taskList) {
        this.list = taskList.getList();
    }

    /**
     * Parse the Text from the File to the corresponding Task objects.
     * @param taskText the Line from the text to be parsed into Task object.
     * @param list The list to which the resulting parsed text wish to be added.
     * @throws ParseException if format of the text does not fit the expected format.
     */
    public void parseTextToTask(String taskText, ArrayList<Task> list) throws ParseException {
        if (taskText.substring(0, 1).equals("T")) {
            parseTextToTaskTodo(taskText, list);
        } else if (taskText.substring(0, 1).equals("D")) {
            parseTextToTaskDeadline(taskText, list);
        } else if (taskText.substring(0, 1).equals("E")) {
            parseTextToTaskEvent(taskText, list);
        }
    }

    /**
     * Parse the Text from the File to the Deadline Task objects and add it to the list of Task.
     * @param taskText the Line from the text to be parsed into Task object.
     * @param list The list to which the resulting parsed text wish to be added.
     * @throws ParseException if format of the text does not fit the expected format.
     */
    public void parseTextToTaskDeadline(String taskText, ArrayList<Task> list) throws ParseException {
        String descriptionAndTime = taskText.substring(8);
        int index = descriptionAndTime.indexOf('|');
        String description = descriptionAndTime.substring(0, index - 1);
        String time = descriptionAndTime.substring(index + 2);
        Task task = new Deadline(description, time);
        if (taskText.substring(4,5).equals("1")) {
            task.markAsDone();
        }
        list.add(task);
        this.set.add(task);
    }

    /**
     * Parse the Text from the File to the Todo Task objects and add it to the list of Task.
     * @param taskText the Line from the text to be parsed into Task object.
     * @param list The list to which the resulting parsed text wish to be added.
     */
    public void parseTextToTaskTodo(String taskText, ArrayList<Task> list) {
        Task task = new Todo(taskText.substring(8));
        if (taskText.substring(4,5).equals("1")) {
            task.markAsDone();
        }
        list.add(task);
        this.set.add(task);
    }

    /**
     * Parse the Text from the File to the Event Task objects and add it to the list of Task.
     * @param taskText the Line from the text to be parsed into Task object.
     * @param list The list to which the resulting parsed text wish to be added.
     * @throws ParseException if format of the text does not fit the expected format.
     */
    public void parseTextToTaskEvent(String taskText, ArrayList<Task> list) throws ParseException {
        String descriptionAndTime = taskText.substring(8);
        int index = descriptionAndTime.indexOf('|');
        String description = descriptionAndTime.substring(0, index - 1);
        String time = descriptionAndTime.substring(index + 2);
        Task task = new Event(description, time);
        if (taskText.substring(4,5).equals("1")) {
            task.markAsDone();
        }
        list.add(task);
        this.set.add(task);
    }

    /**
     * Appending newly added task into the existing file.
     * @param task The task that wish to be added ot the file for update.
     * @throws IOException if file cannot be found when passed as an argument in
     *     the FileWriter constructor.
     */
    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.file, true);
        String isDone = task.isDone() ? "1" : "0";
        String text = task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
        if (task.getTypeOfTask().equals("D")) {
            text = text + " | " + ((Deadline)task).getTime() + System.lineSeparator();
        } else if (task.getTypeOfTask().equals("E")) {
            text = text + " | " + ((Event)task).getTime() + System.lineSeparator();
        } else {
            text = text + System.lineSeparator();
        }
        fw.write(text);
        fw.close();
    }

    /**
     * Update the file when the is changes to the contents of the file.
     * @throws IOException if file cannot be found when passed as an argument in
     *     the FileWriter constructor.
     */
    public void updateFile() throws IOException {
        FileWriter writer = new FileWriter(this.file, false);
        String text = "";
        for (Task task: this.list) {
            String isDone = task.isDone() ? "1" : "0";
            text = text + task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
            if (task.getTypeOfTask().equals("D")) {
                text = text + " | " + ((Deadline)task).getTime() + System.lineSeparator();
            } else if (task.getTypeOfTask().equals("E")) {
                text = text + " | " + ((Event)task).getTime() + System.lineSeparator();
            } else {
                text = text + System.lineSeparator();
            }
        }
        writer.write(text);
        writer.close();
    }

    /**
     * Gets HashSet<Task> that contains all the current task.
     * @return HashSet<TasK> that contains all the current task.
     */
    public HashSet<Task> getSet() {
        return this.set;
    }

}
