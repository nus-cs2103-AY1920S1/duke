package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class DataFile {

    String filePath;
    boolean isValidFilePath;

    public DataFile(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        isValidFilePath = file.exists();
        System.out.println("File at " + filePath + "exists? " + isValidFilePath);
    }

    public DataFile() throws DukeException {
        try {
            filePath = createNewFile();
        } catch (IOException e) {
            isValidFilePath = false;
            throw new DukeException("File cannot be created.");
        }
    }

    public abstract String createNewFile() throws IOException;

    public boolean isValidFilePath() {
        return isValidFilePath;
    }

    public String makeDataDirectoryIfNotExist() {
        String root = new File(System.getProperty("user.dir")).getPath();
        File dataDir = new File(root + "/data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        return dataDir.toString();
    }

    void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * Clears the file found at filePath.
     *
     * @throws DukeException if file is not found.
     */
    public void clearAll() throws DukeException {
        if (!isValidFilePath) {
            return;
        }
        try {
            new FileWriter(filePath); //create new file
        } catch (IOException e) {
            throw new DukeException("File does not exist, there is nothing to clear!");
        }
    }
}
