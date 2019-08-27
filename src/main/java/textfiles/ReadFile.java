package textfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile extends Storage {
    private String path;

    public ReadFile(String path) {
        this.path = path;
    }

    public ArrayList<String> OpenFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        String line = textReader.readLine();

        ArrayList<String> textData = new ArrayList<>();

        while (line != null) {
            textData.add(line);
            line = textReader.readLine();
        }

        textReader.close();
        return textData;
    }
}
