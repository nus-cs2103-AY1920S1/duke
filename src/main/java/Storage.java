import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private ArrayList<Task> tasklist;

    private static void loadFileContents(String filePath, ArrayList<Task> storinglist) throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (f.length() == 0) {
            throw new DukeException("No listings found!");
        }
        while (s.hasNextLine()) {
            String currentline = s.nextLine();
            String[] splitcurrentline = currentline.split("-");
            if (splitcurrentline[0].equals("D")) {
                storinglist.add(new Deadline(splitcurrentline[2],
                        "by " + splitcurrentline[3]));
                storinglist.get(storinglist.size()-1).recoverStatus(splitcurrentline[1]);
            } else if (splitcurrentline[0].equals("E")) {
                storinglist.add(new Events(splitcurrentline[2],
                        "at " + splitcurrentline[3]));
                storinglist.get(storinglist.size()-1).recoverStatus(splitcurrentline[1]);
            } else if (splitcurrentline[0].equals("T"))  {
                storinglist.add(new ToDos(splitcurrentline[2]));
                storinglist.get(storinglist.size()-1).recoverStatus(splitcurrentline[1]);
            }
        }
    }

    private static void WriteTextToFile(String filepath, ArrayList<Task> tasklist) throws IOException {

        FileWriter fileOut = new FileWriter(filepath);

        for (int i = 0; i < tasklist.size(); i++) {
            if (i == tasklist.size() - 1) {
                fileOut.write(tasklist.get(i).formatString());
            } else {
                fileOut.write(tasklist.get(i).formatString() + "\n");
            }
        }
            fileOut.close();
    }

    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasklist = new ArrayList<Task>();
    }

    public void save(ArrayList<Task> tasklist) {
        try {
            Storage.WriteTextToFile(this.filepath, tasklist);
        } catch (IOException e) {
            System.err.println("Something went wrong!");
        }


    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Storage.loadFileContents(this.filepath, this.tasklist);
        } catch (FileNotFoundException e) {
            System.err.println("No file found, creating new file");
            File file = new File(this.filepath);
        }

        return this.tasklist;
    }

}
