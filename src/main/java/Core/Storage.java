package duke.core;

import duke.helper.DateTimeHelper;
import duke.helper.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    //stores information of the data in the duke.txt file.
    final String FILE_NAME = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data/duke.txt";
    final String DIRECTORY_NAME = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data";

    /**
     * Write the updated contents from TaskList.
     * First checks if the file or directory exists, if it doesn't create a new empty file.
     * public method called by Command and it's child classes each time Command.execute is run.
     *
     * @param taskEntered ArrayList of Tasks obtained from the TaskList class.
     * @throws DukeException which occurs if there is problem in loading the duke.txt.
     */
    public void writeToFile(ArrayList<Task> taskEntered) throws DukeException {
        try {
            File taskStorage = new File(FILE_NAME);
            File directoryStorage = new File(DIRECTORY_NAME);
            if (!taskStorage.getAbsoluteFile().exists()) {
                directoryStorage.mkdir();
                taskStorage.createNewFile();
            }
            FileWriter taskWrite = new FileWriter(FILE_NAME);
            String s = writeFromArray(taskEntered);
            taskWrite.write(s);
            taskWrite.close();
        } catch(IOException e) {
            throw new DukeException("OOPS!!! Error occured when loading file.");
        }
    }

    /**
     * Uses StringBuffer to append string of tasks obtained from tastEntered into format required to put into duke.txt.
     * public method called by writeToFile, which is called each time Command.execute is run.
     *
     * @param taskEntered ArrayList of Tasks obtained from the TaskList class.
     */
    public String writeFromArray(ArrayList<Task> taskEntered){
        StringBuffer toWrite = new StringBuffer("");
        for(int i = 0; i < taskEntered.size() ; i++){
            Task t = taskEntered.get(i);
            String s;
            if (t.getType().equalsIgnoreCase("[T]")) {
                s = t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "\n";
            } else {
                s = t.getType() + "|" + t.getIsDone() + "|" + t.getDescription()  + "\n";
            }
            toWrite.append(s);
        }
        return toWrite.toString();
    }

    /**
     * Understands the string from duke.txt and creates a arraylist of tasks based on the strings in duke.txt.
     * First checks if the file or directory exists, if it doesn't create a new empty file.
     * public method called by duke when it first starts up.
     *
     * @return ArrayList of Tasks which will be assigned to TaskList class.
     * @throws DukeException which occurs if there is problem in loading the duke.txt.
     */
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
                    LocalDateTime ldt = DateTimeHelper.formatInput(inputsplit[3]);
                    Deadline d = new Deadline(inputsplit[2], ldt);
                    this.checkIfDone(d,inputsplit[1]);
                    retrievedTask.add(d);
                } else {
                    LocalDateTime ldt = DateTimeHelper.formatInput(inputsplit[3]);
                    Event e = new Event(inputsplit[2], ldt);
                    this.checkIfDone(e,inputsplit[1]);
                    retrievedTask.add(e);
                }
            }
            return retrievedTask;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! I'm sorry, but file not found :-(");
        }
    }

    /**
     * Called by outputFileContents and checks the string which is retrieved from duke.txt if the task is Done.
     */
     public void checkIfDone(Task t, String booleanValue){
        if(booleanValue.equalsIgnoreCase("True") ){
            t.markIsDone();
        }
    }
}
