package duke;

import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.InvalidDateException;
import duke.exception.InvalidTimeException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage in the hard disk.
 * A <code>Storage</code> object corresponds to a specific file in the hard disk that stores the information
 * of the corresponding <code>TaskList</code> object.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for <code>Storage</code>.
     * @param filePath File path of the file stored in the hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task information from the hard disk.
     * @return A list of <code>Task</code> objects that can be used to create a new <code>TaskList</code> object.
     * @throws duke.exception.StorageException If the file in the hard disk cannot be found.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String taskInformation = fileReader.nextLine();
                String[] information = taskInformation.split(" \\| ");
                String type = getType(information);
                boolean isDone = getDoneStatus(information);
                String description = getDescription(information);
                if (type.equals("D")) {
                    loadDeadline(tasks, information, isDone, description);
                } else if (type.equals("T")) {
                    loadTodo(tasks, information, isDone, description);
                } else if (type.equals("E")) {
                    loadEvent(tasks, information, isDone, description);
                } else {
                    throw new StorageException("OOPS!!! Something must have gone wrong during storage.");
                }
            }
            return tasks;
        } catch (FileNotFoundException exception) {
            throw new StorageException("OOPS!!! Please specify a valid file path to store your list data.");
        } catch (InvalidDateException | InvalidTimeException exception) {
            throw new StorageException("OOPS!!! Something must have gone wrong during storage.");
        } catch (IOException exception) {
            throw new StorageException("OOPS!!! Something went wrong: " + exception.getMessage());
        }
    }

    private ArrayList<String> getTags(String tagsAsStrings) {
        boolean hasNoTags = tagsAsStrings.length() == 0;
        if (hasNoTags) {
            return new ArrayList<String>();
        }
        String[] individualTags = tagsAsStrings.split("#");
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 1; i < individualTags.length; i++) {
            tags.add(individualTags[i].trim());
        }
        return tags;
    }

    private String getType(String[] information) {
        return information[0];
    }

    private boolean getDoneStatus(String[] information) {
        return Integer.parseInt(information[1]) == 1;
    }

    private String getDescription(String[] information) {
        return information[2];
    }

    private void loadEvent(ArrayList<Task> tasks, String[] information,
                           boolean isDone, String description) throws InvalidDateException, InvalidTimeException {
        String startDate = information[3];
        String startTime = information.length > 4 ? information[4] : "";
        String endDate = information.length > 5 ? information[5] : "";
        String endTime = information.length > 6 ? information[6] : "";
        String tagsAsStrings = information.length > 7 ? information[7].trim() : "";
        ArrayList<String> tags = getTags(tagsAsStrings);
        Event event = new Event(description, new Date(startDate), new Time(startTime), new Date(endDate),
                new Time(endTime));
        addTags(event, tags);
        if (isDone) {
            event.setDone();
        }
        tasks.add(event);
    }

    private void loadTodo(ArrayList<Task> tasks, String[] information, boolean isDone, String description) {
        Todo todo = new Todo(description);
        if (isDone) {
            todo.setDone();
        }
        String tagsAsStrings = information.length > 3 ? information[3].trim() : "";
        ArrayList<String> tags = getTags(tagsAsStrings);
        addTags(todo, tags);
        tasks.add(todo);
    }

    private void loadDeadline(ArrayList<Task> tasks, String[] information,
                              boolean isDone, String description)
            throws InvalidDateException, InvalidTimeException {
        String date = information[3];
        String time = "";
        boolean hasTimeDetails = information.length == 5;
        if (hasTimeDetails) {
            time = information[4];
        }
        Deadline deadline = new Deadline(description, new Date(date), new Time(time));
        if (isDone) {
            deadline.setDone();
        }
        String tagsAsStrings = information.length > 5 ? information[5].trim() : "";
        ArrayList<String> tags = getTags(tagsAsStrings);
        addTags(deadline, tags);
        tasks.add(deadline);
    }

    private void addTags(Task task, ArrayList<String> tags) {
        for (int i = 0; i < tags.size(); i++) {
            task.addTag(tags.get(i));
        }
    }

    /**
     * Writes the information of the <code>Task</code> objects in a <code>TaskList</code> to the file in the hard disk.
     * @param tasks Tasks that have been updated through various commands.
     * @throws StorageException If information cannot be written to the file.
     */
    public void writeToHardDisk(TaskList tasks) throws StorageException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            ArrayList<Task> taskList = tasks.getList();
            for (int i = 0; i < tasks.getListSize(); i++) {
                Task task = taskList.get(i);
                String type = task.getType();
                int isDone = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String tags = task.getTagsAsStrings();
                StringBuilder taskInformation = new StringBuilder();
                if (type.equals("D")) {
                    writeDeadline((Deadline) task, type, isDone, description, taskInformation, tags);
                } else if (type.equals("T")) {
                    writeTodo(type, isDone, description, taskInformation, tags);
                } else if (type.equals("E")) {
                    writeEvent((Event) task, type, isDone, description, taskInformation, tags);
                }
                fileWriter.write(taskInformation.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            throw new StorageException("OOPS!!! Something went wrong :" + exception.getMessage());
        }
    }

    private void writeEvent(Event task, String type, int isDone, String description, StringBuilder taskInformation,
                            String tags) {
        Event event = task;
        String startDate = event.getUnprocessedStartDate();
        String startTime = event.getUnprocessedStartTime();
        String endDate = event.getUnprocessedEndDate();
        String endTime = event.getUnprocessedEndTime();
        taskInformation.append(type + " | " + isDone + " | " + description + " | ");
        taskInformation.append(startDate + " | " + startTime + " | " + endDate + " | " + endTime + " | " + tags);
    }

    private void writeTodo(String type, int isDone, String description, StringBuilder taskInformation, String tags) {
        taskInformation.append(type + " | " + isDone + " | " + description + " | " + tags);
    }

    private void writeDeadline(Deadline task, String type, int isDone,
                               String description, StringBuilder taskInformation, String tags) {
        Deadline deadline = task;
        String date = deadline.getUnprocessedDate();
        String time = deadline.getUnprocessedTime();
        taskInformation.append(type + " | " + isDone + " | " + description + " | " + date + " | " + time + " | "
                + tags);
    }
}
