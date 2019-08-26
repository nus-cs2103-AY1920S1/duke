package memory;

import task.*;
import time.DateTime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import static task.TaskType.*;

public class Memory {
    private Path path;

    public Memory(String uri) {
        this.path = Paths.get(uri);
    }

    public TaskList readFromDisk() {
        Charset charset = Charset.forName("ISO-8859-1");
        TaskList taskList = new TaskList();
        try {
            BufferedReader reader = Files.newBufferedReader(path, charset);
            String line = null;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] args = line.split(" \\| ");
                String code = args[0];
                boolean isDone = args[1].equals("1");
                String[] taskArgs = Arrays.copyOfRange(args, 2, args.length);
                if (code.equals("T")) {
                    taskList.add(TaskFactory.getTask(TODO, taskArgs, isDone));
                } else if (code.equals("D")) {
                    taskList.add(TaskFactory.getTask(DEADLINE, taskArgs, isDone));
                } else if (code.equals("E")) {
                    taskList.add(TaskFactory.getTask(EVENT, taskArgs, isDone));
                } else {
                    throw new IOException("Attempting to read unknown task from disk!");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public TaskList writeToDisk(TaskList taskList) {
        Charset charset = Charset.forName("ISO-8859-1");
        ArrayList<Task> tasks = taskList.getTaskList();
        try {
            // clear entire file and write on an empty file
            BufferedWriter bw = Files.newBufferedWriter(path, charset, CREATE, TRUNCATE_EXISTING, WRITE);
            for (Task task: tasks) {
                if (task != null) { // account for the first null placeholder
                    String desc = task.getDescription();
                    int isDone = task.getIsDone() ? 1 : 0;
                    if (task instanceof Todo) {
                        bw.write(String.format("T | %d | %s", isDone, desc));
                        bw.newLine();
                    } else if (task instanceof Deadline) {
                        String deadline = ((Deadline) task).getDeadline();
                        bw.write(String.format("D | %d | %s | %s", isDone, desc, deadline));
                        bw.newLine();
                    } else if (task instanceof Event) {
                        String period = ((Event) task).getPeriod();
                        bw.write(String.format("E | %d | %s | %s", isDone, desc, period));
                        bw.newLine();
                    } else {
                        throw new IOException("Attempting to write unknown task to disk!");
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
