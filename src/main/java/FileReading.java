import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {

    public static void checkFileExists(ArrayList<Task> taskList) throws IOException {
        File directory = new File("data");
        if (! directory.exists()){
            directory.mkdir();
        }
        File f = new File("data/duke.txt");
        if (!f.createNewFile()){loadFileContents(taskList, f);}
    }

    public static void loadFileContents(ArrayList<Task> taskList, File f) throws FileNotFoundException {
        String fullLine;
        String type;
        String done;
        String desc;
        String time = "";
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            fullLine = s.nextLine();
            String[] strArr = fullLine.split(" @ ");
            type = strArr[0];
            desc = strArr[1];
            done = strArr[2];
            if (type.equals("D") | type.equals("E")) {
                time = strArr[3];
            }

            switch (type) {
            case "T":
                ToDo toDo = new ToDo(desc);
                taskList.add(toDo);
                if (done.equals("true")) {
                    taskList.get(taskList.size() - 1).setDone();
                }
                break;

            case "D":
                Deadline d = new Deadline(desc, time);
                taskList.add(d);
                if (done.equals("true")) {
                    taskList.get(taskList.size() - 1).setDone();
                }
                break;

            case "E":
                Event e = new Event(desc, time);
                taskList.add(e);
                if (done.equals("true")) {
                    taskList.get(taskList.size() - 1).setDone();
                }
                break;
            }
        }
        s.close();
    }

}
