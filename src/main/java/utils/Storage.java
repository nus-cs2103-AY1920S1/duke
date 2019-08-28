package utils;

import exceptions.DukeException;
import tasks.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* Concerned with reading from and writing to the data file */
public class Storage {
    private List<Task> tasks;
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.tasks = new ArrayList<>();

    }

    /* Takes the current data and populates the task list */
    public List<Task> readFromFile() {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        String name = "";
        String time = "";
        String done = "";
        // parse input and create tasks
        assert sc != null;
        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();
                String[] savedTask = line.split("\\|");

                for (int i = 0; i < savedTask.length; i++) {
                    savedTask[i] = savedTask[i].trim();
                }

                switch (savedTask[0]) {
                    case "T":
                        name = savedTask[2];
                        done = savedTask[1];
                        Task todo = new Todo(name);
                        if (done.equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;

                    case "D":
                        name = savedTask[2];
                        done = savedTask[1];
                        time = savedTask[3];
                        Task deadline = new Deadline(name, new StringToDate(time));
                        if (done.equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;

                    case "E":
                        name = savedTask[2];
                        done = savedTask[1];
                        time = savedTask[3];
                        Task event = new Event(name, new StringToDate(time));
                        if (done.equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;

                    default:
                        break;
                }
            } catch (ParseException e) {
                System.out.print("Format of date when writing to file is incorrect!");
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Takes the current list of task objects and adds them in the correct format to the data file.
     */
    public void writeToFile() throws DukeException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fw != null;
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (i != tasks.size() - 1) {
                    fw.write(task.printForStorage() + "\n");
                } else {
                    fw.write(task.printForStorage());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Internal error, work may not be saved!");
        }
    }
}