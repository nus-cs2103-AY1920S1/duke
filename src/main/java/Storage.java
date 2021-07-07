import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    /**
     * handles then loading of hard-disk data and saving of the data when task list is updated.
     */

    private String filepath;
    private ArrayList<Task> taskList;

    /**
     * constructor, takes in path of the file.
     * @param filepath of the file.
     */
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * loads the data from hard-disk file.
     * @return an array list of task to be passed into TaskList.
     * @throws FileNotFoundException when input format is different from required.
     * @throws ParseException when input format is different from required.
     */
    ArrayList<Task> load() throws FileNotFoundException, ParseException, InvalidTaskException {

        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filepath); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        while (sc.hasNext()) {
            Task t;
            String type = sc.next();
            int done = sc.nextInt();
            String[] nameAndDate = sc.nextLine().split("@");

            switch(type) {
                case "T":
                    t = new Todo(nameAndDate[0]);
                    break;
                case "D":
                    t = new Deadline(nameAndDate[0], nameAndDate[1]);
                    break;
                case "E":
                    t = new Event(nameAndDate[0], nameAndDate[1]);
                    break;
                default:
                    throw new InvalidTaskException();
            }

            if (done == 1) {
                t.markAsDone();
            }
            taskList.add(t);
        }
        this.taskList = taskList;
        return taskList;
    }

    /**
     * update the file when task list is changed.
     * @throws IOException when input format is different from required.
     */
     void save() throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task task : taskList) {
            fw.write(task.getStoredForm());
        }
        fw.close();
    }
}
