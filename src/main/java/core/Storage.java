package core;

import exception.DukeIoException;
import exception.DukeIllegalArgumentException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.TaskType;
import task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Storage {

    private Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Saves tasks into a file on hard disk.
     *
     * @param taskList TaskList to save.
     * @throws DukeIoException If file cannot be opened/modified.
     */
    public void save(TaskList taskList) throws DukeIoException {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            BufferedWriter writer = Files.newBufferedWriter(path);
            StringBuilder sb = new StringBuilder();
            ArrayList<Task> tasks = taskList.toList();

            // write tasks into file
            IntStream.range(0, tasks.size())
                .forEach(i -> {
                    Task task = tasks.get(i);
                    StringBuilder taskSb = encodeTaskToDataString(task);
                    sb.append(taskSb);
                });
            writer.write(sb.toString());

            writer.close();
        } catch (IOException e) {
            throw new DukeIoException("OOPS!!! Error trying to save data to file.");
        }
    }

    /**
     * Encode a task into our data string format.
     *
     * @param task Task to encode.
     * @return StringBuilder containing encoded task data.
     */
    private StringBuilder encodeTaskToDataString(Task task) {
        StringBuilder rowBuilder = new StringBuilder(
                String.format("%s|%s|%s", task.getType().getTag(),
                        task.isDone() ? "1" : "0", task.getDescription()));

        if (task instanceof Deadline) {
            rowBuilder.append(String.format("|%s",
                    ((Deadline) task).getByDateTime().format(DateTimeFormatter.ISO_DATE_TIME)));
            rowBuilder.append(String.format("|%s",
                    ((Deadline) task).isAllDay() ? "1" : "0"));
        } else if (task instanceof Event) {
            rowBuilder.append(String.format("|%s|%s",
                    ((Event) task).getStartDateTime().format(DateTimeFormatter.ISO_DATE_TIME),
                    ((Event) task).getEndDateTime().format(DateTimeFormatter.ISO_DATE_TIME)));
            rowBuilder.append(String.format("|%s",
                    ((Event) task).isAllDay() ? "1" : "0"));
        }

        rowBuilder.append("\n");
        return rowBuilder;
    }

    /**
     * Loads tasks from hard disk.
     *
     * @return An ArrayList of Tasks.
     * @throws DukeIoException              If file cannot be opened/read.
     * @throws DukeIllegalArgumentException If file data is corrupted and invalid values are read.
     */
    public ArrayList<Task> load() throws DukeIoException, DukeIllegalArgumentException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (Files.exists(path)) {
                BufferedReader reader = Files.newBufferedReader(path);

                String line = null;
                while ((line = reader.readLine()) != null) {
                    Task task = decodeTaskFromString(line);
                    tasks.add(task);
                }

                reader.close();
            }
        } catch (IOException e) {
            throw new DukeIoException("OOPS!!! Error trying to load data from file.");
        }

        return tasks;
    }

    /**
     * Decode a Task object from a given string and return it.
     * @param line String to decode.
     * @return Task decoded from String.
     * @throws DukeIllegalArgumentException If decoded string give an invalid value for object creation.
     * @throws DukeIoException              If encounter any IO errors.
     */
    private Task decodeTaskFromString(String line) throws DukeIllegalArgumentException, DukeIoException {
        Task task;
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];

        if (type.equalsIgnoreCase(TaskType.TODO.getTag())) {
            task = new Todo(description);
            task.setDone(isDone);
        } else if (type.equalsIgnoreCase(TaskType.DEADLINE.getTag())) {
            LocalDateTime byDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME);
            boolean isAllDay = "1".equals(parts[4]);
            task = new Deadline(description, byDate, isAllDay);
            task.setDone(isDone);
        } else if (type.equalsIgnoreCase(TaskType.EVENT.getTag())) {
            LocalDateTime startDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime endDate = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_DATE_TIME);
            boolean isAllDay = "1".equals(parts[5]);
            task = new Event(description, startDate, endDate, isAllDay);
            task.setDone(isDone);
        } else {
            throw new DukeIoException("OOPS!!! Encountered error while decoding file tasks.");
        }
        return task;
    }
}
