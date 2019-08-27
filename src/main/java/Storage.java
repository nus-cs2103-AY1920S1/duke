import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {

    Scanner sc;

    public Storage (String filePath) {
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
        List<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()) {
            inputs.add(sc.nextLine());
        }
        return inputs;
    }

}
