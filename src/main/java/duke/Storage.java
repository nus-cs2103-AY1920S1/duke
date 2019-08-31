package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private File f;

    public Storage(String filePath) {
        f = new File(filePath);
    }

    public List<Task> load() throws DukeException {

        List<Task> tasks = new ArrayList<>(100);
        try {
            if (!f.createNewFile()) {
//                System.out.println("New file created");
//            } else {
//                System.out.println("File already exists");

                //read file contents into List
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;



                while((line = br.readLine()) != null){
                    String[] lines = line.split(" \\| ");
                    boolean isDone;
                    if (lines[1].equals("1")) {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    if (lines[0].equals("T")) {
                        tasks.add(new Todo(lines[2], isDone));
                    } else if (lines[0].equals("D")) {
                        tasks.add(new Deadline(lines[2], lines[3], isDone));
                    } else if (lines[0].equals("E")) {
                        tasks.add(new Event(lines[2],lines[3], isDone));
                    } else {
                        System.out.println("Corrupted data.");
                    }
                }

                br.close();
                fr.close();
            }
        } catch (IOException e) {
            throw new DukeException("There has been an IOException while creating the data file.");
        }
        return tasks;
    }

    public void saveDataToFile(List<Task> tasks) throws DukeException {
        //write to a completely new file
        try {
            FileWriter fw = new FileWriter(f, false);
            for (Task task : tasks) {
                String s = task.toDataFormat();
                fw.write(s + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to datafile.");
        }
    }

}
