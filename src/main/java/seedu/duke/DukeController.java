package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskListEmptyException;
import seedu.duke.model.Deadline;
import seedu.duke.model.Event;
import seedu.duke.model.Task;
import seedu.duke.model.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DukeController {
    private static String DIRECTORY_PATH = "D:/project/CS2103T/duke/data";
    private static String FILEPATH = DIRECTORY_PATH + "/duke.txt";

    public File initFile() throws IOException {
        new File(DIRECTORY_PATH).mkdir();

        //System.out.println(FILEPATH);
        File textFile = new File(FILEPATH);
        textFile.createNewFile();
        return textFile;
    }

    public void saveTask(List<Task> list) throws IOException{
        FileWriter fileWriter = new FileWriter(FILEPATH, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Task t : list) {
            printWriter.println(t.toTextFileString());
        }

        printWriter.close();
    }

    //Future exception implementation required
    public List<Task> loadTask() throws IOException {
        FileReader fileReader = new FileReader(FILEPATH);
        BufferedReader reader = new BufferedReader(fileReader);
        List<Task> list = new ArrayList<>();

        String line = reader.readLine();
        while(line != null) {
            String[] arr = line.split(",");
            Task t = new Task("");

            String type = arr[0], desc = arr[2];
            int status = Integer.valueOf(arr[1]);

            if (type.equals("T")) {
                t = new Todo(desc, status);
            } else if (type.equals("D")) {
                String by = arr[3];
                t = new Deadline(desc, by, status);
            } else if (type.equals("E")) {
                String at = arr[3];
                t = new Event(desc, at, status);
            }
            line = reader.readLine();
            list.add(t);
        }
        reader.close();
        return list;
    }

    public void addTask(List<Task> list, String cmd, String desc, String time) throws DukeException,
            IOException {
        DukeController controller = new DukeController();

        if (cmd == null || desc == null || time == null) {
            throw new DukeException("oops! cmd/desc/time is null..");
        } else if (cmd.equals("") || desc.equals("") || time.equals("")) {
            throw new DukeException("oops! you entered cmd/desc/time empty..");
        } else {

            Task task = null;
            if (cmd.equals("todo")) {
                task = new Todo(desc);
            } else if (cmd.equals("deadline")) {
                task = new Deadline(desc, time);
            } else if (cmd.equals("event")) {
                task = new Event(desc, time);
            }
            list.add(task);

            saveTask(list);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    public void removeTask(List<Task> list, int index) throws TaskListEmptyException, DukeException,
            IOException{
        if (list.isEmpty()) {
            throw new TaskListEmptyException("list is empty");
        } else if (index <= 0 || list.size() < index  + 1) {
            throw new DukeException("Entered index is out of bound: " + index);
        } else {
            System.out.println("Noted. I've removed this task:");
            Task t = list.get(index);
            System.out.println("  " + t);
            list.remove(index);
            saveTask(list);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    public void validateTime(String[] arr) throws DukeException{
        if (arr[0].equals("")) {
            throw new DukeException("Oops! please specify task description");
        } else if (!arr[0].equals("") && arr.length == 1){
            throw new DukeException("Oops! please specify time (/at, /by ...");
        }
    }

    public void displayList(List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int index = 0;
        for (Task task : list) {
            index++;
            System.out.println(index + "." + task);
        }
    }

    public void displayTask(List<Task> list, int index) {
        if (index >= 0) {
            System.out.println(list.get(index));
        }
    }

    public static void main(String[] args) throws IOException{
        DukeController controller = new DukeController();
        List<Task> list = controller.loadTask();
        for (Task t : list) {
            System.out.println(t);
        }
    }
}
