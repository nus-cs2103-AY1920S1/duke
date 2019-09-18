package duke.command;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Manages the file that acts as a database for the tasks provided by User.
 */
public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes data in the history file and creates an ArrayList holding the User's tasks.
     * @return ArrayList holding the Tasks retrieved from history file.
     * @throws DukeException thrown if a task is of invalid format.
     * @throws IOException thrown id filePath is non-Existent.
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> result = new ArrayList<>();
        File f = new File(filePath);
        Scanner reader = new Scanner(f);
        while (reader.hasNext()) {
            String s = reader.nextLine();
            String[] tempArray = s.split(" -- ");
            Task t;
            if (tempArray[0].equals("[D]")) {
                String dateTime = tempArray[3];
                Calendar dateAndTime = Calendar.getInstance();
                String[] date = dateTime.split(" ")[0].split("/");
                int time = Integer.parseInt(dateTime.split(" ")[1]);
                int hours = time / 100;
                int minutes = time % 100;
                dateAndTime.set(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]) - 1,
                        Integer.parseInt(date[0]),
                        hours,
                        minutes);
                t = new Deadline(tempArray[2], dateAndTime);

            } else if (tempArray[0].equals("[E]")) {
                String dateTime = tempArray[3];
                Calendar startDateAndTime = Calendar.getInstance();
                Calendar endDateAndTime = Calendar.getInstance();
                String[] date = dateTime.split(" ")[0].split("/");
                int startTime = Integer.parseInt(dateTime.split(" ")[1].split("-")[0]);
                int endTime = Integer.parseInt(dateTime.split(" ")[1].split("-")[1]);
                int startHours = startTime / 100;
                int startMinutes = startTime % 100;
                int endHours = endTime / 100;
                int endMinutes = endTime % 100;
                startDateAndTime.set(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]) - 1,
                        Integer.parseInt(date[0]),
                        startHours,
                        startMinutes);
                endDateAndTime.set(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]) - 1,
                        Integer.parseInt(date[0]),
                        endHours,
                        endMinutes);
                t = new Event(tempArray[2], startDateAndTime, endDateAndTime);
            } else if (tempArray[0].equals("[T]")) {
                t = new ToDo(tempArray[2]);
            } else {
                throw new DukeException(" :( OOPS!!! Not a valid Task type.");
            }
            if (tempArray[1].equals("[+]")) {
                t.markAsDone();
            }
            result.add(t);
        }
        return result;
    }

    /**
     * Uses TaskList holding all current Tasks to update history file to hold their information.
     * @param tasks the TaskList that the history file must reflect.
     * @throws IOException thrown if filePath of history file is non-Existent.
     */
    public void update(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (Task t : tasks.getArr()) {
            String type = "";
            if (t instanceof Event) {
                type = "E";
            } else if (t instanceof ToDo) {
                type = "T";
            } else if (t instanceof Deadline) {
                type = "D";
            }
            textToAdd += "[" + type + "] -- " + "[" + t.getStatusIcon() + "] -- " + t.getDescription();
            if (t instanceof Event) {
                Calendar startTime = ((Event) t).getStartTime();
                Calendar endTime = ((Event) t).getEndTime();
                SimpleDateFormat startDateFormat = new SimpleDateFormat(" -- dd/MM/YYYY HHmm");
                textToAdd += startDateFormat.format(startTime.getTime());
                SimpleDateFormat endDateFormat = new SimpleDateFormat("-HHmm");
                textToAdd += endDateFormat.format(endTime.getTime());
            }
            if (t instanceof Deadline) {
                Calendar time = ((Deadline) t).getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat(" -- dd/MM/YYYY HHmm");
                textToAdd += dateFormat.format(time.getTime());
            }
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
