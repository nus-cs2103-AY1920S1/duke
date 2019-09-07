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
                    loadTodo(tasks, isDone, description);
                } else if (type.equals("E")) {
                    loadEvent(tasks, information, isDone, description);
                } else {
                    throw new StorageException("☹ OOPS!!! Something must have gone wrong during storage.");
                }
            }
            return tasks;
        } catch (FileNotFoundException | InvalidDateException | InvalidTimeException exception) {
            throw new StorageException("☹ OOPS!!! Something must have gone wrong during storage.");
        }
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
        Event event = new Event(description, new Date(startDate), new Time(startTime), new Date(endDate),
                new Time(endTime));
        if (isDone) {
            event.setDone();
        }
        tasks.add(event);
    }

    private void loadTodo(ArrayList<Task> tasks, boolean isDone, String description) {
        Todo todo = new Todo(description);
        if (isDone) {
            todo.setDone();
        }
        tasks.add(todo);
    }

    private void loadDeadline(ArrayList<Task> tasks, String[] information,
                              boolean isDone, String description) throws InvalidDateException, InvalidTimeException {
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
        tasks.add(deadline);
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
                StringBuilder taskInformation = new StringBuilder();
                if (type.equals("D")) {
                    writeDeadline((Deadline) task, type, isDone, description, taskInformation);
                } else if (type.equals("T")) {
                    writeTodo(type, isDone, description, taskInformation);
                } else if (type.equals("E")) {
                    writeEvent((Event) task, type, isDone, description, taskInformation);
                }
                fileWriter.write(taskInformation.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            throw new StorageException("☹ OOPS!!! Something went wrong :" + exception.getMessage());
        }
    }

    private void writeEvent(Event task, String type, int isDone, String description, StringBuilder taskInformation) {
        Event event = task;
        String startDate = event.getUnprocessedStartDate();
        String startTime = event.getUnprocessedStartTime();
        String endDate = event.getUnprocessedEndDate();
        String endTime = event.getUnprocessedEndTime();
        taskInformation.append(type + " | " + isDone + " | " + description + " | ");
        taskInformation.append(startDate + " | " + startTime + " | " + endDate + " | " + endTime);
    }

    private void writeTodo(String type, int isDone, String description, StringBuilder taskInformation) {
        taskInformation.append(type + " | " + isDone + " | " + description);
    }

    private void writeDeadline(Deadline task, String type, int isDone,
                               String description, StringBuilder taskInformation) {
        Deadline deadline = task;
        String date = deadline.getUnprocessedDate();
        String time = deadline.getUnprocessedTime();
        taskInformation.append(type + " | " + isDone + " | " + description + " | " + date + " | " + time);
    }
}
