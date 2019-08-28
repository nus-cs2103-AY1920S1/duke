import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.Stream;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Stream<String> load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            return reader.lines();
        } catch (IOException io) {
            System.out.println(io);
            return Stream.of();
        }
    }

    public void save(String db) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(db);
            writer.close();
        } catch (IOException io) {
            System.out.println(io);
        }
    }

}
