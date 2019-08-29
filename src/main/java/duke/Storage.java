package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {

    private Scanner sc;
    private String filePath;
    List<String> inputs = new ArrayList<>();
    private TaskList tasks = new TaskList();
    private File f;

    /**
     * Loads and writes into given file.
     * @param filePath String that indicates path to file.
     */
    Storage (String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
        try {
            if (!f.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Loads tasks from file into the program.
     * @return TaskList that will be used in the program for further modifications by user.
     */
    TaskList loadTasks() {
        int counter = 0;
        try {
            sc = new Scanner(f);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] details = task.split(" \\| ");
                int num = Integer.parseInt(details[1]);
                boolean done = false;
                done = (num == 1);
                Date date = null;
                Time time = null;
                counter++;
                try {
                    switch (details[0]) {
                    case "T":
                        tasks.loadTask(new Todo(counter, details[2], "T", done));
                        break;
                    case "D":
                        date = Date.processDate(details[3].split(" ")[0]);
                        time = Time.processTime(details[3].split(" ")[1]);
                        tasks.loadTask(new Deadline(counter, details[2], date, time, "D", done));
                        break;
                    case "E":
                        date = Date.processDate(details[3].split(" ")[0]);
                        time = Time.processTime(details[3].split(" ")[1]);
                        tasks.loadTask(new Event(counter, details[2], date, time, "E", done));
                        break;
                    default:
                        throw new InvalidInputException();
                    }
                } catch (InvalidInputException e) {
                    e.printError();
                }
            }
        } catch (IOException e) {
            System.out.println("file not detected");
        }
        return tasks;
    }

    /**
     * Writes formatted task list into file.
     * @param tasks Task list that has been processed and updated.
     * @throws IOException when there are input problems.
     */
    void updateTaskList(TaskList tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.taskList) {
                if (task != null) {
                    fw.write( task.fileFormat() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
