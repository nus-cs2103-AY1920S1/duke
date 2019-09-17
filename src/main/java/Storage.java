import java.io.*;

/**
 * Contains the method to update data to the file when the user exits.
 *
 */
public class Storage {

     File file ;
     File archiveFile;

    /**
     * Creates a storage object with a specified file path.
     *
     */
    public Storage() throws IOException {
        String folderPath = System.getProperty("user.dir") + "\\data";
        File directory = new File(folderPath);
        directory.mkdirs();
        String filePath = System.getProperty("user.dir") + "\\data\\duke.txt";
        file = new File(filePath);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(filePath, true);
        fos.close();
        String archiveFilePath = System.getProperty("user.dir") + "\\data\\DukeArchive.txt";
        archiveFile = new File(archiveFilePath);
        FileOutputStream fs = new FileOutputStream(archiveFilePath, true);
        fs.close();
    }



    /**
     * Writes data to the file when user exits.
     *
     * @param content the information to be updated
     * @throws IOException if file not found, insufficient disk space and other failed input output operations.
     */
    public void writeFile(String content) throws IOException {
        FileWriter saveToFile = new FileWriter(file);
        saveToFile.write(content);
        saveToFile.close();
    }
}
