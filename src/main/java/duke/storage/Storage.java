package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import duke.tag.Tag;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDoTask;

/**
 * Represents a Storage Class which loads and saves data to the output file.
 */
public class Storage {

    private BufferedReader reader;
    private FileWriter writer;

    /**
     * The path of the output file.
     */
    private String filepath;

    /**
     * The list of the tasks to be uploaded or saved.
     */
    private ArrayList<Task> tasks;

    /**
     * Creates a new Storage based on the filepath provided.
     * @param filepath the path of the file provided.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads back the Deadline Task based on the task data loaded from the file.
     * @param task the data from the file.
     */
    public void addBackDeadlineTask(String task) {
        String[] taskDetails = task.split(" / ");
        String completion = taskDetails[1];
        String nameOfTask = taskDetails[2];
        String deadline = taskDetails[3];
        String tag = taskDetails[4];

        if (completion.equals("Y")) {
            DeadlineTask theTask = new DeadlineTask(nameOfTask, true, deadline);
            theTask.pushTag(new Tag(tag));
            tasks.add(theTask);
        } else {
            DeadlineTask theTask = new DeadlineTask(nameOfTask, true, deadline);
            theTask.pushTag(new Tag(tag));
            tasks.add(theTask);
        }
    }

    /**
     * Loads back the Event Task based on the task data loaded from the file.
     * @param task the data from the file.
     */
    public void addBackEventTask(String task) {
        String[] taskDetails = task.split(" / ");
        String completion = taskDetails[1];
        String nameOfTask = taskDetails[2];
        String time = taskDetails[3];
        String tag = taskDetails[4];

        if (completion.equals("Y")) {
            EventTask theTask = new EventTask(nameOfTask, true, time);
            theTask.pushTag(new Tag(tag));
            tasks.add(theTask);
        } else {
            EventTask theTask = new EventTask(nameOfTask, false, time);
            theTask.pushTag(new Tag(tag));
            tasks.add(theTask);
        }
    }

    /**
     * Loads back the ToDo Task based on the task data loaded from the file.
     * @param task the data from the file.
     */
    public void addBackToDoTask(String task) {
        String[] taskDetails = task.split(" / ");
        String completion = taskDetails[1];
        String nameOfTask = taskDetails[2];
        String tag = taskDetails[3];

        if (completion.equals("Y")) {
            ToDoTask theTask = new ToDoTask(nameOfTask, true);
            theTask.pushTag(new Tag(tag));
            tasks.add(theTask);
        } else {
            ToDoTask theTask = new ToDoTask(nameOfTask, false);
            theTask.pushTag(new Tag(tag));
            tasks.add(theTask);
        }
    }

    /**
     * Checks if the storage file is found or not.
     * @return false is the storage file is not found.
     */
    public boolean hasStorage() {
        return new File(filepath).exists();
    }

    /**
     * Loads all the data from the output file.
     * @return A list of Tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String task;
            while ((task = reader.readLine()) != null) {
                if (task.startsWith("D")) {
                    addBackDeadlineTask(task);
                } else if (task.startsWith("E")) {
                    addBackEventTask(task);
                } else {
                    addBackToDoTask(task);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Please check if you have your data folder!");
        }
        return tasks;
    }

    /**
     * Saves all the tasks from the TaskList into the output file one by one.
     */
    public void save() {
        try {
            writer = new FileWriter(filepath);
            for (int i = 0; i < TaskList.getNumberOfTasks(); i++) {
                Task task = TaskList.getTask(i);
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    if (task.isCompleted) {
                        writer.write(String.format("D / Y / %s / %s / %s", task.todo,
                                deadlineTask.deadline, task.getTag().getTagName()));
                    } else {
                        writer.write(String.format("D / N / %s / %s / %s", task.todo,
                                deadlineTask.deadline, task.getTag().getTagName()));
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    if (task.isCompleted) {
                        writer.write(String.format("E / Y / %s / %s / %s", task.todo,
                                eventTask.time, task.getTag().getTagName()));
                    } else {
                        writer.write(String.format("E / N / %s / %s / %s", task.todo,
                                eventTask.time, task.getTag().getTagName()));
                    }
                } else {
                    ToDoTask todoTask = (ToDoTask) task;
                    if (task.isCompleted) {
                        writer.write(String.format("T / Y / %s / %s", task.todo,
                                task.getTag().getTagName()));
                    } else {
                        writer.write(String.format("T / N / %s / %s", task.todo,
                                task.getTag().getTagName()));
                    }
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the filepath of the storage.
     * @return The filepath of the storage.
     */
    public String getFilePath() {
        return this.filepath;
    }

}
