import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException, IOException{
        ArrayList<Task> result = new ArrayList<>();

            File f = new File(filePath);
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()) {
                String s = reader.nextLine();
                String[] tempArray = s.split(" -- ");
                Task t;
                if (tempArray[0].equals("[E]") || tempArray[0].equals("[D]")) {
                    String dateTime = tempArray[3];
                    Calendar dateAndTime = Calendar.getInstance();
                    String[] date = dateTime.split(" ")[0].split("/");
                    int time = Integer.parseInt(dateTime.split(" ")[1]);
                    int hours = time / 100;
                    int minutes = time % 100;
                    dateAndTime.set(Integer.parseInt(date[2]),
                            Integer.parseInt(date[1]),
                            Integer.parseInt(date[0]),
                            hours,
                            minutes);
                    if (tempArray[0].equals("[D]")) {
                        t = new Deadline(tempArray[2], dateAndTime);
                    } else {
                        t = new Event(tempArray[2], dateAndTime);
                    }
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

    public void update(TaskList tasks) throws IOException{
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
                Calendar time = ((Event) t).getTime();
                textToAdd += " -- " + time.get(Calendar.DAY_OF_MONTH) + "/" +
                        time.get(Calendar.MONTH) + "/" + time.get(Calendar.YEAR) + " ";
                if (time.get(Calendar.HOUR_OF_DAY) < 10) {
                    textToAdd += "0";
                }
                textToAdd += time.get(Calendar.HOUR_OF_DAY);
                if (time.get(Calendar.MINUTE) < 10) {
                    textToAdd += "0";
                }
                textToAdd += time.get(Calendar.MINUTE);
            }
            if (t instanceof Deadline) {
                Calendar time = ((Deadline) t).getTime();
                textToAdd += " -- " + time.get(Calendar.DAY_OF_MONTH) + "/" +
                        time.get(Calendar.MONTH) + "/" + time.get(Calendar.YEAR) + " ";
                if (time.get(Calendar.HOUR_OF_DAY) < 10) {
                    textToAdd += "0";
                }
                textToAdd += time.get(Calendar.HOUR_OF_DAY);
                if (time.get(Calendar.MINUTE) < 10) {
                    textToAdd += "0";
                }
                textToAdd += time.get(Calendar.MINUTE);
            }
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
