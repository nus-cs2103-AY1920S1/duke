package duke.storage;

import duke.exception.DukeException;
import duke.exception.DukeLoadingError;
import duke.task.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Storage {

    private BufferedReader reader;
    private FileWriter writer;
    private String filepath;
    private ArrayList<Task> tasks;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasks = new ArrayList<>();
    }

    public void addBackDeadlineTask(String task) {
        String[] taskDetails = task.split(" / ");
        String completion = taskDetails[1];
        String nameOfTask = taskDetails[2];
        String deadline = taskDetails[3];

        if (completion.equals("Y")) {
            tasks.add(new DeadlineTask(nameOfTask, true, deadline));
        } else {
            tasks.add(new DeadlineTask(nameOfTask, false, deadline));
        }
    }

    public void addBackEventTask(String task) {
        String[] taskDetails = task.split(" / ");
        String completion = taskDetails[1];
        String nameOfTask = taskDetails[2];
        String time = taskDetails[3];

        if (completion.equals("Y")) {
            tasks.add(new EventTask(nameOfTask, true, time));
        } else {
            tasks.add(new EventTask(nameOfTask, false, time));
        }
    }

    public void addBackToDoTask(String task) {
        String[] taskDetails = task.split(" / ");
        String completion = taskDetails[1];
        String nameOfTask = taskDetails[2];

        if (completion.equals("Y")) {
            tasks.add(new ToDoTask(nameOfTask, true));
        } else {
            tasks.add(new ToDoTask(nameOfTask, false));
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String task;
            boolean hasStartedLoading = false;
            while ((task = reader.readLine()) != null) {
                if (task.startsWith("D")) {
                    addBackDeadlineTask(task);
                } else if (task.startsWith("E")) {
                    addBackEventTask(task);
                } else {
                    addBackToDoTask(task);
                }

                hasStartedLoading = true;
            }
            if (!hasStartedLoading) {
                throw new DukeLoadingError();
            }
            reader.close();
        } catch (Exception e) {
            throw new DukeLoadingError();
        }
        return tasks;
    }

    public void save() {
        try {
            writer = new FileWriter(filepath);
            for (int i = 0; i < TaskList.getNumberOfTasks(); i ++) {
                Task task = TaskList.getTask(i);
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    if (task.isCompleted) {
                        writer.write(String.format("D / Y / %s / %s", task.todo, deadlineTask.deadline));
                    } else {
                        writer.write(String.format("D / N / %s / %s", task.todo, deadlineTask.deadline));
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    if (task.isCompleted) {
                        writer.write(String.format("E / Y / %s / %s", task.todo, eventTask.time));
                    } else {
                        writer.write(String.format("E / N / %s / %s", task.todo, eventTask.time));
                    }
                } else {
                    ToDoTask todoTask = (ToDoTask) task;
                    if (task.isCompleted) {
                        writer.write(String.format("T / Y / %s", task.todo));
                    } else {
                        writer.write(String.format("T / N / %s", task.todo));
                    }
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {

        }
    }

}
