package textfiles;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
    private String path;
    private boolean append_to_file = false;

    public WriteFile(String path) {
        this.path = path;
    }

    public WriteFile(String path, boolean append_to_file) {
        this.path = path;
        this.append_to_file = append_to_file;
    }

    public void writeToFile(String text) throws IOException {
        FileWriter write = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("%s" + "%n", text);

        print_line.close();
    }
}
