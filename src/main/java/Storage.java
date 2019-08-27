import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    /**
     * Storage constructor.
     *
     * @param filepath path of data file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Initially populates the Task List with the hard drive data (Input data file).
     * @param list ArrayList to be populated.
     * @param line A given line in the input file.
     */
    private void initialPopulate(ArrayList<Task> list, String line) throws DukeException {
        String[] arr = line.split(" \\| ", 4);
        String cmd = arr[0];
        boolean isDone = Integer.parseInt(arr[1]) != 0;
        switch (cmd) {
            case "T":
                Task todo = new Todo(arr[2]);
                if (isDone) {
                    todo.markAsDone();
                }
                list.add(todo);
                break;
            case "D":
                String[] datetime = arr[3].split(" ", 2);
                Task deadline = new Deadline(arr[2], datetime[0], datetime[1]);
                if (isDone) {
                    deadline.markAsDone();
                }
                list.add(deadline);
                break;
            case "E":
                Task event = new Event(arr[2], arr[3]);
                if (isDone) {
                    event.markAsDone();
                }
                list.add(event);
                break;
            default:
                throw new DukeException("Invalid Task Type");
        }
    }

    /**
     * Initial seeding of the data from hard drive memory.
     *
     * @throws DukeException DukeException class.
     * @return seeded ArrayList.
     */
    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    throw new DukeException("Data file not created");
                }
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                initialPopulate(list, line);
            }
            fileReader.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * Method to override the hard drive memory with the given list.
     *
     * @param tasklist given tasklist.
     * @throws DukeException custom DukeException.
     */
    public void updateData(TaskList tasklist) throws DukeException {
        try {
            ArrayList<Task> list = tasklist.getlist();
            FileWriter fw = new FileWriter(this.filepath);
            for (Task task : list) {
                String data = String.format("%s\n", task.fileFormat());
                fw.write(data);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

}

