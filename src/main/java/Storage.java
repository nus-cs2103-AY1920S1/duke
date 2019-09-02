import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/* THINGS THAT NEED TO BE ADDED:
    1. load function that loads ArrayList in
    2.
 */

public class Storage {
    private PrintWriter writer;
    private String filename;

    public Storage(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        this.filename = filename;
        createTodoFile();
    }

    public void createTodoFile() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(filename, "UTF-8");
    }

    public void updateTodoFile(String todoString) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(filename, "UTF-8");
        writer.printf(todoString);
        writer.close();
    }
}
