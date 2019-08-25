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

    public Storage(String s) {
        this.filePath = s;
    }

    public LinkedList<Task> load() throws DukeException {
        File file = new File(filePath);
        LinkedList<Task> result = new LinkedList<>();
        if (!file.exists()) {
            throw new DukeException("File not found!");
        } else {
            try {
                List<String> temp = Files.lines(file.toPath())
                        .collect(Collectors.toList());
                for (String s : temp) {
                    Task curr = null;
                    if (s.contains("[T]")) {
                        curr = new Todo(s.substring(7));
                    } else if (s.contains("[D]")) {
                        curr = new Deadline(s.substring(7));
                    } else if (s.contains("[E]")) {
                        curr = new Event(s.substring(7));
                    }

                    if (s.contains("[✓]")) {
                        curr.markAsDone();
                    }

                    result.add(curr);
                }

            } catch (IOException e) {
                throw new DukeException("File not found!");
            }
        }

        return result;
    }

    public void updateSaveFile(LinkedList<Task> tasks) throws DukeException {
        try {
            File saveFile = new File(filePath);
            FileWriter fw = new FileWriter(saveFile, false);
            for (Task t : tasks) {
                fw.write(t.toString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save failed");
        }

    }


}
