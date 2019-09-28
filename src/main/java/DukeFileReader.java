import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a File Reader that reads data from the save file.
 */
public class DukeFileReader {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Returns the Task data present in the file path in the form of an arrayList.
     *
     * @param filePath the filepath to obtain data from
     * @return an arrayList that contains task data from the file path
     * @throws FileNotFoundException The exception when a file is not found.
     */
    public static ArrayList<Task> getData(String filePath) throws FileNotFoundException {
        assert !filePath.equals("") : "filePath should not be empty";
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineSplit = line.split("\\|");
            String eventType = lineSplit[0].trim();
            switch (eventType) {
            case "todo":
                tasks.add(new ToDoTask(lineSplit[1].trim(), lineSplit[2].trim()));
                break;
            case "event":
                tasks.add(new EventsTask(lineSplit[1].trim(), lineSplit[2].trim(),
                        lineSplit[3].trim()));
                break;
            case "deadline":
                tasks.add(new DeadlineTask(lineSplit[1].trim(), lineSplit[2].trim(),
                        lineSplit[3].trim()));
                break;
            default:
                throw new FileErrorDukeException(file.getAbsolutePath());
            }
        }
        return tasks;
    }

}

