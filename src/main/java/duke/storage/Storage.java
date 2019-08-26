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

    private File f;

    public Storage(String filePath) {

        f = new File(filePath);

    }

    /**
     * Loads the ArrayList of Task(s) from duke.txt.
     * @return ArrayList of Task(s)
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {

        ArrayList<Task> arr = new ArrayList<>();

        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] stringArr = line.split("\\|");


            if (stringArr[0].trim().equals("T")) {

                arr.add(Parser.getToDo(stringArr[2].trim()));
            } else if (stringArr[0].trim().equals("D")) {
                arr.add(Parser.getDeadline(stringArr[2].trim(), stringArr[3].trim()));
            } else if (stringArr[0].trim().equals("E")) {
                arr.add(Parser.getEvent(stringArr[2].trim(), stringArr[3].trim()));
            }

            if (stringArr[1].trim().equals("1")) {
                arr.get(arr.size()-1).markAsDone();
            }

        }

        return arr;
    }

    /**
     * Writes taskList to duke.txt.
     * @param taskList
     * @throws IOException
     */
    public void writeListToFile(TaskList taskList) throws IOException {
        ArrayList<Task> arr = taskList.getArr();

        FileWriter fw = new FileWriter("resources/duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task entry : arr) {
            if (entry instanceof Deadline) {
                sb.append(String.format("D | %s | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getTaskName(), ((Deadline) entry).getDateTime() ));
            } else if (entry instanceof Event) {
                sb.append(String.format("E | %s | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getTaskName(), ((Event) entry).getDateTime() ));
            } else if (entry instanceof ToDo) {
                sb.append(String.format("T | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getTaskName() ));
            }

            sb.append(System.lineSeparator());
            fw.write(sb.toString());
            sb = new StringBuilder();
        }

        fw.close();

    }
}
