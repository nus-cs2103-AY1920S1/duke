package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

class Storage {
    private File file;

    Storage(final String fileName) {
        Path dataDir = getDataDir();
        this.file = dataDir.resolve(fileName).toFile();
        if (!this.file.exists()) {
            dataDir.toFile().mkdirs();
        }
    }

    TaskList loadTasks() throws DukeException {
        TaskList tasks = new TaskList();
        if (!this.file.isFile()) {
            return tasks;
        }

        Scanner scanner;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            // Should not reach here
            throw new DukeException("Cannot find data file.");
        }

        while (scanner.hasNextLine()) {
            String[] tokens = scanner.nextLine().split(" \\| ");
            String type = tokens[0];
            boolean isDone = !tokens[1].equals("0");
            String description = tokens[2];
            Task task;
            switch (type) {
                case "T": {
                    task = new Todo(description, isDone);
                    break;
                }
                case "D": {
                    String by = tokens[3];
                    task = new Deadline(description, by, isDone);
                    break;
                }
                case "E": {
                    String at = tokens[3];
                    task = new Event(description, at, isDone);
                    break;
                }
                default:
                    throw new DukeException("Data file is corrupted.");
            }
            tasks.addTask(task);
        }
        scanner.close();
        return tasks;
    }

    void writeTasks(final TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.file, false);
            writer.write(tasks.toStorageString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to update task list on disk. " + e.getMessage());
        }
    }

    private Path getDataDir() {
        String os = System.getProperty("os.name").toUpperCase();
        String path;
        if (os.contains("WIN")) {
            path = System.getenv("APPDATA");
        } else if (os.contains("MAC")) {
            path = System.getProperty("user.home")
                + "/Library/Application Support";
        } else { // Assume *nix
            String xdgDataHome = System.getenv("XDG_DATA_HOME");
            path = xdgDataHome == null
                ? System.getProperty("user.home") + "/.local/share"
                : xdgDataHome;
        }
        return Path.of(path + "/Duke");
    }
}
