import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a storage object to handle file input/output.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filepath);
            //testing
            System.out.println(filepath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                Task task;
                String instruction = sc.nextLine();
                String[] lines = instruction.split(" \\| ");

                switch (lines[0]) {
                    case "T":
                        task = new ToDo(lines[2]);
                        break;
                    case "D":
                        task = new Deadline(lines[2], lines[3]);
                        break;
                    case "E":
                        task = new Event(lines[2], lines[3]);
                        break;
                    default:
                        throw new DukeException("The task does not exist.");
                }

                if (lines[1].equals("1")) {
                    task.markAsDone();
                }

                tasks.add(task);
                //testing
                System.out.println(task);
            }

            return tasks;

        } catch (FileNotFoundException ex) {
            throw new DukeException("File is not found");
        }

    }

    public void save(TaskList tasklist) throws DukeException {
        try {
            FileWriter filewriter = new FileWriter(filepath);

            for (Task task : tasklist.list) {
                filewriter.write(task.textFormat());
                //testing
                System.out.println(task.textFormat());

                filewriter.write(System.lineSeparator());
            }

            filewriter.close();

        } catch (IOException e) {
            throw new DukeException("Failed to save tasks.");
        }
    }


}
