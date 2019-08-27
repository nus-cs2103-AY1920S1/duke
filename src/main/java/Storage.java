import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Storage {
    private static final File file = new File("data/duke.txt");

    public static ArrayList<Task> getTasks() throws FileNotFoundException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String saveString = sc.nextLine();
            String[] stringComponents =  saveString.split(Pattern.quote(" | "));
            String taskType = stringComponents[0];

            switch (taskType) {
            case "T":
                Todo todo = new Todo(stringComponents[2]);
                if (stringComponents[1].equals("1")) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(stringComponents[2], stringComponents[3]);
                if (stringComponents[1].equals("1")) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(stringComponents[2], stringComponents[3]);
                if (stringComponents[1].equals("1")) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                break;
            }
        }

        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder fileStringBuilder = new StringBuilder();

            for (Task task : tasks) {
                fileStringBuilder.append(task.getSaveString());
                fileStringBuilder.append(System.lineSeparator());
            }

            fw.write(fileStringBuilder.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
