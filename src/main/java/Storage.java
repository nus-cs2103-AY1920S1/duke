import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Takes care of storing the tasks.
 */
public class Storage {
    private String filePath;
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructor for Storage.
     *
     * @param filePath representing a string for file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to load tasks from the file.
     *
     * @return A task list which has tasks loaded from the file
     */
    public List<Task> load() throws IOException {
        List<Task> tempList = new ArrayList<>();
        File file = new File(filePath);
        Path path = Paths.get(filePath);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent().getFileName());
        }
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] token = fileScanner.nextLine().split("\\|");
                if (token.length <= 2) {
                    throw new DukeException("Invalid line");
                }
                if (token[0].equals("T")) {
                    tempList.add(new Todo(token[2], token[1]));
                } else if (token[0].equals("D")) {
                    tempList.add(new Deadline(token[2], token[1], LocalDateTime.parse(token[3], dtf)));
                } else if (token[0].equals("E")) {
                    tempList.add(new Event(token[2], token[1], LocalDateTime.parse(token[3], dtf)));
                } else {
                    throw new DukeException("Invalid task type");
                }
            }
            return tempList;
        } catch (DukeException de) {
            System.out.println(de);
        } catch (IOException ie) {
            System.out.println("IOException");
        }
        return tempList;
    }

    /**
     * Method to write to the file.
     *
     * @param tasks a list of tasks to be written to the file
     */
    public void writeToFile(List<Task> tasks) throws IOException {
        FileWriter pw = new FileWriter(filePath);
        Path path = Paths.get(filePath);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent().getFileName());
        }
        String output = "";
        for (Task t : tasks) {
            if (t.getType().equals("T")) {
                output += t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "\n";
            } else if (t.getType().equals("E")) {
                output += t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "|"
                        + ((Event) t).getAt() + "\n";
            } else if (t.getType().equals("D")) {
                output += t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "|"
                        + ((Deadline) t).getBy() + "\n";
            }
        }
        pw.write(output);
        pw.close();
    }
}
