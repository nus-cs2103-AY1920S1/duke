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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public LinkedList<Task> load() throws DukeException {
        LinkedList<Task> taskList = new LinkedList<>();

        File file = new File(filePath);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        } else {
            try {
                taskList = readData(file, taskList);
            } catch (FileNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return taskList;

    }

    private LinkedList<Task> readData(File file, LinkedList<Task> tasks) throws DukeException, FileNotFoundException {
        Scanner readFile = new Scanner(file);
        while (readFile.hasNext()) {
            String nextTask = readFile.nextLine();
            String[] details = nextTask.split(Pattern.quote(" | "));
            String type = details[0];
            Task task;

            if (type.equals("T")) {
                task = new Todo(details[2]);
            } else if (type.equals("D")) {
                task = new Deadline(details[2], details[3]);
            } else {
                task = new Event(details[2], details[3]);
            }

            if (details[1].equals("1")) task.markAsDone();
            tasks.add(task);
        }

        return tasks;
    }

    public void writeToFile(TaskList taskList) throws IOException {
        LinkedList<Task> tasks = taskList.getTasks();
        FileWriter fw = new FileWriter(filePath);
        String toWrite = "";
        for (int i = 0; i < tasks.size(); i++) {
            toWrite += tasks.get(i).toSave();
            if (i != tasks.size() - 1) toWrite += "\n";
        }
        fw.write(toWrite);
        fw.close();
    }

}




