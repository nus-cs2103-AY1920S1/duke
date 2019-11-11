package seedu.duke.tasks;

import seedu.duke.tasks.exceptions.InvalidTaskException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class TaskStorage {
    private File file;

    /**
     * Constructor for a <code>TaskStorage</code> object that handles storing data into a text file.
     *
     * @param filePath A path to the file that is used to store data.
     * @throws IOException An error that occurs while trying to access the file (i.e. read or write operation).
     */
    TaskStorage(String filePath) throws IOException {
        this.file = new File(filePath);

        File directory = new File(String.valueOf(Path.of(filePath).getParent()));
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        if (!file.isFile()) {
            file.createNewFile();
        }
    }

    /**
     * Loads tasks that are listed in the text file.
     *
     * @return List of tasks that are present in the storage.
     * @throws IOException          An error that occurs while trying to read the file.
     * @throws InvalidTaskException An error that occurs if the format of tasks that are stored in the
     *                              database is invalid
     */
    List<Task> loadTasks() throws IOException, InvalidTaskException {
        List<Task> tasks = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Task task = Task.parseTask(line);
            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Saves list of tasks in a text file.
     *
     * @param tasks List of tasks to be saved in the database, which is represented as a text file.
     * @throws IOException An error that occurs while trying to write into the file.
     */
    void saveTasks(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        for (Task task : tasks) {
            printWriter.println(task.getDataRepresentation());
        }

        printWriter.close();
    }
}
