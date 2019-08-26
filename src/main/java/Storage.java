import java.io.*;
import java.util.LinkedList;

class Storage {

    private final String filepath;
    private final LinkedList<Task> allTasks = new LinkedList<>();

    /**
     * Initialises a Storage session.
     *
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the input file line by line to add tasks into the TaskList.
     *
     * @return The LinkedList containing all loaded tasks.
     * @throws DukeException In the event that the file cannot be read properly.
     */
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
                }
                allTasks.add(t);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return allTasks;
    }

    /**
     * Append a new task to the end of the file.
     *
     * @param t The task to be added.
     */
    public void appendTaskToFile(Task t) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filepath, true));
            output.append(t.toStore()).append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task from the file.
     *
     * @param t The task to be deleted.
     */
    public void deleteTaskFromFile(Task t) {
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
        tempFile.renameTo(inputFile);
    }
}
