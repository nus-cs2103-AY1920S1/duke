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

public class WriteFile extends Storage {
    private String path;
    private boolean append_to_file;

    public WriteFile(String path, boolean append_to_file) {
        this.path = path;
        this.append_to_file = append_to_file;
    }

    public void writeToFile(String text) throws IOException {
        FileWriter write = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("%s" + "%n", text);

        print_line.close();
    }

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
