package com.leeyiyuan.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.leeyiyuan.task.DeadlineTask;
import com.leeyiyuan.task.EventTask;
import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TodoTask;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                tasks.add(Storage.deserialize(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {

        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fw = new FileWriter(filePath, false);
        for (Task task : tasks) {
            fw.write(Storage.serialize(task) + "\n");
        }
        fw.close();         
    }

    // TODO Utilize polymorphism on Task subclasses.
    protected static Task deserialize(String line) {
        if (Pattern.matches("^T \\| [01] \\| .+$", line)) {
            String[] data = line.split(" \\| ", 3);
            TodoTask task = new TodoTask();
            task.setIsDone(data[1].equals("1"));
            task.setTitle(data[2]);
            return task;
        } else if (Pattern.matches("^D \\| [01] \\| .+ \\| .+$", line)) {
            String[] data = line.split(" \\| ", 4);
            DeadlineTask task = new DeadlineTask();
            task.setIsDone(data[1].equals("1"));
            task.setTitle(data[2]);
            task.setDeadline(data[3]);
            return task;
        } else if (Pattern.matches("^E \\| [01] \\| .+ \\| .+$", line)) {
            String[] data = line.split(" \\| ", 4);
            EventTask task = new EventTask();
            task.setIsDone(data[1].equals("1"));
            task.setTitle(data[2]);
            task.setTime(data[3]);
            return task;
        } else {
            return null;
        }
    }

    // TODO Utilize polymorphism on Task subclasses.
    protected static String serialize(Task task) {
        if (task instanceof TodoTask) {
            return String.format("T | %d | %s",
                    ((TodoTask)task).getIsDone() ? 1 : 0,
                    ((TodoTask)task).getTitle());
        } else if (task instanceof DeadlineTask) {
            return String.format("D | %d | %s | %s",
                   ((DeadlineTask)task).getIsDone() ? 1 : 0,
                   ((DeadlineTask)task).getTitle(),
                   ((DeadlineTask)task).getDeadline());
        } else if (task instanceof EventTask) {
            return String.format("E | %d | %s | %s",
                    ((EventTask)task).getIsDone() ? 1 : 0,
                    ((EventTask)task).getTitle(),
                    ((EventTask)task).getTime());
        }
        return null;
    }

}
