import exceptions.DukeException;
import task.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            String data = Files.readString(Path.of(filePath));
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                list.add(Task.parse(sc.nextLine()));
            }
            return list;
        } catch (IOException e) {
            // no such file
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    void save(List<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (Task task : list) {
            sb.append(task.getStringRepresentation());
            sb.append("\n");
        }
        String text = sb.toString();
        BufferedWriter output = null;
        try {
            File file = new File(filePath);
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
