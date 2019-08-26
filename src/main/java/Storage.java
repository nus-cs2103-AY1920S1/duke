import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    private Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /** Saves tasks to hard disk */
    public void save(TaskList taskList) throws DukeIOException {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            BufferedWriter writer = Files.newBufferedWriter(path);
            StringBuilder sb = new StringBuilder();
            ArrayList<Task> tasks = taskList.toList();

            // write tasks into file
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);

                StringBuilder rowBuilder = new StringBuilder(
                        String.format("%s|%s|%s", task.type.getTag(), task.isDone ? "1" : "0", task.description));

                if (task instanceof Deadline) {
                    rowBuilder.append(String.format("|%s",
                            ((Deadline) task).by.format(DateTimeFormatter.ISO_DATE_TIME)));
                    rowBuilder.append(String.format("|%s",
                            ((Deadline) task).isAllDay ? "1" : "0"));
                } else if (task instanceof Event) {
                    rowBuilder.append(String.format("|%s|%s",
                            ((Event) task).startDateTime.format(DateTimeFormatter.ISO_DATE_TIME),
                            ((Event) task).endDateTime.format(DateTimeFormatter.ISO_DATE_TIME)));
                    rowBuilder.append(String.format("|%s",
                            ((Event) task).isAllDay ? "1" : "0"));
                }

                rowBuilder.append("\n");
                sb.append(rowBuilder);
            }
            writer.write(sb.toString());

            writer.close();
        } catch (IOException e) {
            throw new DukeIOException("OOPS!!! Error trying to save data to file.");
        }
    }

    /** Loads tasks from hard disk */
    public ArrayList<Task> load() throws DukeIOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (Files.exists(path)) {
                BufferedReader reader = Files.newBufferedReader(path);

                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    String type = parts[0];
                    boolean isDone = "1".equals(parts[1]);
                    String description = parts[2];

                    if (type.equalsIgnoreCase(TaskType.TODO.getTag())) {
                        Task task = new Todo(description);
                        task.setDone(isDone);
                        tasks.add(task);
                    } else if(type.equalsIgnoreCase(TaskType.DEADLINE.getTag())) {
                        LocalDateTime byDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME);
                        boolean isAllDay = "1".equals(parts[4]);
                        Task task = new Deadline(description, byDate, isAllDay);
                        task.setDone(isDone);
                        tasks.add(task);
                    } else if (type.equalsIgnoreCase(TaskType.EVENT.getTag())) {
                        LocalDateTime startDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_DATE_TIME);
                        LocalDateTime endDate = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_DATE_TIME);
                        boolean isAllDay = "1".equals(parts[5]);
                        Task task = new Event(description, startDate, endDate, isAllDay);
                        task.setDone(isDone);
                        tasks.add(task);
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            throw new DukeIOException("OOPS!!! Error trying to load data from file.");
        }

        return tasks;
    }
}
