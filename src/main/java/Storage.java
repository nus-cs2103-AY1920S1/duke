import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void writeFile( String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

}
