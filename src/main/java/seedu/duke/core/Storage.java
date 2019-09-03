package seedu.duke.core;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskListEmptyException;
import seedu.duke.model.Deadline;
import seedu.duke.model.Event;
import seedu.duke.model.Task;
import seedu.duke.model.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static String DIRECTORY_PATH = "D:/project/CS2103T/duke/data";
    private static String FILEPATH = DIRECTORY_PATH + "/duke.txt";

    /**
     * Initialises external text file configuration.
     * @return text file which stores the task list and is created if not exists.
     * @throws IOException when file cannot be created.
     */
    public File initFile() throws IOException {
        new File(DIRECTORY_PATH).mkdir();

        //System.out.println(FILEPATH);
        File textFile = new File(FILEPATH);
        textFile.createNewFile();
        return textFile;
    }

    /**
     * Saves task in both task list (ArrayList) and updates in the external duke.txt file.
     * @param list Task List (ArrayList) where all tasks are stored.
     * @throws IOException when file cannot be opened or modified.
     */
    public void saveTask(List<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(FILEPATH, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Task t : list) {
            printWriter.println(t.toTextFileString());
        }

        printWriter.close();
    }

    //Future exception implementation required
    /**
     * loads tasks from external text file, duke.txt.
     * @param filePath file path of external text file, duke.txt.
     * @return Task list (ArrayList) which includes all tasks.
     * @throws IOException when file cannot be opened or loaded.
     * @throws ParseException when date written in duke.txt has incorrect format.
     */
    public List<Task> loadTask(String filePath) throws IOException, ParseException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        List<Task> list = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            String[] arr = line.split(",");
            Task t = new Task("");

            String type = arr[0];
            String desc = arr[2];
            int status = Integer.valueOf(arr[1]);

            if (type.equals("T")) {
                t = new Todo(desc, status);
            } else if (type.equals("D")) {
                String by = arr[3];
                t = new Deadline(desc, by, status);
            } else if (type.equals("E")) {
                String at = arr[3];
                t = new Event(desc, at, status);
            }
            line = reader.readLine();
            list.add(t);
        }
        reader.close();
        return list;
    }

    /**
     * Adds task into the task list to both ArrayList and text file, duke.txt.
     * @param list Task List (ArrayList), which includes all tasks.
     * @param cmd command string input, which determines which type of task.
     * @param desc task description.
     * @param time specified time for Event and Deadline objects.
     * @return Task object for tracking purpose.
     * @throws DukeException when user enters empty input for either cmd, desc or time.
     * @throws IOException when file is corrupted or cannot be opened.
     * @throws ParseException when date entered from the user is in incorrect date format.
     */
    public String addTask(String output, List<Task> list, String cmd, String desc, String time) throws DukeException,
            IOException, ParseException {
        Task task = new Task();
        if (cmd == null || desc == null || time == null) {
            throw new DukeException("oops! cmd/desc/time is null..");
        } else if (cmd.equals("") || desc.equals("") || time.equals("")) {
            throw new DukeException("oops! you entered cmd/desc/time empty..");
        } else {
            if (cmd.equals("todo")) {
                task = new Todo(desc);
            } else if (cmd.equals("deadline")) {
                task = new Deadline(desc, time);
            } else if (cmd.equals("event")) {
                task = new Event(desc, time);
            }
            list.add(task);

            saveTask(list);
            output += "Got it. I've added this task:\n";
            output += "  " + task + "\n";
            output += "Now you have " + list.size() + " tasks in the list.\n";
            return output;
        }
    }

    /**
     * searches any given keyword from the task description in the task list.
     * @param list Task List (ArrayList) that contains all tasks.
     * @param description task description.
     * @return Task List (ArrayList) based on the search keyword.
     */
    public List<Task> searchTask(List<Task> list, String description) {
        List<Task> searchResultList = new ArrayList<>();

        for (Task t : list) {
            String[] arr = t.getDescription().split(" ");

            for (String word : arr) {
                if (word.equals(description)) {
                    searchResultList.add(t);
                }
            }
        }
        return searchResultList;
    }

    /**
     * removes the task from the task list and from text file, duke.txt.
     * @param list Task List (ArrayList) which includes all tasks.
     * @param index index number that user wants to remove from the task list.
     * @throws TaskListEmptyException when task list is empty but user inputs an index regardless of the index.
     * @throws DukeException when user enters index that is out of boundaries of the list index.
     * @throws IOException when text file cannot be opened or modified.
     */
    public String removeTask(String output, List<Task> list, int index) throws TaskListEmptyException,
            DukeException, IOException {
        if (list.isEmpty()) {
            throw new TaskListEmptyException("list is empty");
        } else if (index < 0 || list.size() < index  + 1) {
            throw new DukeException("Entered index is out of bound: " + index);
        } else {
            output += "Noted. I've removed this task: \n";
            Task t = list.get(index);
            output += "  " + t + "\n";
            list.remove(index);
            saveTask(list);
            output += "Now you have " + list.size() + " tasks in the list.\n";
        }
        return output;
    }
}
