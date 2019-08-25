package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws Exception {
        //return array after reading from file
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filepath);
        f.getParentFile().mkdirs();
        f.createNewFile();
        BufferedReader bfr = new BufferedReader(new FileReader(f));
        String line = null;
        while ((line = bfr.readLine()) != null) {
            Task t = Parser.readInFileLine(line);
            tasks.add(t);
        }
        return tasks;
    }

    public void writeFile(TaskList tasks) throws Exception {
        FileWriter fw = new FileWriter(this.filepath);
        String textFileMsg = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                textFileMsg = textFileMsg + tasks.get(i).toWriteIntoFile();
            } else {
                textFileMsg = textFileMsg + System.lineSeparator() + tasks.get(i).toWriteIntoFile();
            }
        }
        fw.write(textFileMsg);
        fw.close();
    }

    public void appendFile(TaskList tasks) throws Exception {
        String textFileMsg = System.lineSeparator() + tasks.get(tasks.size() - 1).toWriteIntoFile();
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(textFileMsg);
        fw.close();
    }
}
