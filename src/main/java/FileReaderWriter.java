import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class FileReaderWriter {
    private static final String DataBaseLocation = System.getProperty("user.dir") + "/data/duke.csv";
    private static final String CsvDelimiter = "\\s\\|\\s";

    /**
     * Try to load the list of task from the database File.
     * @param myTaskList The destination which the list of Task from the database will be loaded to.
     * @return A boolean indicating whether the list of task were sucessfully loaded from the database.
     */
    public static boolean tryReadFromDataBase(List<Task> myTaskList) throws NullPointerException {

        boolean successfullyLoadedDb = false;

        if (myTaskList == null) {
            throw new NullPointerException("myTaskList should not be Null");
        }

        myTaskList.clear();
        try {
            File db = new File(DataBaseLocation);
            Scanner in = new Scanner(db);
            in.useDelimiter(CsvDelimiter);

            while (in.hasNextLine() && in.hasNext()) {
                String taskType = in.next().trim();

                if (taskType.equals("END")) {
                    successfullyLoadedDb = true;
                    break;
                }

                Task newTask = null;
                boolean markDone = (in.nextInt() != 0);
                String description = in.next();

                switch (taskType) {
                case "T": //Todo Task
                    newTask = new ToDo(description);
                    break;

                case "D": //Deadline Task
                    newTask = new Deadline(description, in.next());
                    break;

                case "E": //Event Task
                    newTask = new Event(description, in.next());
                    break;

                case "":
                    //Ignore Blank line
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + taskType);
                }

                if (newTask != null) {
                    if (markDone) {
                        newTask.markAsDone();
                    }
                    myTaskList.add(newTask);
                }

            }
            in.close();

            System.out.println("[Info]: Successfully loaded DB File");
        } catch (FileNotFoundException ex) {
            System.out.println("[Warning]: Could find DB File");
        } catch (IllegalStateException ex) {
            System.out.println("[Warning]: DB File is corrupt. " + ex.getMessage());
        } catch (NoSuchElementException ex) {
            System.out.println("[Warning]: DB File is corrupt. " + ex.getMessage());
        }

        if (!successfullyLoadedDb) {
            myTaskList.clear();
        }

        return successfullyLoadedDb;
    }

    /**
     * Try to save the given list of task to the database file.
     * @param taskList the list of tasks to be saved.
     * @return A boolean indicating whether the list of task were sucessfully saved to the database.
     */
    public static boolean tryWriteToFile(List<Task> taskList) {
        try {
            File directory = new File(DataBaseLocation).getParentFile();
            if (!directory.exists()) {
                directory.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(DataBaseLocation));
            for (Task myTask : taskList) {
                writer.write(myTask.getDataBaseFormat());
            }
            writer.write("END");
            writer.close();

            System.out.println("[Info]: Saved DB File successfully");
        } catch (IOException ex) {
            System.out.println("[Warning]: Could Write DB File");
            return false;
        }

        return true;
    }

}
