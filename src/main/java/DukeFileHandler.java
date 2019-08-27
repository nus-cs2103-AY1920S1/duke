import java.io.File;

import java.util.Scanner;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

class DukeFileHandler {
    private static File dataFile = new File("data/duke.txt");

    /**
     * Reads tasks from a (valid) data file and adds them to the given list.
     * Any existing tasks in the list are removed.
     * @param taskList          List of tasks to be filled.
     * @throws DukeException    If lines cannot be read from the file.
     */
    static void readTasksFromFile(List<Task> taskList) throws DukeException {
        try {
            Scanner fileScanner = new Scanner(dataFile);
            taskList.clear();
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] details = task.split(" \\| ");
                boolean isDone = details[1].equals("+");
                if (details[0].equals("T")) {
                    taskList.add(new Todo(details[2], isDone));
                } else if (details[0].equals("E")) {
                    taskList.add(new Event(details[2], details[3], isDone));
                } else if (details[0].equals("D")) {
                    taskList.add(new Deadline(details[2], details[3], isDone));
                } else {
                    throw new DukeException(
                            "I could not retrieve your previous tasks.");
                }
            }
        } catch (FileNotFoundException e) {
            // do nothing. Duke continues with an empty list.
        }
    }

    /**
     * Writes the tasks in the given list to an external data file.
     * @param taskList          List of tasks to be written.
     * @throws IOException      If file cannot be found, etc.
     */
    static void writeToFile(List<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        for (Task task : taskList) {
            fileWriter.append(task.formatAsData() + "\n");
        }
        fileWriter.close();
    }
}
