package duke.storage;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

import duke.dukeexception.DukeSaveFileCorruptedError;
import duke.task.Task;
import duke.parser.Parser;

public class Storage {

    private String saveFilePath;

    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public ArrayList<Task> load() throws DukeSaveFileCorruptedError {
        try {
            FileReader tasksFileReader = new FileReader(new File(saveFilePath));
            BufferedReader bTasksFileReader = new BufferedReader(tasksFileReader);
            return readTasksFile(bTasksFileReader);
        } catch (FileNotFoundException e) {
            createNewSaveFile();
        } catch (DukeSaveFileCorruptedError e) {
            createNewSaveFile();
        }
        return new ArrayList<Task>();
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            new File(saveFilePath).delete();
            createNewSaveFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createNewSaveFile() {
        try {
            File newSaveFile = new File(saveFilePath);
            newSaveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Task> readTasksFile(BufferedReader bTasksFileReader) throws DukeSaveFileCorruptedError {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String newTaskString = null;
        while (true) {
            try {
                newTaskString = bTasksFileReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (newTaskString == null) {
                break;
            }
            Task newTask = Parser.parseSaveData(newTaskString);
            tasks.add(newTask);
        }
        try {
            bTasksFileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}