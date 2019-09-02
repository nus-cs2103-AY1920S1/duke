import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Storage {
    private PrintWriter writer;
    private String filename;

    public Storage(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        this.filename = filename;
        this.writer = new PrintWriter(filename, "UTF-8");
    }

    public void updateTodoFile(String todoString) {
        writer.printf(todoString);
        writer.close();
    }
}
