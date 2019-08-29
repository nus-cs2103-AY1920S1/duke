import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private File f;

    public Storage(String filepath) {
        this.f = new File(filepath);
    }

    public void saveToFile(ArrayList<Task> allTasksForStorage) throws IOException {
        Files.deleteIfExists(Paths.get(Duke.saveFilePath));
        FileWriter fw = new FileWriter(Duke.saveFilePath, true);

        for (Task t : allTasksForStorage) {
            fw.write(t.getStorageFormat() + "\n");
        }

        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.f);
        ArrayList<Task> allStoredTasks = new ArrayList<Task>();
        while (sc.hasNext()) {
            Task t = generateSavedTask(sc.nextLine());
            allStoredTasks.add(t);
        }

        return allStoredTasks;
    }

    private Task generateSavedTask(String nextLine) {
        String[] s = nextLine.split("\\|");
        String command = s[0].trim();
        Task t = new Task("Uninitialised Task");

        //These 2 attributes are consistent across all 3 Task types (ToDo, Deadline, Event)
        boolean isDone = s[1].trim().equals("1") ? true : false;
        String description = s[2].trim();

        switch (command) {
        case "T":
            t = new ToDo(description);
            break;
        case "E":
            String duration = s[3].trim();
            t = new Event(description, duration);
            break;
        case "D":
            String deadline = s[3].trim();
            t = new Deadline(description, deadline);
            break;
        }

        if (isDone) {
            t.markAsDone();
        }

        return t;
    }

    public static void main(String[] args) {
        try {
            Storage store = new Storage(Duke.saveFilePath);
            ArrayList<Task> allStoredTasks = store.load();
            for (Task t : allStoredTasks) {
                System.out.println(t);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }

        try {
            Task t1 = new ToDo("Return Library Books");
            t1.markAsDone();
            Task t2 = new Event("Project Meeting", "Sunday 2-4pm");
            Task t3 = new Deadline("Complete Project Work", "Sunday 3pm");
            ArrayList<Task> allTasks = new ArrayList<>();
            allTasks.add(t1);
            allTasks.add(t2);
            allTasks.add(t3);
            Storage s = new Storage(Duke.saveFilePath);
            s.saveToFile(allTasks);
        }
        catch (IOException e) {
            System.out.println("Problem!");
        }


    }

}
