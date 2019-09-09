import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path p = Paths.get(System.getProperty("user.dir"));
    private File data = new File(p + "/data/duke.txt");

    public Storage() {
    }


    public ArrayList<Task> readFromFile() throws FileNotFoundException {
        ArrayList<Task> toReturn = new ArrayList<>();

        try {
            Scanner sc = new Scanner(data);

            while(sc.hasNextLine()) {
                String[] next = sc.nextLine().split("-");

                switch (next[0]) {
                case ("T"):
                    Task todo = new Todo(next[2]);

                    if (Integer.valueOf(next[1]) == 1) {
                        todo.setDone();
                    }
                    toReturn.add(todo);
                    break;


                case ("E"):
                    Task event = new Event(next[2], next[3]);

                    if (Integer.valueOf(next[1]) == 1) {
                        event.setDone();
                    }
                    toReturn.add(event);
                    break;

                case ("D"):
                    Task deadline = new Deadline(next[2], next[3]);

                    if (Integer.valueOf(next[1]) == 1) {
                        deadline.setDone();
                    }
                    toReturn.add(deadline);
                    break;
                }
            }
            sc.close();
        } catch(FileNotFoundException fE) {
            System.out.println(fE);
        }

        return toReturn;
    }

    public void writeToFile(ArrayList<Task> list) throws FileNotFoundException, IOException {
        try {
            Path p = Paths.get(System.getProperty("user.dir"));
            File data = new File(p + "/data/duke.txt");
            FileWriter fw = new FileWriter(data);

            String toWrite = "";
            for(Task task : list) {
                toWrite += task.toFile() + " \n";
            }

            fw.write(toWrite);
            fw.close();
        } catch (FileNotFoundException Fe) {
            System.out.println(Fe);
        } catch (IOException IOe) {
            System.out.println(IOe);
        }
    }

}
