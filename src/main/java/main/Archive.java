package main;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Archive {

    private String filePath;
    private boolean isValidFilePath;

    /**
     * Creates a new Archive object which reads and writes to file at filepath.
     * If file path does not exist, a default task list would be created as follows:
     * [root]/data/tasks.txt
     *
     * @param filePath File path to read from and to write to.
     */
    public Archive(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        isValidFilePath = file.exists();
    }

    /**
     * Creates a default Archive object with predetermined file path.
     *
     * @throws DukeException if there is an error in creating the file at the
     * predetermined file path
     */
    public Archive() throws DukeException {
        try {
            filePath = createNewFile();
        } catch (IOException e) {
            isValidFilePath = false;
            throw new DukeException("File cannot be created.");
        }
    }

    /**
     * Returns true if file path is valid.
     *
     * @return true if file path is valid.
     */
    public boolean isValidFilePath() {
        return isValidFilePath;
    }

    /**
     * Makes a directory called Data if it does not exist.
     *
     * @return file path of new data directory.
     */
    public String makeDataDirectoryIfNotExist() {
        String root = new File(System.getProperty("user.dir")).getPath();
        File dataDir = new File(root + "/data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        return dataDir.toString();
    }

    /**
     * Creates a new file at [root]/data/archive.txt to store the task list.
     *
     * @throws IOException if file cannot be created
     */
    public String createNewFile() throws IOException {
        String dataDir = makeDataDirectoryIfNotExist();
        File archiveFile = new File(dataDir + "/archive.txt");
        boolean isFileCreated = archiveFile.createNewFile();
        if (isFileCreated) {
            filePath = archiveFile.toString();
            isValidFilePath = true;
            return archiveFile.toString();
        } else {
            return null;
        }
    }

    public void addTaskToArchive(Task task) throws IOException {
        if (!isValidFilePath) {
            return;
        }
        LocalDate localDate = LocalDate.now();
        String dateArchived = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);
        appendToFile(filePath, task.publishTask() + " (Date archived: " + dateArchived + ")");
    }

    void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

}
