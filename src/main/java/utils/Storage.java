package utils;

import exceptions.DukeException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.text.ParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private List<Task> tasks;
    private File file;
    private String filePath;

    /**
     * Enable read and write access to retrieve and store tasks.
     *
     * @param filePath the local path to storage file
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "File path not specified!";
        this.filePath = filePath;
        this.file = new File(filePath);
        this.tasks = new ArrayList<>();
    }

    /**
     *  Takes the stored data and populates the task list.
     *
     * @return list of tasks
     * @throws DukeException if local file not found or invalid date in saved file
     */
    public List<Task> readFromFile() throws DukeException {
        Scanner sc;
        try {
            if (file.createNewFile()) {
                System.out.println("File created!");
            } else {
                System.out.println("File already exists");
            }
            sc = new Scanner(file);
        } catch (IOException e) {
            throw new DukeException("File cannot be created, please try again soon!");
        }
        String name;
        String time;
        String done;

        // parse input and create tasks
        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();

                // since task details are separated by | when saved
                // refer to printForStorage method in Task component
                String[] savedTask = line.split("\\|");

                for (int i = 0; i < savedTask.length; i++) {
                    savedTask[i] = savedTask[i].trim();
                }

                String taskType = savedTask[0];

                switch (taskType) {
                case "T":
                    createAndAddTodo(savedTask);
                    break;

                case "D":
                    createAndAddDeadline(savedTask);
                    break;

                case "E":
                    createAndAddEvent(savedTask);
                    break;

                default:
                    break;
                }
            } catch (ParseException e) {
                throw new DukeException("Format of date or time in tasks "
                        + "saved in file is invalid!");
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Create a todo task found in the file.
     * Adds the  task to the list of tasks.
     *
     * @param taskDetails an array containing details of the task
     */
    private void createAndAddTodo(String[] taskDetails) {
        String done = taskDetails[1];
        String name = taskDetails[2];
        Task todo = new Todo(name);
        if (done.equals("1")) {
            todo.markAsDone();
        }
        tasks.add(todo);
    }

    /**
     * Create a deadline task found in the file.
     * Adds the  task to the list of tasks.
     *
     * @param taskDetails an array containing details of the task
     */
    private void createAndAddDeadline(String[] taskDetails) throws ParseException, DukeException {
        String done = taskDetails[1];
        String name = taskDetails[2];
        String time = taskDetails[3];
        Task deadline = new Deadline(name, new StringToDate(time));
        if (done.equals("1")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    /**
     * Create a event task found in the file.
     * Adds the  task to the list of tasks.
     *
     * @param taskDetails an array containing details of the task
     */
    private void createAndAddEvent(String[] taskDetails) throws ParseException, DukeException {
        String done = taskDetails[1];
        String name = taskDetails[2];
        String time = taskDetails[3];
        Task event = new Event(name, new StringToDate(time));
        if (done.equals("1")) {
            event.markAsDone();
        }
        tasks.add(event);
    }

    /**
     * Takes the current list of task objects and adds them in the correct format to the data file.
     *
     * @throws DukeException in case of error writing to file
     */
    public void writeToFile() throws DukeException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fw != null : "Error writing to file, file writer not initialised!";
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