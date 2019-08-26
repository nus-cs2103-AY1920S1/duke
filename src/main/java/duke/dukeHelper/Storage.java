package duke.dukeHelper;

import duke.dukeTask.Deadline;
import duke.dukeTask.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File listFile = new File(filePath);
        listFile.createNewFile(); // Directory data needs to exist already

        Scanner fileScanner = new Scanner(listFile);
        while (fileScanner.hasNext()) {
            String type = fileScanner.next();
            int isDone = Integer.parseInt(fileScanner.next());
            String remaining = fileScanner.nextLine().trim();
            String[] remainingSplit = remaining.split(" \\| ");
            switch (type) {
            case "T":
                tasks.add(new ToDo(remaining, isDone));
                break;
            case "D":
                tasks.add(new Deadline(remainingSplit[0], isDone, remainingSplit[1]));
                break;
            case "E":
                tasks.add(new Event(remainingSplit[0], isDone, remainingSplit[1]));
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    public void writeToFile(String filePath, String textToAdd, boolean isAppend) {
        try {
            FileWriter fw = isAppend ? new FileWriter(filePath, true) : new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException ioe) {
            new Ui().printError("Failed to save changes to file. Please try again.");
        }
    }

    public void overwriteFile(ArrayList<Task> listOfTasks) {
        String saveToFile = "";
        for (Task t : listOfTasks) {
            saveToFile += String.format("%s %d %s", t.getType(), t.getIsDone(), t.getDescription());
            if ("D".equals(t.getType())) {
                saveToFile += " | " + ((Deadline) t).getEndTime();
            } else if ("E".equals(t.getType())) {
                saveToFile += " | " + ((Event) t).getEventPeriod();
            }
            saveToFile += "\n";
        }
        writeToFile(filePath, saveToFile, false);
    }
}
