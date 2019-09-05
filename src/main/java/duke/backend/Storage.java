package duke.backend;

import duke.task.Task;
import duke.task.ToDos;
import duke.task.Events;
import duke.task.Deadlines;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    public String filePath;
    private SimpleDateFormat formatter;

    public Storage(String filePath, SimpleDateFormat formatter) {
        this.filePath = filePath;
        this.formatter = formatter;
    }

    /**
     * Checks given filepath, if file exists, creates ArrayList of tasks from that file.
     * If it does not exist, an empty ArrayList is created.
     * @return new ArrayList of tasks.
     * @throws FileNotFoundException in the event that file does not exist in given directory.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> oldList = new ArrayList<>();
        while (s.hasNextLine()) {
            String task = s.nextLine();
            String[] splitTask = task.split("\\|");
            switch (splitTask[0]) {
            case "T":
                ToDos todo = new ToDos(splitTask[2], formatter);
                todo.done = isDone(Integer.parseInt(splitTask[1]));
                oldList.add(todo);
                break;
            case "D":
                try {
                    Date date = formatter.parse(splitTask[3]);
                    Deadlines dl = new Deadlines(splitTask[2], formatter, date);
                    dl.done = isDone(Integer.parseInt(splitTask[1]));
                    oldList.add(dl);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "E":
                try {
                    System.out.println(splitTask[3]);
                    Date date = formatter.parse(splitTask[3]);
                    Events event = new Events(splitTask[2], formatter, date);
                    event.done = isDone(Integer.parseInt(splitTask[1]));
                    oldList.add(event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Task does not exist.");
                break;
            }
        }
        return oldList;
    }

    /**
     * method that saves current list in ListManager to a text file in given directory.
     * @param filePath String of given directory.
     * @param textToAdd String of tasks.
     * @throws IOException to handle if there is no text to be written in.
     */
    public void save(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * simple boolean flag to tell UI that the program is exiting.
     * @param num 0 for no exit, 1 for exit.
     * @return boolean on whether to quit the program.
     */
    private boolean isDone(int num) {
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }
}
