package softeng.duke;

import softeng.tasks.Task;
import softeng.tasks.Deadline;
import softeng.tasks.toDo;
import softeng.tasks.Event;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a statistics recorder of tasks done before.
 */

public class Statistics {
    private List<Task> tasksDone;

    public Statistics() {
        tasksDone = new LinkedList<>();
        Path file = Paths.get("../../stats.txt");
        try {
            Scanner fileSc = new Scanner(file).useDelimiter("\\||\\n");
            while (fileSc.hasNext()) {
                String line = fileSc.nextLine();
                String[] lineBreakUp = line.split(" \\| ");
                switch (lineBreakUp[0]) {
                    case "T":
                        tasksDone.add(new toDo(lineBreakUp[2], lineBreakUp[1].equals("1")));
                        break;
                    case "D":
                        tasksDone.add(new Deadline(lineBreakUp[2], lineBreakUp[3], lineBreakUp[1].equals("1")));
                        break;
                    case "E":
                        tasksDone.add(new Event(lineBreakUp[2], lineBreakUp[3], lineBreakUp[1].equals("1")));
                        break;
                    default:
                        System.out.println("wrong input from file");
                }
            }
        } catch (IOException exp) {
            System.out.println("ioException caught when loading file!");
        }
    }

    /**
     * Adds a done task into the records
     * @param task the newly done task to be added
     */
    public void addToDone(Task task) {
        tasksDone.add(task);
    }

    /**
     * Lists all the tasks done
     * @return a string containing all the tasks done
     */
    public String listDone(){
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks you have done:\n");
        for (Task t : tasksDone) {
            str.append(t.toString());
        }
        return str.toString();
    }

    /**
     * Saves the tasks done to disk
     */
    public void save() {
        List<String> lines = new LinkedList<>();
        for (Task t : tasksDone) {
            lines.add(t.toSave());
        }
        Path file = Paths.get("../../stats.txt");
        try {
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("error when saving stats");
        }
    }
}
