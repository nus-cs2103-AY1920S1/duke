package storage;

import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * load the history of Duke.
     *
     * @return return an arraylist of tasks
     * @throws DukeException when loading encounters exception
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splits = line.split("[|]");
                int completed = Integer.parseInt(splits[1].substring(1, 2));
                Task newTask;
                switch (splits[0]) {
                case "T ":
                    newTask = new Todo(splits[2].substring(1));
                    break;
                case "E ":
                    newTask = new Event(splits[2].substring(1, splits[2].length() - 1), splits[3].substring(1));
                    break;
                case "D ":
                    newTask = new Deadline(splits[2].substring(1, splits[2].length() - 1), splits[3].substring(1));
                    break;
                default:
                    newTask = new Task("");
                }
                if (completed == 1) {
                    newTask.changeStatus();
                }
                list.add(newTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException();
        }
        return list;
    }

    /**
     * save the tasks into a .txt file.
     *
     * @param list a list of tasks
     */
    public void save(ArrayList<Task> list) throws DukeException {
        try {
            File myFile = new File(filePath);
            if (!myFile.getParentFile().exists()) {
                myFile.getParentFile().mkdirs();
            }
            //FileWriter: the file's parent directory must exist
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (Task task : list) {
                if (!textToAdd.equals("")) {
                    textToAdd = textToAdd.concat("\n");
                }
                textToAdd = textToAdd.concat(task.writer());
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Tasks are not able to be saved into .txt file.");
        }
    }
}
