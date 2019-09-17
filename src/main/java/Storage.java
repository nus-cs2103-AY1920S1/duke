import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private String filePath;
    static String TODO = "[T]";
    static String DEADLINE = "[D]";
    static String EVENT = "[E]";
    static String COMPLETE = "[O]";
    static String INCOMPLETE = "[X]";

    /**
     * Constructor.
     *
     * @param s location of file
     */
    public Storage(String s) {
        this.filePath = s;
    }

    /**
     * Loads tasks to task list.
     *
     * @return LinkedList containing tasks
     * @throws DukeException if file does not exist
     */
    public LinkedList<Task> load() throws DukeException {
        File file = new File(filePath);
        LinkedList<Task> result = new LinkedList<>();
        if (!file.exists()) {
            throw new DukeException("Save file not found! Duke will be saving everything to a new file.");
        }
        try {
            List<String> temp = Files.lines(file.toPath())
                    .collect(Collectors.toList());
            for (String s : temp) {
                Task curr = null;
                String[] strArray = s.split(". ", 2);
                if (strArray.length != 2) {
                    throw new DukeException("Invalid data entries in save file!");
                } else {
                    String cmd = strArray[1].substring(0, 3);
                    String description = strArray[1].substring(6);
                    String status = strArray[1].substring(3, 6);
                    if (cmd.equals(TODO)) {
                        curr = new Todo(description);
                    } else if (cmd.equals(DEADLINE)) {
                        curr = new Deadline(description);
                    } else if (cmd.equals(EVENT)) {
                        curr = new Event(description);
                    } else {
                        throw new DukeException("Please make sure all the entries in your file "
                                + "starts with the correct prefix!");
                    }

                    if (status.equals(COMPLETE)) {
                        curr.setDone();
                    } else if (!status.equals(INCOMPLETE)) {
                        throw new DukeException("Please make sure all the entries in your file "
                                + "starts with the correct prefix!");
                    }
                }

                result.add(curr);
            }
        } catch (IOException e) {
            throw new DukeException("File not found!");
        }
        return result;
    }

    /**
     * Updates Save file with any changes.
     *
     * @param tasks Class dealing with manipulating global LinkedList storing tasks
     * @throws DukeException if save file fails
     */
    public void updateSaveFile(LinkedList<Task> tasks) throws DukeException {
        try {
            File saveFile = new File(filePath);
            FileWriter fw = new FileWriter(saveFile, false);
            int i = 1;
            //StringBuilder result = new StringBuilder();
            for (Task t : tasks) {
                fw.write(i + ". " + t.toFileString() + "\n");
                i++;
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save failed");
        }

    }


}
