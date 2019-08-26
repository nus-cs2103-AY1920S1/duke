import java.util.ListIterator;
import java.util.LinkedList;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskFile {
    File dukeTaskList;

    public TaskFile(File dukeTaskList) {
        this.dukeTaskList = dukeTaskList;
    }

    public void importTaskList() {
        try {
            File f = new File("dukeTaskList.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String nextTask = sc.nextLine();

                String[] nextTaskArr = nextTask.split("~");
                boolean status = nextTaskArr[1].equals("1");

                if (nextTaskArr[0].equals("T")) {
                    Task.addToTaskList(
                            new Todo(nextTaskArr[2], status));
                } else if (nextTaskArr[0].equals("D")) {
                    Task.addToTaskList(
                            new Deadline(nextTaskArr[2], nextTaskArr[3], status));
                } else if (nextTaskArr[0].equals("E")) {
                    Task.addToTaskList(
                            new Event(nextTaskArr[2], nextTaskArr[3], status));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() throws IOException {
        FileWriter fw = new FileWriter(this.dukeTaskList);
        StringBuilder sb = new StringBuilder();
        LinkedList<Task> taskList = Task.getTaskList();

        ListIterator<Task> iter = taskList.listIterator();

        while(iter.hasNext()) {
            Task current = iter.next();
            sb.append(current.toFileFormat());
            sb.append("\n");
        }

        String taskListString = sb.toString();

        fw.write(taskListString);
        fw.close();
    }

    public void checkForExistingTasks(File dukeTaskList) {
        if (dukeTaskList.exists()) {
            importTaskList();
        }
    }
}