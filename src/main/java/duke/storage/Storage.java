package duke.storage;

import duke.logic.Parser;
import duke.logic.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Deals with saving and loading of tasks in duke.txt.
 */

public class Storage {

    private File file;
    private String filePath;

    /**
     * Constructor for storage.
     * @param filePath name of file path.
     * @throws IOException when file does not open.
     */
    public Storage(String filePath) throws IOException {

        file = new File(filePath);

        assert file != null;
        if (! file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        this.filePath = filePath;


    }

    /**
     * Loads the ArrayList of Task(s) from duke.txt.
     * @return ArrayList of Task(s)
     * @throws FileNotFoundException when file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {

        ArrayList<Task> arr = new ArrayList<>();

        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] stringArr = line.split("\\|");

            if (stringArr[0].trim().equals("T")) {
                arr.add(Parser.getToDo(stringArr[3].trim()));

            } else if (stringArr[0].trim().equals("D")) {
                arr.add(Parser.getDeadline(stringArr[3].trim(), stringArr[4].trim()));

            } else if (stringArr[0].trim().equals("E")) {
                arr.add(Parser.getEvent(stringArr[3].trim(), stringArr[4].trim()));

            }

            Task lastTask = arr.get(arr.size() - 1);
            //Mark the last task as done.
            if (stringArr[1].trim().equals("1")) {
                lastTask.markAsDone();
            }

            switch (stringArr[2].trim()) {
            case "1":
                lastTask.setPriority(1);
                break;
            case "2":
                lastTask.setPriority(2);
                break;
            case "3":
                lastTask.setPriority(3);
                break;
            case "4":
                lastTask.setPriority(4);
                break;
            default:
                lastTask.setPriority(1);
                break;
            }
        }

        return arr;
    }

    /**
     * Writes taskList to duke.txt.
     * @param taskList that is to be written to file.
     * @throws IOException when file does not exist.
     */
    public void writeListToFile(TaskList taskList) throws IOException {
        ArrayList<Task> arr = taskList.getArr();
        FileWriter fw = new FileWriter(filePath);
        StringBuilder sb = new StringBuilder();

        for (Task entry : arr) {

            if (entry instanceof Deadline) {
                sb.append(String.format("D | %s | %d | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getPriorityValue(), entry.getTaskName(), ((Deadline) entry).getDateTime()));
            } else if (entry instanceof Event) {
                sb.append(String.format("E | %s | %d | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getPriorityValue(), entry.getTaskName(), ((Event) entry).getDateTime()));
            } else if (entry instanceof ToDo) {
                sb.append(String.format("T | %s | %d | %s", entry.isDone() ? "1" : "0",
                        entry.getPriorityValue(), entry.getTaskName()));
            }

            sb.append(System.lineSeparator());
            fw.write(sb.toString());
            sb = new StringBuilder();
        }

        fw.close();
    }
}
