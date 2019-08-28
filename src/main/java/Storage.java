import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    Scanner sc;
    String filePath;
    List<String> inputs = new ArrayList<>();

    public Storage (String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                throw new FileNotFoundException();
            }
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public List<String> processInput() {
        while (sc.hasNextLine()) {
            inputs.add(sc.nextLine());
        }
        return inputs;
    }

    public void writeToFile(String text) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
            System.out.println("test");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }

}
