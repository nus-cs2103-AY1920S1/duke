import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private ArrayList<Task> taskList;

    private static void loadFileContents(String filePath, ArrayList<Task> storingList)
            throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (f.length() == 0) {
            throw new DukeException("No listings found!");
        }
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            String[] splitCurrentLine = currentLine.split("-");
            if (splitCurrentLine[0].equals("D")) {
                storingList.add(new Deadline(splitCurrentLine[2],
                        "by " + splitCurrentLine[3]));
                storingList.get(storingList.size()-1).recoverStatus(splitCurrentLine[1]);
            } else if (splitCurrentLine[0].equals("E")) {
                storingList.add(new Events(splitCurrentLine[2],
                        "at " + splitCurrentLine[3]));
                storingList.get(storingList.size()-1).recoverStatus(splitCurrentLine[1]);
            } else if (splitCurrentLine[0].equals("T"))  {
                storingList.add(new ToDos(splitCurrentLine[2]));
                storingList.get(storingList.size()-1).recoverStatus(splitCurrentLine[1]);
            }
        }
    }

    private static void WriteTextToFile(String filepath, ArrayList<Task> taskList)
            throws IOException {
        FileWriter fileOut = new FileWriter(filepath);
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                fileOut.write(taskList.get(i).formatString());
            } else {
                fileOut.write(taskList.get(i).formatString() + "\n");
            }
        }
            fileOut.close();
    }

    protected Storage(String filepath) {
        this.filepath = filepath;
        this.taskList = new ArrayList<Task>();
    }

    protected void save(ArrayList<Task> taskList) {
        try {
            Storage.WriteTextToFile(this.filepath, taskList);
        } catch (IOException e) {
            System.err.println("Something went wrong!");
        }
    }

    protected ArrayList<Task> load() throws DukeException {
        try {
            Storage.loadFileContents(this.filepath, this.taskList);
        } catch (FileNotFoundException e) {
            System.err.println("No file found, creating new file");
            File file = new File(this.filepath);
        }
        return this.taskList;
    }
}
