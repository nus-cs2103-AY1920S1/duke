import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath = ""; // Default is "data/duke.txt"

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            String path = this.filePath;
            FileWriter fw = new FileWriter(path);

            String text = "";
            for (int i = 0; i < tasks.size(); i++) {
                //Get tasks
                Task task = tasks.get(i);

                if(task instanceof Todo){
                    text += "T|" + task.isDone + "|" + task.description;
                } else if(task instanceof Deadline){
                    text += "D|" + task.isDone + "|" + task.description + "|" + ((Deadline)task).getDate();
                } else if(task instanceof Event){
                    text += "D|" + task.isDone + "|" + task.description + "|" + ((Event)task).getDate();
                }

                if (i + 1 != tasks.size()) {
                    text += "\n";
                }
            }

            fw.write(text);
            fw.close();
        } catch(IOException ie) {
            new DukeException("Something went wrong when saving. Please ensure the data directory is created.");
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> storage = new ArrayList<>();
        try {
            String path = this.filePath;
            File f = new File(path); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] input = s.nextLine().split("[|]");

                switch(input[0]){
                case "T":
                    Todo todo = new Todo(input[2]);
                    if(input[1].equals("true")) {
                        todo.markAsDone();
                    }
                    storage.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(input[2], input[3]);
                    if(input[1].equals("true")) {
                        deadline.markAsDone();
                    }
                    storage.add(deadline);
                    break;
                case "E":
                    Event event = new Event(input[2], input[3]);
                    if(input[1].equals("true")) {
                        event.markAsDone();
                    }
                    storage.add(event);
                    break;
                default:;
                    break;
                }

            }
        } catch(FileNotFoundException fnfe) {
            new DukeException("Unable to load file. Your saved data will not be loaded.");
        } catch(ArrayIndexOutOfBoundsException aiobe) {
            new DukeException("File corrupted. Your saved data will not be loaded.");
        }

        return storage;
    }

}
