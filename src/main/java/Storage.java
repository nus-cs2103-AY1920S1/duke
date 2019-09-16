import java.io.FileWriter;
import java.io.IOException;

/**
 * Contains the method to update data to the file when the user exits.
 *
 */
public class Storage {

    String filePath;

    /**
     * Creates a storage object with a specified file path.
     *
      * @param filePath the absolute path of the file which stores user data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes data to the file when user exits.
     *
      * @param content the information to be updated
     *  @param  filePath file where data is to be stored
     * @throws IOException if file not found, insufficient disk space and other failed input output operations.
     */
    public void writeFile(String content,String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }
}