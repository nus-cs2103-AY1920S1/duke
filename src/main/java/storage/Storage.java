package storage;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks stored in the file and returns them as a list of tasks.
     * @return list of tasks stored in the text document
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
            String[] storedTasks = content.split("\n");
            // Adding tasks stored in the text file
            for (String s: storedTasks) {
                char taskType = s.charAt(0);
                tasks.add(taskStringToTask(taskType, s));
            }
        } catch (IOException e) {
            new File("./data").mkdir();
            new File("/data/duke.txt").createNewFile();
        } finally {
            return tasks;
        }
    }

    public Task taskStringToTask(char taskType, String taskString) throws DukeException{
        if (taskType == 'T') {
            Todo todo = new Todo(taskString.substring(8));
            if (taskString.charAt(4) != '0') {
                todo.markAsDone();
            }
            return todo;
        } else if (taskType == 'E') {
            String[] tempSplit = taskString.substring(8).split("\\u007C ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime ldt = LocalDateTime.parse(tempSplit[1].trim(), dtf);
            Event event = new Event(tempSplit[0].trim(), ldt, tempSplit[1].trim());
            if (taskString.charAt(4) != '0') {
                event.markAsDone();
            }
            return event;
        } else if (taskType == 'D') {
            String[] tempSplit = taskString.substring(8).split("\\u007C ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime ldt = LocalDateTime.parse(tempSplit[1].trim(), dtf);
            Deadline deadline = new Deadline(tempSplit[0].trim(), ldt, tempSplit[1].trim());
            if (taskString.charAt(4) != '0') {
                deadline.markAsDone();
            }
            return deadline;
        } else {
            assert false: "Invalid task type stored in storage";
            return null;
        }
    }

    /**
     * Writes the tasks into the text document.
     * @param tasks list of tasks
     */
    public void uploadTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(new File("data/duke.txt"));
            for (int i = 0; i < tasks.size(); i++) {
                String type = tasks.get(i) instanceof Event ? "E"
                        : tasks.get(i) instanceof Todo ? "T"
                        : tasks.get(i) instanceof Deadline ? "D"
                        : "INVALID CLASS";
                assert !type.equals("INVALID CLASS"): "Type of task in storage invalid";
                String isDone = tasks.get(i).isDone() ? "1" : "0";
                if (tasks.get(i) instanceof Todo) {
                    fw.append(type + " | " + isDone + " | " + tasks.get(i).getDescription());

                } else if (tasks.get(i) instanceof Event) {
                    fw.append(type + " | " + isDone + " | " + tasks.get(i).getDescription() + " | "
                            + ((Event) tasks.get(i)).getEventDateString());
                } else if (tasks.get(i) instanceof  Deadline) {
                    fw.append(type + " | " + isDone + " | " + tasks.get(i).getDescription() + " | "
                            + ((Deadline) tasks.get(i)).getTaskDeadlineString());
                }
                if (i != tasks.size() - 1) {
                    fw.append("\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}