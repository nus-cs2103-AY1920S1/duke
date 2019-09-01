import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class.
 */
public class Storage {
    public static List<Task> myList = new ArrayList<>();
    public static String txtFileLocation;
    private File parentFile;

    public Storage(String filePath) {
        this.txtFileLocation = filePath;
    }

    /**
     * putToList().
     */
    public void putToList(String str) {
        String[] oneLine = str.split(" \\| ");
        String firstWord = oneLine[0].trim();
        try {
            if (oneLine[1].trim().equals("1") || oneLine[1].trim().equals("0")) {
                if (firstWord.equals("T") && oneLine.length == 3) {
                    myList.add(new Todo(oneLine[2], oneLine[1]));
                } else if (firstWord.equals("D") && oneLine.length == 4) {
                    System.out.println(oneLine[2] + "; " + oneLine[3] + "; " + oneLine[1]);
                    myList.add(new Deadline(oneLine[2], oneLine[3], oneLine[1]));
                } else if (firstWord.equals("E") && oneLine.length == 4) {
                    myList.add(new Event(oneLine[2], oneLine[3], oneLine[1]));
                } else {
                    throw new InvalidCommandException("[duke.txt]: I'm sorry, but I don't know what that means :-(");
                }
            } else {
                throw new InvalidNumberException("[duke.txt]: the description should have 0 or 1");
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * load().
     */
    public List<Task> load() throws DukeException {
        try {
            parentFile = new File(txtFileLocation);
            Scanner sc = new Scanner(parentFile);

            while (sc.hasNextLine()) {
                putToList(sc.nextLine());
            }

        } catch (Exception e) {
            System.out.println("[duke.txt]: duke.txt not found");
        }
        return myList;
    }

    /**
     * save(TaskList tasks).
     */
    public void save(TaskList tasks) throws Exception {
        try {
            parentFile = new File(txtFileLocation).getParentFile();

            if (!parentFile.exists()) {
                parentFile.mkdir();
            }

            PrintWriter pr = new PrintWriter(txtFileLocation);
            for (Task obj : myList) {
                pr.write(obj.getFormatToFile());
            }
            pr.close();
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("duke.txt not found");
        }
    }
}
