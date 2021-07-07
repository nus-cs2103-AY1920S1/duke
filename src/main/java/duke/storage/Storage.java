package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the storage class. It loads and writes into a txt file to keep the task list even when the program is terminated.
 */

public class Storage {

    private String filePath;
    private File file;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;

        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int indexToDelete) {
        tasks.remove(indexToDelete);
    }

    public void save() throws DukeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task: tasks) {
                String a = task.getType();
                String b = String.valueOf(task.isDone());
                String c = task.getDescription();
                writer.write(a + " " + b + " " + c + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new DukeException("Saving error");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            file = new File(filePath);
            file.createNewFile();
            System.out.println(file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            while ((str = br.readLine()) != null) {
                String[] words = str.split(" ");
                String type = words[0];
                String done = words[1];
                StringBuilder descriptions = new StringBuilder();
                Task task;
                switch (type) {
                    case "T":
                        for (int i = 2; i < words.length; i++) {
                            descriptions.append(words[i]);
                            descriptions.append(" ");
                        }
                        task = new ToDo(descriptions.toString().trim());
                        if (done.equals("true")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;

                    case "D":
                        StringBuilder by = new StringBuilder();
                        for (int i = 2; i < words.length; i++) {
                            String word = words[i];
                            if (!word.equals("/by")) {
                                descriptions.append(word);
                                descriptions.append(" ");
                            } else {
                                for (int j = i + 1; j < words.length; j++) {
                                    by.append(words[j]);
                                    by.append(" ");
                                }
                                break;
                            }
                        }
                        task = new Deadline(descriptions.toString().trim(), by.toString().trim());
                        if (done.equals("true")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;

                    case "E":
                        StringBuilder at = new StringBuilder();
                        for (int i = 2; i < words.length; i++) {
                            String word = words[i];
                            if (!word.equals("/at")) {
                                descriptions.append(word);
                                descriptions.append(" ");
                            } else {
                                for (int j = i + 1; j < words.length; j++) {
                                    at.append(words[j]);
                                    at.append(" ");
                                }
                                break;
                            }
                        }
                        task = new Event(descriptions.toString().trim(), at.toString().trim());
                        if (done.equals("true")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                }
            }

            return tasks;
        } catch (Exception e) {
            throw new DukeException("Error");
        }
    }
}