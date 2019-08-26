package duke;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static final String FILE_PATH = "data/duke.txt";

    private ArrayList<Task> tasks;

    public static TaskList create(int num) {
        try {
            ArrayList<String> storage = read();
            return new TaskList(num, storage);
        } catch (FileNotFoundException e) {
            return new TaskList(num);
        }
    }

    private TaskList(int num) {
        this.tasks = new ArrayList<>(num);
    }

    private TaskList(int num, ArrayList<String> storage) {
        this.tasks = new ArrayList<>(num);
        for (String s : storage) {
            String[] args = s.split("\\|");
            switch(args[0]) {
                case "T":
                    Task todo = new TodoTask(args[2]);
                    if(args[1].equals("1"))
                        todo.markDone();
                    tasks.add(todo);
                    break;
                case "E":
                    Task event = new EventTask(args[2], args[3]);
                    if(args[1].equals("1"))
                        event.markDone();
                    tasks.add(event);
                    break;
                case "D":
                    Task deadline = new DeadlineTask(args[2], args[3]);
                    if(args[1].equals("1"))
                        deadline.markDone();
                    tasks.add(deadline);
                    break;
                default:
                    break;
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);

        try {
            write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);

        try {
            write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public int numTasks() {
        return tasks.size();
    }

    public void markDone(int index) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markDone();

        try {
            write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        int size = tasks.size();
        if(size == 0) {
            return "Empty list: no tasks added";
        } else {
            int index = 1;
            StringBuilder listOfTasks = new StringBuilder();
            for(Task task : tasks) {
                if(index == 1) {
                    listOfTasks.append(
                            String.format("%d.%s", index, task));
                    index++;
                } else {
                    listOfTasks.append(
                            String.format("\n%d.%s", index, task));
                    index++;
                }
            }
            return listOfTasks.toString();
        }
    }

    private ArrayList<String> formatStrings() {
        ArrayList<String> al = new ArrayList<>();
        for (Task task : tasks) {
            al.add(task.formatString());
        }
        return al;
    }

    private void write() throws IOException {
        Path path = Paths.get(FILE_PATH);
        Files.write(path, formatStrings());
    }

    private static ArrayList<String> read() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner sc = new Scanner(f);
        ArrayList<String> input = new ArrayList<>();
        while(sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        return input;
    }
}
