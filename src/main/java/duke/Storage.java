package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Handles interactions of the duke bot's list of tasks and the file that saves these tasks.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file with previously saved list of tasks, if any, or creates a new file in the desired location.
     *
     * @return List of tasks.
     * @throws DukeException If unable to create new file or read from an existing file.
     */
    public LinkedList<Task> load() throws DukeException {
        LinkedList<Task> tasks = new LinkedList<>();

        File file = new File(filePath);
        file.getParentFile().mkdirs();

        if (file.exists()) {
            try {
                tasks = readData(file, tasks);
            } catch (FileNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return tasks;

    }

    private LinkedList<Task> readData(File file, LinkedList<Task> tasks) throws DukeException, FileNotFoundException {
        Scanner readFile = new Scanner(file);
        while (readFile.hasNext()) {
            String nextTask = readFile.nextLine();
            String[] details = nextTask.split(Pattern.quote(" | "));
            assert details.length == 4 || details.length == 5;

            String type = details[0];
            int freq = Integer.parseInt(details[3]);
            Task task = new Task("dummy task");

            if (type.equals("T")) {
                task = new Todo(details[2], freq);
            } else if (type.equals("D")) {
                task = new Deadline(details[2], details[4], freq);
            } else if (type.equals("E")) {
                task = new Event(details[2], details[4], freq);
            } else {
                assert false;
            }

            if (details[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Writes tasks in the taskList to a file in the hard drive that can be retrieved by the user when they use the bot
     * again.
     *
     * @param taskList List of tasks.
     * @throws IOException If unable to write updated taskList to the file.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        LinkedList<Task> tasks = taskList.getTasks();
        FileWriter fw = new FileWriter(filePath);
        String toWrite = "";

        int lastTask = tasks.size() - 1;
        for (int i = 0; i < tasks.size(); i++) {
            toWrite += tasks.get(i).toSave();
            if (i != lastTask) {
                toWrite += "\n";
            }
        }
        fw.write(toWrite);
        fw.close();
    }

}




