package seedu.duke;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class Storage {

    String directory;
    FileReader fileReader;
    FileWriter fileWriter;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;


    protected Storage(String dir) {
        directory = dir;
    }

    public ArrayList<Task> load() {
        try {
            fileReader = new FileReader(directory);
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        }
        bufferedReader = new BufferedReader(fileReader);
        String[] tokens;

        String nextLine;
        try {
            nextLine = bufferedReader.readLine();
        } catch (IOException e) {
            nextLine = null;
        }
        ArrayList<Task> taskList = new ArrayList<Task>();

        // Savefile layout
        // Event type|done or not done(1 or 0)|Description|Date(if applicable)
        while (nextLine != null) {
            tokens = nextLine.split("\\|");

            switch (tokens[0]) {
            case "T":
                Todo todo = new Todo(tokens[2], tokens[1]);
                taskList.add(todo);
                break;

            case "D":
                Deadline deadline;
                try {
                    deadline = new Deadline(tokens[2], tokens[1], tokens[3]);
                } catch (DukeException e) {
                    e.printMessage();
                    break;
                }
                taskList.add(deadline);
                break;

            case "E":
                Event event;
                try {
                    event = new Event(tokens[2], tokens[1], tokens[3]);
                } catch (DukeException e) {
                    e.printMessage();
                    break;
                }
                taskList.add(event);
                break;

            default:
                PrettyPrint.print("Error reading task from save file.\n");
            }
            try {
                nextLine = bufferedReader.readLine();
            } catch (IOException e) {
                nextLine = null;
            }
        }

        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws DukeSaveFailedException {
        try {
            fileWriter = new FileWriter(directory);
        } catch (IOException e) {
            throw new DukeSaveFailedException();
        }
        bufferedWriter = new BufferedWriter(fileWriter);

        for (int z = 0; z < taskList.size(); z++) {
            try {
                bufferedWriter.write(taskList.get(z).toStorageString());
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                throw new DukeSaveFailedException();
            }
        }
    }

}
