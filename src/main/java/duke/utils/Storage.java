package duke.utils;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File f;

    public Storage(String filepath) {
        this.f = new File(filepath);
    }

    public void save(TaskList allTasks) throws DukeException {
        try {
            Files.deleteIfExists(Paths.get(Duke.saveFilePath));
            FileWriter fw = new FileWriter(Duke.saveFilePath, true);

            ArrayList<Task> allTasksArrList = allTasks.getArrayList();
            for (Task t : allTasksArrList) {
                fw.write(t.getStorageFormat() + "\n");
            }

            fw.close();
        }
        catch (IOException e) {
            throw new DukeException("Could not save to file!");
        }

    }

    public TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.f);
            ArrayList<Task> allStoredTasks = new ArrayList<Task>();
            while (sc.hasNext()) {
                Task t = generateSavedTask(sc.nextLine());
                allStoredTasks.add(t);
            }

            return new TaskList(allStoredTasks);
        }
        catch (FileNotFoundException e) {
            throw new DukeException("No existing tasks found!");
        }
    }

    private Task generateSavedTask(String nextLine) throws DukeException {
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
            String startTime = s[3].trim();
            String endTime = s[4].trim();
            t = new Event(description, startTime, endTime);
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
}
