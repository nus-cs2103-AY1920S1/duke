import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Storage {

    String filePath;
    File f;

    public Storage(String filepath) throws IOException {
        f = new File(filepath);
        f.getParentFile().mkdirs();
        f.createNewFile();
        this.filePath = filepath;
    }

    private static ArrayList<Task> readFile(FileReader fr) throws IOException, ParseException {
        ArrayList<Task> taskList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while((line = br.readLine()) != null) {
            Task t = null;
            String[] splitArr = line.split(" [|] ");
            if(splitArr[0].equals("T")) {
                t = new Todo(splitArr[2]);
            } else if(splitArr[0].equals("E")) {
                Date dateTime = dateFormat.parse(splitArr[3]);
                t = new Event(splitArr[2], dateTime);
            } else if(splitArr[0].equals("D")){
                Date dateTime = dateFormat.parse(splitArr[3]);
                t = new Deadline(splitArr[2], dateTime);
            }
            if(splitArr[1].equals("1")) {
                t.updateDone();
            }
            taskList.add(t);
        }
        return taskList;
    }

    public ArrayList<Task> load() throws IOException, ParseException {
        return readFile(new FileReader(f));
    }

    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < taskList.getSize(); i++) {
            if(i == 0) {
                fw.write(taskList.getTask(i).writeToFile());
            } else {
                fw.write(System.lineSeparator() + taskList.getTask(i).writeToFile());
            }
        }
        fw.close();
    }

    public void appendToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        if(taskList.getSize() == 1) {
                fw.write(taskList.getTask(taskList.getSize()-1).writeToFile());
        } else {
                fw.write(System.lineSeparator() + taskList.getTask(taskList.getSize()-1).writeToFile());
        }
        fw.close();
    }

}
