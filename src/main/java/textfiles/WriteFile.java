package textfiles;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Writes duke.txt file in the user local drive and save it whenever
 * there is any commands which changes the task list in Duke.
 */
public class WriteFile extends Storage {
    /**
     * This field stores the path to duke.txt file.
     */
    private String path;
    /**
     * This field determines whether the txt file can be overwritten.
     */
    private boolean append_to_file;

    /**
     * Constructor for the WriteFile class. Edits and saves any changes on
     * the task list of Duke and store it in the user's local drive.
     * @param path File path in which duke.txt is going to be saved at.
     * @param append_to_file Determines whether the file is editable.
     */
    public WriteFile(String path, boolean append_to_file) {
        this.path = path;
        this.append_to_file = append_to_file;
    }

    /**
     * Write any changes made to Duke task list and save it.
     * @param text Text which describes the task and Duke will update the
     *             duke.txt file accordingly.
     * @throws IOException Exception thrown when there is no duke.txt file
     * to write to.
     */
    public void writeToFile(String text) throws IOException {
        FileWriter write = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("%s" + "%n", text);

        print_line.close();
    }

    /**
     * Remove the specific task from the task list based on index.
     * @param f Indicates the file name to be renamed into.
     * @param toRemove Index of the task to be removed.
     * @throws IOException Exception thrown when there is no duke.txt file.
     */
    public void removeNthLine(String f, int toRemove) throws IOException {
        File tmp = File.createTempFile("tmp", ".txt");

        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

        for (int i = 0; i < toRemove; i++)
            bw.write(String.format("%s%n", br.readLine()));

        br.readLine();

        String l;
        while (null != (l = br.readLine()))
            bw.write(String.format("%s%n", l));

        br.close();
        bw.close();

        File oldFile = new File(f);
        if (oldFile.delete()) {
            //noinspection ResultOfMethodCallIgnored
            tmp.renameTo(oldFile);
        }
    }

    /**
     * Edits the current task. Used when the command "done" is called.
     * @param f Indicates the file name to be renamed into.
     * @param toReplace Index of the task to be removed.
     * @param currTask Indicates the current task which needs to be marked
     *                 as done.
     * @throws IOException Exception thrown when there is no duke.txt file.
     */
    public void replaceNthLine(String f, int toReplace, Task currTask) throws IOException {
        File tmp = File.createTempFile("tmp", ".txt");

        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

        for (int i = 0; i < toReplace; i++) {
            bw.write(String.format("%s%n", br.readLine()));
        }

        if (currTask instanceof ToDo) {
            bw.write("T | " + "\u2713" + " | " + currTask.getDescription() + "\n");
        } else if (currTask instanceof Deadline) {
            bw.write("D | " + "\u2713" + " | " + currTask.getDescription()
                    + " | " + ((Deadline) currTask).getBy() + "\n");
        } else {
            bw.write("E | " + "\u2713" + " | " + currTask.getDescription()
                    + " | " + ((Event) currTask).getAt() + "\n");
        }

        br.readLine();

        String l;
        while (null != (l = br.readLine()))
            bw.write(String.format("%s%n", l));

        br.close();
        bw.close();

        File oldFile = new File(f);
        if (oldFile.delete())
            //noinspection ResultOfMethodCallIgnored
            tmp.renameTo(oldFile);
    }
}
