import java.io.File;
import java.io.FileWriter;

import java.util.ListIterator;
import java.util.LinkedList;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    File taskListFile;

    public Storage(String filePath) {
        taskListFile = new File(filePath);
    }

    public LinkedList<Task> load() throws DukeException {
        try {
            if (!taskListFile.exists()) {
                throw new NoExistingListException("No saved List found.");
            }

            Scanner sc = new Scanner(taskListFile);
            LinkedList<Task> taskList = new LinkedList<>();


            while (sc.hasNext()) {
                String nextTask = sc.nextLine();

                String[] nextTaskArr = nextTask.split("~");
                boolean status = nextTaskArr[1].equals("1");

                if (nextTaskArr[0].equals("T")) {
                    taskList.add(
                            new Todo(nextTaskArr[2], status));
                } else if (nextTaskArr[0].equals("D")) {
                    taskList.add(
                            new Deadline(nextTaskArr[2], nextTaskArr[3], status));
                } else if (nextTaskArr[0].equals("E")) {
                    taskList.add(
                            new Event(nextTaskArr[2], nextTaskArr[3], status));
                }
            }

            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return new LinkedList<Task>();
        }
    }

    public void update(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(taskListFile);
        StringBuilder sb = new StringBuilder();
        LinkedList<Task> list = taskList.getList();

        ListIterator<Task> iter = list.listIterator();

        while (iter.hasNext()) {
            Task current = iter.next();
            sb.append(current.toFileFormat());
            sb.append("\n");
        }

        String taskListString = sb.toString();

        fw.write(taskListString);
        fw.close();
    }
}