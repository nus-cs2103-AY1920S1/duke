import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the file used to store data.
 */
public class Storage {
    private String filePath = "";
    /**
     * Default file path provides by user
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads the data from the storage file.
     * @throws ErrorException if the default path is invalid
     */
    public ArrayList<Task> loadtxt() throws ErrorException {
        ArrayList<Task> arrlist = new ArrayList<>();
        try {
            File txtfile = new File(filePath);
            Scanner s = new Scanner(txtfile);
            while (s.hasNext()) {
            String txttasks = s.nextLine();
            String[] txtsplit = txttasks.split(",");
            if (txtsplit[0].equals("T")) {
                String description = txtsplit[2];
                Task todo = new Todo(description);
                if (txtsplit[1].equals("1")) {
                    todo.setDone();
                }
                arrlist.add(todo);
            } else if (txtsplit[0].equals("E")) {
                String description = txtsplit[2];
                String at = txtsplit[3];
                Task event = new Event(description, at);
                if (txtsplit[1].equals("1")) {
                    event.setDone();
                }
                arrlist.add(event);
            } else {
                String description = txtsplit[2];
                String by = txtsplit[3];
                Task deadline = new Deadline(description, by);
                if (txtsplit[1].equals("1")) {
                    deadline.setDone();;
                }
                arrlist.add(deadline);
            }
        }
    }
        catch (FileNotFoundException e) {
            throw new ErrorException("File not found");
        } finally {
            return arrlist;
        }
    }
    /**
     * Saves the data to the storage file.
     *
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textToAdd);
        writer.close();
    }
    /**
     * Adds tasks to list.
     *
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    public void addtask(ArrayList<Task> arrlist) {
        String file = "data/tasks.txt";
        StringBuilder build = new StringBuilder();

        try {
            for (int i = 0; i < arrlist.size(); i++) {
                if (i == arrlist.size() - 1) {
                    build.append(arrlist.get(i).toStringintxt());
                } else {
                    build.append(arrlist.get(i).toStringintxt()+"\n");
                }
            }
            String text = build.toString();
            writeToFile(file, text);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
