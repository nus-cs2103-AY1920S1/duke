import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading/saving a Duke object's archives from/to a file in the hard drive.
 */
public class ArchiveStorage extends Storage {

    /** The string used to mark the end of an archive in the text file. */
    private String endOfArchiveMarker = "/end";

    /**
     * Creates an ArchiveStorage object with the file's file path as the argument.
     *
     * @param filePath String of file's filepath.
     */
    public ArchiveStorage(String filePath) {
        super(filePath);
    }

    /**
     * Accesses the file and extracts tasks according to their respective archives,
     * saving the tasks of each archive into its own TaskList object and storing all Taskist archives into a HashMap.
     * Each archive is hashed with its archive name as the key.
     *
     * @param archives Archives of tasks extracted from the file will be added to this HashMap.
     */
    public void getArchivedTasksFromFile(HashMap<String, TaskList> archives) {
        try {
            File taskFile = new File(filePath);
            new File("./data").mkdirs();
            if (!taskFile.exists()) {
                taskFile.createNewFile();
                this.fileAccessStatus = "Any previously saved archives were not be loaded: new file was created";
            } else {
                Scanner scanner = new Scanner(taskFile);
                String currentArchiveName = "";
                TaskList currentArchiveTasks = new TaskList();

                while (scanner.hasNext()) {
                    String textLine = scanner.nextLine();

                    if (isEndOfArchiveMarker(textLine)) {
                        archives.put(currentArchiveName, currentArchiveTasks);
                    } else if (isArchiveName(textLine)) {
                        currentArchiveName = textLine;
                        currentArchiveTasks = new TaskList();
                    } else {
                        currentArchiveTasks.addTask(stringToTask(textLine));
                    }
                }
                this.fileAccessStatus = "Previously saved archives successfully loaded :)";
            }

        } catch (IOException e) {
            this.fileAccessStatus = "Any previously saved archives were not be loaded: Could not create new file :(";
        } catch (InvalidTaskArgumentDukeException e) {
            this.fileAccessStatus = "Any previously saved archives were not be loaded: Invalid format in file :(";
        }
    }

    /**
     * saves the tasks in the TaskList archives into the file.
     *
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     */
    public void loadArchivedTasksToFile(HashMap<String, TaskList> archives) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            List<String> archiveNames = new ArrayList<>(archives.keySet());
            for (int archiveIndex = 0; archiveIndex < archiveNames.size(); archiveIndex++) {
                String archiveName = archiveNames.get(archiveIndex);
                fileWriter.write(archiveName + System.lineSeparator());
                TaskList archiveTasks = archives.get(archiveName);
                for (int i = 0; i < archiveTasks.taskListSize(); i++) {
                    fileWriter.write(taskToString(archiveTasks.getTask(i)));
                    fileWriter.write(System.lineSeparator());
                }

                fileWriter.write(endOfArchiveMarker);
                if (archiveIndex != archiveNames.size() - 1) {
                    fileWriter.write(System.lineSeparator());
                }

            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    /**
     * Checks if the given String is a valid archive name.
     *
     * @param text String to be determined if valid.
     * @return true if the given string is a valid archive name, false otherwise.
     */
    private boolean isArchiveName(String text) {
        String[] split = text.split("\\|");
        return split.length == 1;
    }

    public boolean isEndOfArchiveMarker(String text) {
        return text.equals(endOfArchiveMarker);
    }
}
