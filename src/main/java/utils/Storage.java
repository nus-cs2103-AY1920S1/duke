package utils;

import exceptions.DukeException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private List<Task> tasks;
    private File file;
    private String filePath;

    /**
     * Enable read and write access to retrieve and store tasks.
     *
     * @param filePath the local path to storage file
     */
    public Storage(String filePath) throws DukeException {
        assert !filePath.isEmpty() : "File path not specified!";
        this.filePath = filePath;
        this.file = new File(filePath);

        File directory = new File(String.valueOf(Path.of(filePath).getParent()));
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating a file");
            }
        }

        this.tasks = new ArrayList<>();
    }

    /**
     *  Takes the stored data and populates the task list.
     *
     * @return list of tasks
     * @throws DukeException if local file not found or invalid date in saved file
     */
    public List<Task> readFromFile() throws DukeException {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // parse input and create tasks
            while ((line = bufferedReader.readLine()) != null) {

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
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error creating and reading from file!");
        }
    }

    /**
     * Create a todo task found in the file.
     * Adds the  task to the list of tasks.
     *
     * @param taskDetails an array containing details of the task
     */
    private void createAndAddTodo(String[] taskDetails) throws DukeException {
        String done = taskDetails[1];
        String name = taskDetails[2];
        Task todo = new Todo(name);
        if (done.equals("1")) {
            todo = todo.markAsDone();
        }
        tasks.add(todo);
    }

    /**
     * Create a deadline task found in the file.
     * Adds the  task to the list of tasks.
     *
     * @param taskDetails an array containing details of the task
     */
    private void createAndAddDeadline(String[] taskDetails) throws DukeException {
        String done = taskDetails[1];
        String name = taskDetails[2];
        String time = taskDetails[3];
        Task deadline = new Deadline(name, new StringToDate(time));
        if (done.equals("1")) {
            deadline = deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    /**
     * Create a event task found in the file.
     * Adds the  task to the list of tasks.
     *
     * @param taskDetails an array containing details of the task
     */
    private void createAndAddEvent(String[] taskDetails) throws DukeException {
        String done = taskDetails[1];
        String name = taskDetails[2];
        String time = taskDetails[3];
        Task event = new Event(name, new StringToDate(time));
        if (done.equals("1")) {
            event = event.markAsDone();
        }
        tasks.add(event);
    }

    /**
     * Takes the current list of task objects and adds them in the correct format to the data file.
     *
     * @throws DukeException in case of error writing to file
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fw != null : "Error writing to file, file writer not initialised!";
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                if (i != taskList.getSize() - 1) {
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