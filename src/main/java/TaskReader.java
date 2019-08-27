import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TaskReader {
    private static final String FILE_NAME = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data/duke.txt";
    private static final String DIRECTORY_NAME = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data";

    public ArrayList<Task> outputFileContents() throws DukeException {
        try {
            File taskStorage = new File(FILE_NAME);
            File directoryStorage = new File(DIRECTORY_NAME);
            if (!taskStorage.getAbsoluteFile().exists()) {
                directoryStorage.mkdir();
                taskStorage.createNewFile();
            }
            Scanner s = new Scanner(taskStorage); // create a Scanner using the File as the source
            ArrayList<Task> retrievedTask = new ArrayList<Task>();
            while (s.hasNext()) {
                //note that | is known as || in java
                String[] inputsplit = s.nextLine().split("\\|");
                if (inputsplit[0].equalsIgnoreCase("[T]")) {
                    ToDo t = new ToDo(inputsplit[2]);
                    this.checkIfDone(t,inputsplit[1]);
                    retrievedTask.add(t);
                } else if (inputsplit[0].equalsIgnoreCase("[D]")) {
                    Deadline d = new Deadline(inputsplit[2], inputsplit[3]);
                    this.checkIfDone(d,inputsplit[1]);
                    retrievedTask.add(d);
                } else {
                    Event e = new Event(inputsplit[2], inputsplit[3]);
                    this.checkIfDone(e,inputsplit[1]);
                    retrievedTask.add(e);
                }
            }
            return retrievedTask;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! I'm sorry, but file not found :-(");
        }
    }

    public void checkIfDone(Task t, String booleanValue){
        if(booleanValue.equalsIgnoreCase("True") ){
            t.markIsDone();
        }
    }

}

