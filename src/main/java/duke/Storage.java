package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path_string;

    public Storage(String path_string) {
        this.path_string = path_string;
    }

    public ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File f = new File(path_string);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        return input;
    }

    public void write(ArrayList<String> formatStrings) throws IOException {
        Path path = Paths.get(path_string);
        Files.write(path, formatStrings);
    }
}
