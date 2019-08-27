import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String file;

    /**
     * Constructor of a storage object.
     * @param filepath
     */

    public Storage(String filepath) {
        this.file = filepath;
    }

    /**
     * This method will print the contents of the Storage object (file).
     * @throws FileNotFoundException
     */

    public ArrayList<Task> readFileContents() throws FileNotFoundException {
        File currentFile = new File(this.file);
        Scanner sc = new Scanner(currentFile);
        ArrayList<Task> list = new ArrayList<>();
        while(sc.hasNext()) {
            String current = sc.nextLine();
            char type = current.charAt(0);
            int isDone = Integer.parseInt("" + current.charAt(4));
            String des = current.substring(8).trim();
            switch(type) {
                case 'T':
                    ToDo newT = new ToDo(isDone, des);
                    list.add(newT);
                    break;

                case 'D':
                    int index = des.indexOf('|');
                    String taskName = des.substring(0, index).trim();
                    String date = des.substring(index + 1).trim();
                    Deadline newD = new Deadline(isDone, taskName, date);
                    list.add(newD);
                    break;

                case 'E':
                    int index1 = des.indexOf('|');
                    String taskName1 = des.substring(0, index1).trim();
                    String date1 = des.substring(index1 + 1).trim();
                    Event newE = new Event(isDone, taskName1, date1);
                    list.add(newE);
                    break;

                default:
            }
        }
        return list;
    }

    /**
     * Checks if the file is empty. If it is empty, method will return true.
     * @return
     */

    public boolean isFileEmpty() {
        File file = new File(this.file);
        return 0 == file.length();
    }

    /**
     *
     * @param toAdd string pushed to the file.
     * @throws IOException
     * This method rewrites the whole text file.
     */

    public void writeToFile(String toAdd) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        fw.write(toAdd);
        fw.close();
    }

    /**
     *
     * @param toAppend string to be appended to the list.
     * @throws IOException
     * This method instead of rewriting the entire text file, it adds on.
     */
    public void appendToFile(String toAppend) throws IOException {
        FileWriter fw = new FileWriter(this.file, true);
        fw.write(System.lineSeparator() + toAppend);
        fw.close();
    }


}
