package storage;

import model.Task;
import model.Tasklist;
import model.deadline;
import model.event;
import model.todo;

import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    private final Path filePath;
    private final String DELIMITER = "//";

    /**
     * Creates an instance of Storage to save and load data into the filepath
     *
     * @param filePath path of which data is saved and loaded from
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates and instance of Storage with no filepath
     */
    public Storage() {
        this.filePath = null;
    }

    /**
     * Loads data from the filepath and outputs the TaskList of Tasks
     *
     * @return a TaskList of Tasks
     */
    public Tasklist load() {
        return parseFromFile(this.filePath);
    }

    /**
     * Saves the current TaskList into the filepath
     *
     * @param tasks TaskList to be saved
     */
    public void save(Tasklist tasks) {
        try {
            serializeToFile(filePath, tasks);
        } catch (Exception E) {
            System.out.println("Failed to save file");
        }
    }

    /**
     * Attempts to read from filepath and returns it as a TaskList.
     * It returns an empty TaskList if no filepath is specified or filepath is inaccessible
     *
     * @param filepath filepath in which it is loaded from
     * @return Resulting TaskList
     */
    public Tasklist parseFromFile(Path filepath) {
        Tasklist tasks = new Tasklist();
        try {
            Reader reader = Files.newBufferedReader(filepath);
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                tasks.add(parseLine(sc.nextLine()));
            }
        } catch (Exception E) {
            System.out.println("Error in parsing file! Returning empty TaskList");
        }
        return tasks;
    }

    /**
     * Parses the command and returns the resulting Task
     *
     * @param line input command to be parsed
     * @return resulting Task
     */
    public Task parseLine(String line) {
        assert line != null: "input string cannot be null";
        
        String[] sp = line.split(DELIMITER);

        Class<? extends Task> taskType = parseTaskType(sp[0]);
        String taskDescription = parseTaskDescription(sp[1]);
        boolean taskIsDone = parseTaskIsDone(sp[2]);

        Task task = null;
        if (taskType.equals(todo.class)) {
            task = new todo(taskDescription, taskIsDone);
        } else if (taskType.equals(deadline.class)) {
            String taskDetails = parseTaskDetails(sp[3]);
            task = new deadline(taskDescription, taskIsDone, taskDetails);
        } else if (taskType.equals(event.class)) {
            String taskDetails = parseTaskDetails(sp[3]);
            task = new event(taskDescription, taskIsDone, taskDetails);
        }
        return task;
    }

    /**
     * Parses a string and outputs the corresponding task type
     *
     * @param s string to be parsed into the corresponding task type
     * @return resulting task type
     */
    private Class<? extends Task> parseTaskType(String s) {
        if (s.equals("T")) {
            return todo.class;
        } else if (s.equals("D")) {
            return deadline.class;
        } else if (s.equals("E")) {
            return event.class;
        } else {
            System.out.println("Error in resolving task type");
            return null;
        }
    }

    /**
     * Parses a string and outputs the corresponding task description
     *
     * @param s string to be parsed into the corresponding task description
     * @return resulting task description
     */
    private String parseTaskDescription(String s) {
        return s;
    }

    /**
     * Parses a string and outputs the corresponding task isDone
     *
     * @param s string to be parsed into the corresponding task isDone
     * @return resulting task isDone
     */
    private boolean parseTaskIsDone(String s) {
        if (s.equals("Y")) {
            return true;
        } else if (s.equals("N")) {
            return false;
        } else {
            System.out.println("Error in resolving task done");
            return false;
        }
    }

    /**
     * Parses a string and outputs the corresponding task details
     *
     * @param s string to be parsed into the corresponding task details
     * @return resulting task details
     */
    private String parseTaskDetails(String s) {
        if (s.equals("null")) {
            return null;
        } else {
            return s;
        }
    }

    /**
     * writes data into the file
     *
     * @param filePath filepath to write to
     * @param tasks    tasks to be written to filepath
     */
    private void serializeToFile(Path filePath, Tasklist tasks) {
        try {
            PrintWriter writer = new PrintWriter(filePath.toFile(), "UTF-8");
            int i;
            for (i = 0; i < tasks.size(); i++) {
                writer.write(serializeLine(tasks.get(i)));
                writer.println();
            }
            writer.close();
        } catch (Exception E) {
            System.out.println("Unable to write to file");
        }
    }

    /**
     * Formats and serializes a task into a String
     *
     * @param task task to be formatted to a String
     * @return a String that repersents the Task
     */
    private String serializeLine(Task task) {
        StringJoiner sj = new StringJoiner(DELIMITER);

        String taskType = serializeTaskType(task);
        sj.add(taskType);

        String taskDescription = serializeTaskDescription(task);
        sj.add(taskDescription);

        String taskIsDone = serializeTaskIsDone(task);
        sj.add(taskIsDone);

        if (taskType.equals("D")) {
            String taskDetails = serializeTaskDetails(task);
            sj.add(taskDetails);
        } else if (taskType.equals("E")) {
            String taskDetails = serializeTaskDetails(task);
            sj.add(taskDetails);
        }
        return sj.toString();
    }

    /**
     * Serializes the Task type into a String
     *
     * @param task Task to be serialized
     * @return resulting String
     */
    private String serializeTaskType(Task task) {
        if (task instanceof todo) {
            return "T";
        } else if (task instanceof deadline) {
            return "D";
        } else if (task instanceof event) {
            return "E";
        } else {
            return null;
        }
    }

    /**
     * Serializes the Task isDone into a String
     *
     * @param task Task to be serialized
     * @return resulting String
     */
    private String serializeTaskIsDone(Task task) {
        if (task.isDone()) {
            return "Y";
        } else if (!task.isDone()) {
            return "N";
        } else {
            return "X";
        }
    }

    /**
     * Serializes the Task description into a String
     *
     * @param task Task to be serialized
     * @return resulting String
     */
    private String serializeTaskDescription(Task task) {
        return task.getDescription();
    }

    /**
     * Serializes the Task details into a String
     *
     * @param task Task to be serialized
     * @return resulting String
     */
    private String serializeTaskDetails(Task task) {
        return task.getDetails();
    }


}
