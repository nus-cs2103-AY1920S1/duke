import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    String filePath;
    FileWriter fw;
    ArrayList<Task> taskList;

    public Storage(String filepath) throws DukeException{
        this.filePath = filepath;
        this.createWriter();
    }

    private void createWriter() throws DukeException{
        try{
            fw = new FileWriter(filePath, true);
        } catch (IOException e) {
            throw new DukeException((new Border()) + "\n     ☹ invalid filepath \n" + (new Border()));
        }
    }

    public void closeWriter() throws DukeException{
        try {
            fw.close();
        } catch (IOException e) {
            throw new DukeException((new Border()) + "\n     ☹ invalid filepath \n" + (new Border()));
        }
    }

    public void writeToFile(String textToAdd) throws DukeException{
        try {
            fw.write(textToAdd + "\n");
            //System.out.println(textToAdd);
        } catch (IOException e) {
            throw new DukeException((new Border()) + "\n     ☹ Something went wrong: " + e.getMessage() + "\n" + (new Border()));
        }
    }

    public void editFile(String textToAdd) throws DukeException{
        closeWriter();
        createWriter();
        writeToFile(textToAdd);
    }

    private Task read(String line) throws DukeException {
        Task output;
        switch (line.charAt(line.indexOf("[") + 1)) {

            case 'T':
                output = new Todo(line.substring(8, line.length()));
                return output;
            case 'D':
                try {
                    int divider = line.indexOf("(by:");
                    String input = line.substring(7, divider);
                    input += "/by " + DateTime.readDeadLine(line.substring(divider + 5, line.length() - 1)).toString();
                    output = new Deadline(input);
                    return output;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new DukeException((new Border()) +
                            "\n     ☹ OOPS!!! Deadline task not stored properly.\n"
                            + (new Border()));
                }
            default:
                try {
                    int divider = line.indexOf("(at:");
                    String input = line.substring(7, divider);
                    input += "/at " + DateTime.readEventTime(line.substring(divider + 5, line.length() - 1)).toString();
                    System.out.println(input);
                    output = new Event(input);
                    return output;
                } catch (Exception e) {
                    throw new DukeException((new Border()) +
                            "\n     ☹ OOPS!!! Event task not stored properly.\n"
                            + (new Border()));
                }
        }
    }

    public Storage load() throws DukeException {
        try {
            taskList = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            int n = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (! line.equals("")) {
                    Task newTask = read(line.replace("\n", ""));
                    taskList.add(newTask);
                }
            }
            return this;
        } catch (FileNotFoundException e){
            throw new DukeException((new Border())
                    + "\n     ☹ OOPS!!! File not found!\n"
                    + (new Border()));
        }
    }

    public ArrayList<Task> getSchedule(){
        return taskList;
    }
}
