import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File f = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        Scanner s = new Scanner(f);
        while(s.hasNextLine()) {
            String[] task = s.nextLine().replaceAll(", ", ",").split(",");
            Task tsk;
            if(task[0].equals("T")) {
                tsk = new Todo(task[2]);
            } else if(task[0].equals("D")) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd HH:mm:ss z yyyy");
                    tsk = new Deadline(task[2], formatter.parse(task[3]));
                } catch (java.text.ParseException exp) {
                    exp.printStackTrace();
                    break;
                }
            } else {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd HH:mm:ss z yyyy");
                    tsk = new Event(task[2], formatter.parse(task[3]));
                } catch (java.text.ParseException exp) {
                    exp.printStackTrace();
                    break;
                }

            }

            if(task[1].equals("1")) {
                tsk.markAsDone();
            }
            list.add(tsk);
        }
        s.close();
        return list;
    }

    public void append(Task tsk) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String status = tsk.getIsDone() ? "1" : "0";

        try {
            if (tsk instanceof Todo) {
                fw.write("T, " + status + ", " + tsk.getDescription() + "\n");
            } else if (tsk instanceof Deadline) {
                fw.write("D, " + status + ", " + tsk.getDescription() + ", " + ((Deadline) tsk).getBy() + "\n");
            } else if (tsk instanceof Event) {
                fw.write("E, " + status + ", " + tsk.getDescription() + ", " + ((Event) tsk).getAt() + "\n");
            } else {
                throw new DukeException("☹ OOPS! Error in file handling");
            }
        } catch (DukeException exp) {
            System.out.println(exp);
        }
        fw.close();
    }

    public void update(ArrayList<Task> list) throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter(filePath));
        int counter = 0;
        while(counter < list.size()) {
            Task tsk = list.get(counter);
            String status = tsk.getIsDone() ? "1" : "0";
            try {
                if (tsk instanceof Todo) {
                    bfw.write("T, " + status + ", " + tsk.getDescription());
                    bfw.newLine();
                    counter++;
                } else if (tsk instanceof Deadline) {
                    bfw.write("D, " + status + ", " + tsk.getDescription() + ", " + ((Deadline) tsk).getBy());
                    bfw.newLine();
                    counter++;
                } else if (tsk instanceof Event) {
                    bfw.write("E, " + status + ", " + tsk.getDescription() + ", " + ((Event) tsk).getAt());
                    bfw.newLine();
                    counter++;
                } else {
                    throw new DukeException("☹ OOPS! Error in file handling");
                }
            } catch (DukeException exp) {
                System.out.println(exp);
            }
        }
        bfw.close();
    }
}
