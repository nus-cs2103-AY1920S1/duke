import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    /**
     * Manages file management.
     * Will load, update, and append the associated .txt file.
     * @param filePath refers to the path of the .txt file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the .txt file into the program.
     * @return ArrayList of Task specified in the .txt file
     * @throws IOException when unable to resolve date
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] task = sc.nextLine().replaceAll(", ", ",").split(",");

            if (task[0].equals("T")) {
                Task tsk = new Todo(task[2]);
                if (task[1].equals("1")) {
                    tsk.markAsDone();
                }
                list.add(tsk);
            } else if (task[0].equals("D")) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd HH:mm:ss z yyyy");
                    Task tsk = new Deadline(task[2], formatter.parse(task[3]));
                    if (task[1].equals("1")) {
                        tsk.markAsDone();
                    }
                    list.add(tsk);
                } catch (java.text.ParseException exp) {
                    exp.printStackTrace();
                    break;
                }
            } else {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd HH:mm:ss z yyyy");
                    Task tsk = new Event(task[2], formatter.parse(task[3]));
                    if (task[1].equals("1")) {
                        tsk.markAsDone();
                    }
                    list.add(tsk);
                } catch (java.text.ParseException exp) {
                    exp.printStackTrace();
                    break;
                }
            }
        }
        sc.close();
        return list;
    }

    /**
     * Appends new task specified by the user into the file.
     * @param tsk refers to the tasklist
     * @throws IOException when unable to read file
     */
    public void append(Task tsk) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        String status = tsk.getIsDone() ? "1" : "0";

        try {
            if (tsk instanceof Todo) {
                fileWriter.write("T, " + status + ", " + tsk.getDescription() + "\n");
            } else if (tsk instanceof Deadline) {
                fileWriter.write("D, " + status + ", " + tsk.getDescription() + ", " + ((Deadline) tsk).getBy() + "\n");
            } else if (tsk instanceof Event) {
                fileWriter.write("E, " + status + ", " + tsk.getDescription() + ", " + ((Event) tsk).getAt() + "\n");
            } else {
                throw new DukeException("☹ OOPS! Error in file handling");
            }
        } catch (DukeException exp) {
            System.out.println(exp.getMessage());
        }
        fileWriter.close();
    }

    /**
     * Updates the tasks in the file.
     * @param list refers to the tasklist
     * @throws IOException when unable to read file
     */
    public void update(ArrayList<Task> list) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        int counter = 0;
        while (counter < list.size()) {
            Task tsk = list.get(counter);
            String status = tsk.getIsDone() ? "1" : "0";
            try {
                if (tsk instanceof Todo) {
                    bufferedWriter.write("T, " + status + ", " + tsk.getDescription());
                    bufferedWriter.newLine();
                    counter++;
                } else if (tsk instanceof Deadline) {
                    bufferedWriter.write("D, " + status + ", " + tsk.getDescription() + ", "
                            + ((Deadline) tsk).getBy());
                    bufferedWriter.newLine();
                    counter++;
                } else if (tsk instanceof Event) {
                    bufferedWriter.write("E, " + status + ", " + tsk.getDescription() + ", "
                            + ((Event) tsk).getAt());
                    bufferedWriter.newLine();
                    counter++;
                } else {
                    throw new DukeException("☹ OOPS! Error in file handling");
                }
            } catch (DukeException exp) {
                System.out.println(exp);
            }
        }
        bufferedWriter.close();
    }
}
