package main;

import task.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Archive extends DataFile {

    public Archive() throws DukeException {
        super();
    }

    public Archive(String filePath) {
        super(filePath);
    }

    @Override
    public String createNewFile() throws IOException {
        String dataDir = makeDataDirectoryIfNotExist();
        File archiveFile = new File(dataDir + "/archive.txt");
        boolean isFileCreated = archiveFile.createNewFile();
        if (isFileCreated) {
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

}
