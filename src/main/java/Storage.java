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

    public Stream<String> load() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return reader.lines();
    }

    public void save(Stream<String> stream) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            stream.forEach(line-> {
                try {
                    writer.write(line);
                } catch(IOException io) {
                    System.out.println(io);
                }
            });
            writer.close();
        } catch (IOException io) {
            System.out.println(io);
        }
    }

}
