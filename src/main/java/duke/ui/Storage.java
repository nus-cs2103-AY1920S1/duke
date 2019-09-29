package duke.ui;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class for Duke.
 */
public class Storage {
    private static List<Task> myList = new ArrayList<>();
    private static String TXTFILELOCATION;
    private File parentFile;

    public Storage(String filePath) {
        TXTFILELOCATION = filePath;
    }

    /**
     * Stores a task into the myList.
     *
     * @param str The string of the task detail.
     */
    private void putToList(String str) {
        assert str != null : "task detail should bot be empty";

        String[] oneLine = str.split(" \\| ");
        String firstWord = oneLine[0].trim();
        try {
            if (oneLine[1].trim().equals("1") || oneLine[1].trim().equals("0")) {
                if (firstWord.equals("T") && oneLine.length == 3) {
                    myList.add(new Todo(oneLine[2], oneLine[1]));
                } else if (firstWord.equals("D") && oneLine.length == 4) {
                    myList.add(new Deadline(oneLine[2], oneLine[3], oneLine[1]));
                } else if (firstWord.equals("E") && oneLine.length == 4) {
                    myList.add(new Event(oneLine[2], oneLine[3], oneLine[1]));
                } else {
                    throw new InvalidCommandException("[duke.txt]: I'm sorry, but I don't know what that means :-(");
                }
            } else {
                throw new InvalidNumberException("[duke.txt]: the description should have 0 or 1");
            }
        } catch (DukeException e) {
            Ui.printOutput(e);
        }
    }

    /**
     * Loads and returns a list of tasks from the storage.
     *
     * @return The list of task of tasks from the storage
     */
    public List<Task> load() throws DukeException {

        try {
            parentFile = new File(TXTFILELOCATION);
            Scanner sc = new Scanner(parentFile);

            while (sc.hasNextLine()) {
                putToList(sc.nextLine());
            }
        } catch (Exception e) {
            Ui.printOutput("[duke.txt]: duke.txt not found");
        }
        return myList;
    }

    /**
     * save a list of tasks to text file.
     *
     * @param tasks The list of tasks
     */
    public void save(TaskList tasks) throws Exception {
        try {
            parentFile = new File(TXTFILELOCATION).getParentFile();

            if (!parentFile.exists()) {
                parentFile.mkdir();
            }

            PrintWriter pr = new PrintWriter(TXTFILELOCATION);
            for (Task obj : myList) {
                pr.write(obj.getFormatToFile());
            }
            pr.close();
        } catch (FileNotFoundException e) {
            System.out.println();
            Ui.printOutput("duke.txt not found");
        }
    }
}
