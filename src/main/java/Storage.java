import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
        ensureStorageCreated();
    }

    private void ensureStorageCreated() {
        File file = new File(System.getProperty("user.dir") + storagePath);
        file.getParentFile().mkdirs();
    }

    public void save(TaskList tasks) throws DukeException {
        try(FileWriter writer = new FileWriter(storagePath)) {
            writer.write(tasks.printTasksForHardDisk());
        } catch (IOException ex) {
            throw new DukeException("There is an issue in updating duke.txt.");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(storagePath));
        } catch (FileNotFoundException ex) {
            return tasks;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] datas = line.split(" \\| ");

            Task task;
            switch (datas[0]) {
            case "E":
                task = new Event(datas[2], datas[3]);
                break;
            case "D":
                task = new Deadline(datas[2], datas[3]);
                break;
            case "T":
                task = new Todo(datas[2]);
                break;
            default:
                throw new DukeException("Unrecognized tasks");
            }

            if (datas[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
        }
        scanner.close();

        return tasks;
    }
}
