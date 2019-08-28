import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File duke = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(duke);
            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                String[] words = nextLine.split(" \\| ");
                if (words[0].equals("T")) {
                    Task t = new ToDo(words[2]);
                    if (words[1].equals("\u2713")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
                if (words[0].equals("D")) {
                    Task t = new Deadline(words[2], words[3]);
                    if (words[1].equals("\u2713")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } 
                if (words[0].equals("E")) {
                    Task t = new Event(words[2], words[3]);
                    if (words[1].equals("\u2713")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Load failed");
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks.getList()) {
                fw.write(t.format() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks :-(");
        }
    }
}






