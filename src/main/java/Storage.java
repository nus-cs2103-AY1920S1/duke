import java.io.*;
import java.util.LinkedList;

public class Storage {

    private String filepath;
    private LinkedList<Task> allTasks = new LinkedList<>();

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public LinkedList<Task> load() throws DukeException {
        try {
            File f = new File(filepath);
            if (f.exists()) {
            } else {
                f.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            String currLine;
            while ((currLine = bufferedReader.readLine()) != null) {
                String[] formatted_text = currLine.split("\\|");
                Task t;
                switch (formatted_text[0]) {
                    case "T":
                        t = new ToDo(formatted_text[2]);
                        break;
                    case "D":
                        t = new Deadline(formatted_text[2], formatted_text[3]);
                        break;
                    case "E":
                        t = new Event(formatted_text[2], formatted_text[3]);
                        break;
                    default:
                        t = new Task("");
                        break;
                }
                if (formatted_text[1].equals("1")) {
                    t.markAsDone();
                } else {
                }
                allTasks.add(t);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return allTasks;
    }

    public boolean appendTaskToFile(Task t) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filepath, true));
            output.append(t.toStore() + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteTaskFromFile(Task t) {
        File inputFile = new File(filepath);
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = t.toStore();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + "\n");
            }
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean successful = tempFile.renameTo(inputFile);
        return successful;
    }
}
