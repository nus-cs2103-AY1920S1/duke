import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private Scanner fileSc;

    /**
     * Creates a Storage object which handles file I/O for reading/saving tasks.
     * @param filePath The location of the text file to be used.
     *                 If the file does not exist, it will create the file.
     */
    Storage(String filePath) throws DukeIoException {
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            if (file.exists()) {
                this.fileSc = new Scanner(new File(filePath));
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                this.fileSc = new Scanner(file);
            }
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileSc.hasNextLine()) {
            tasks.add(parseFileLine(fileSc.nextLine()));
        }
        return tasks;
    }

    void writeToFile(TaskList tasks) throws DukeIoException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (int i = 1; i <= tasks.size(); i++) {
                bw.write(tasks.get(i).toFileString());
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    private Task parseFileLine(String line) throws DukeException {
        String[] arg = line.split("\t");
        if (arg.length < 3) {
            throw new DukeInvalidTaskFormatException(line);
        }
        assert arg.length >= 3;
        Task t;
        switch (arg[0]) {
        case "T":
            t = new TodoTask(arg[2]);
            break;
        case "D":
            if (arg.length < 4) {
                throw new DukeInvalidTaskFormatException(line);
            }
            t = new DeadlineTask(arg[2], arg[3]);
            break;
        case "E":
            if (arg.length < 4) {
                throw new DukeInvalidTaskFormatException(line);
            }
            t = new EventTask(arg[2], arg[3]);
            break;
        default:
            throw new DukeInvalidTaskFormatException(line);
        }
        if (arg[1].equals("1")) {
            t.markAsDone();
        }
        return t;
    }
}
