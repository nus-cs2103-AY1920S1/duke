import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class SaveToFile {
    String filePath;
    private static SimpleDateFormat PARSEFILE = new SimpleDateFormat("dd MMMMM yyyy ha");


    public SaveToFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the txt file with the new task list.
     * @param listOfTasks the list of tasks used while the program is running.
     */
    public void updateFile(TaskList listOfTasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Tasks t: listOfTasks.getTaskList()) {
                fw.write(t.toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Loads the information from the text file where previous information is saved.
     * @return
     * @throws DukeException when there is no file/file is empty.
     */
    public TaskList load() throws DukeException {
        TaskList list = new TaskList();
        File f = new File(filePath);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String nextTask = sc.nextLine();
                String[] details = nextTask.split("\\|");

                switch (details[0].trim()) {
                    case "E":
                        try {
                            String edate = details[3].trim().substring(2);
                            Date de = PARSEFILE.parse(edate);
                            list.addTask(new Event(details[2].trim(), de, Integer.parseInt(details[1].trim())));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "D":
                        try {
                            String ddate = details[3].trim().substring(2);
                            Date dd = PARSEFILE.parse(ddate);
                            list.addTask(new Deadline(details[2].trim(), dd, Integer.parseInt(details[1].trim())));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "T":
                        try {
                            list.addTask(new Todo(details[2].trim(), Integer.parseInt(details[1].trim())));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException();
                        }
                        break;

                    default:
                        throw new DukeException();

                }
            }
            sc.close();
        } catch(FileNotFoundException e) {
            f.getParentFile().mkdir();
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            throw new DukeException();
        }

        return list;
    }

}