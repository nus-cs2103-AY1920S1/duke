package taskchick.storage;

import taskchick.exception.TaskChickException;
import taskchick.task.Deadline;
import taskchick.task.Event;
import taskchick.task.Task;
import taskchick.task.Todo;
import taskchick.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a Storage object with the file path assigned.
     *
     * @param filePath Location of task list to load from and store to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Interprets the todo in the saved string format and creates a todo object.
     *
     * @param todoInString Todo in saved string format.
     * @return Todo object created.
     */
    private Todo loadTodo(String todoInString) {
        String[] currArray = todoInString.split(" \\| ");
        Todo currTodo = new Todo(currArray[2]);
        if (currArray[1].equals("v")) {
            currTodo.markAsCompleted();
        }
        return currTodo;
    }

    /**
     * Interprets the deadline in the saved string format and creates a deadline object.
     *
     * @param deadlineInString Deadline in saved string format.
     * @return Deadline object created.
     */
    private Deadline loadDeadline(String deadlineInString) {
        String[] currArray = deadlineInString.split(" \\| ");
        Deadline currDeadline = new Deadline(currArray[2], currArray[3]);
        if (currArray[1].equals("v")) {
            currDeadline.markAsCompleted();
        }
        return currDeadline;
    }

    /**
     * Interprets the event in the saved string format and creates an event object.
     *
     * @param eventInString Event in saved string format.
     * @return Event object created.
     */
    private Event loadEvent(String eventInString) {
        String[] currArray = eventInString.split(" \\| ");
        Event currEvent = new Event(currArray[2], currArray[3]);
        if (currArray[1].equals("v")) {
            currEvent.markAsCompleted();
        }
        return currEvent;
    }

    /**
     * Converts the contents of .txt file into a String.
     *
     * @return .txt file as a String.
     * @throws FileNotFoundException If .txt file cannot be found.
     */
    private String convertFileToString() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine() + "\n");
        }
        sc.close();
        return sb.toString();
    }

    /**
     * Creates a task list based on the string of tasks given.
     *
     * @param tasks Tasks given with their type, description, completion status and date (if applicable).
     * @return Populated list of tasks.
     */
    public ArrayList<Task> loadTasks(String tasks) {
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        Scanner sc = new Scanner(tasks);
        while (sc.hasNextLine()) {
            String currTask = sc.nextLine();
            if (currTask.charAt(0) == 'T') {
                loadedTaskList.add(loadTodo(currTask));
            } else if (currTask.charAt(0) == 'D') {
                loadedTaskList.add(loadDeadline(currTask));
            } else if (currTask.charAt(0) == 'E') {
                loadedTaskList.add(loadEvent(currTask));
            } else {
                throw new TaskChickException("OOPS!!! Your task file appears to be corrupted.");
            }
        }
        sc.close();
        return loadedTaskList;

    }

    /**
     * Loads the task list from the previous session form the hard disk, if any.
     *
     * @return ArrayList of task that has been loaded from previous session.
     * @throws FileNotFoundException If there was no previous session.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        return loadTasks(convertFileToString());
    }

    /**
     * Stores the task list in the hard disk.
     *
     * @param tasks Task list to retrieve.
     * @throws IOException If hard disk cannot be found in the given file path.
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(getSavedListString(tasks));
        fw.close();
    }

    /**
     * Formats the task list to be stored.
     * @param tasks Task list to retrieve.
     * @return All tasks in the list in a simplified format.
     */
    public static String getSavedListString(TaskList tasks) {
        String allTasks = "";
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            allTasks += tasks.getTasks().get(i).toSave() + "\n";
        }
        return allTasks;
    }
}