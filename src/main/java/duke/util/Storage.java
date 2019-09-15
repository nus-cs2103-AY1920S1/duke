package duke.util;

import duke.note.Note;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Class to handle storage of data.
 */
public class Storage {
    private String path;
    private static final String LINE_DIVIDER = "____________________";

    /**
     * Constructor for Storage object.
     *
     * @param path path to save/read history relative to current directory
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Retrieves history of TaskList.
     *
     * @return History of TaskList if present
     * @throws IOException if there are errors reading the file
     */
    public ListManager retrieveHistory() throws IOException, DukeException {
        Path filePath = Paths.get(path);
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));
        ListManager lists = textToListManager(lines);
        return lists;
    }

    /**
     * Returns a list of Tasks.
     *
     * @param lines List of lines read from .txt.file
     * @return list of Task objects
     * @throws DukeException when history is corrupted
     */
    private ListManager textToListManager(ArrayList<String> lines) throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Note> notesList = new ArrayList<>();
        boolean readingTasks = true;
        for (String line : lines) {
            if (line.equals(LINE_DIVIDER)) {
                readingTasks = false;
                continue;
            }
            if (readingTasks) {
                String[] parts = line.split("\\|");
                String part = parts[0];
                switch (part) {
                case "T":
                    taskList.add(new Todo(parts[2], parts[1].equals("1")));
                    break;
                case "D":
                    taskList.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                    break;
                case "E":
                    taskList.add(new Event(parts[2], parts[3], parts[1].equals("1")));
                    break;
                default:
                    throw new DukeException("Corrupted history");
                }
                assert false : "Corrupted history";
            } else {
                notesList.add(new Note(line));
            }
        }
        return new ListManager(new TaskList(taskList), new NoteList(notesList));
    }

    /**
     * Saves the history before closing the app.
     *
     * @param lists lists to be saved as a .txt file
     * @throws IOException if there are errors writing to the file
     */
    public void saveHistory(ListManager lists) throws IOException {
        String result = "";
        ArrayList<Task> taskList = lists.getTasks().getTaskAsArrayList();
        for (Task t : taskList) {
            String type = "";
            String date = "";
            if (t instanceof Todo) {
                type = "T";
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                type = "D";
                date = d.getDate();
            } else if (t instanceof Event) {
                Event e = (Event) t;
                type = "E";
                date = e.getDate();
            }
            String completed = t.isCompleted() ? "1" : "0";
            String title = t.getName();
            if (t instanceof Todo) {
                String currentTask = type + "|" + completed + "|" + title + "\n";
                result += currentTask;
            } else {
                String currentTask = type + "|" + completed + "|" + title + "|" + date + "\n";
                result += currentTask;
            }
        }
        result += LINE_DIVIDER + "\n";
        ArrayList<Note> noteList = lists.getNotes().getNotesAsArrayList();
        for (Note n : noteList) {
            result += n.getDesc() + "\n";
        }
        Path filePath = Paths.get(path);
        Files.deleteIfExists(filePath);
        Files.write(filePath, result.getBytes(), StandardOpenOption.CREATE);
    }

    /**
     * Checks if TaskList history exists.
     *
     * @return true if TaskList history exists, false otherwise
     */
    public boolean historyExists() {
        Path filePath = Paths.get(path);
        return Files.exists(filePath);
    }

    /**
     * Creates a new .txt file to write history to.
     *
     * @throws IOException if there are errors creating the file
     */
    public void createFile() throws IOException {
        Path filePath = Paths.get(path);
        Path folderPath = Paths.get("./saved/");
        Files.createDirectory(folderPath);
        Files.createFile(filePath);
    }
}
