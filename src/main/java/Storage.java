import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private String saveFilePath;

    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        this.readSaveData();
    }

    protected ArrayList<Task> getTasks() {
        return this.tasks;
    }

    protected int getTaskCount() {
        return this.tasks.size();
    }

    protected boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    private void readSaveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.saveFilePath));

            String rawTask = reader.readLine();
            while (rawTask != null) {
                String[] tokens = rawTask.split(" \\| ");
                String taskType = tokens[0];
                boolean isDone = tokens[1].equals("1");

                Task task;

                switch (taskType) {
                case "T": {
                    task = new Todo(tokens[2], isDone);
                    this.addTask(task);
                    break;
                }

                case "D": {
                    task = new Deadline(tokens[2], isDone, tokens[3]);
                    this.addTask(task);
                    break;
                }

                case "E": {
                    task = new Event(tokens[2], isDone, tokens[3]);
                    this.addTask(task);
                    break;
                }

                default: {
                    System.out.println("Error: Invalid task type.");
                }
                }

                rawTask = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read save file.");
        }
    }

    private String getSaveData() {
        StringBuilder saveData = new StringBuilder();

        for (Task t : this.tasks) {
            saveData.append(String.format("%s%n", t.toSaveFormat()));
        }

        return saveData.toString();
    }

    public void writeSaveData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.saveFilePath));
            String saveData = this.getSaveData();
            writer.write(saveData);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to write to save file.");
        }
    }
}
