import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList extends ArrayList<Task> {
    private String filePath;

    TaskList(String filePath) throws IOException{
        this.filePath = filePath;

        try {
            String data = Files.readString(Path.of(filePath));
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                super.add(Task.parse(sc.nextLine()));
            }
        } catch (IOException e) {
            // no such file
        }
    }

    @Override
    public boolean add(Task task) {
        boolean added = super.add(task);
        save();
        return added;
    }

    @Override
    public Task remove(int index) {
        Task removed = super.remove(index);
        save();
        return removed;
    }

    private void save() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this) {
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

    public void notifyChange() {
        save();
    }
}
