import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class SaveManager {
    private static File file = new File("data/duke.txt");

    protected static void saveTasks(ArrayList<Task> tasks) {
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);

            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    String str = String.format("D|%d|%s|%s\n", task.isDone ? 1 : 0, task.description,
                            ((Deadline) task).by);
                    fw.write(str);
                } else if (task instanceof Event) {
                    String str = String.format("E|%d|%s|%s\n", task.isDone ? 1 : 0, task.description,
                            ((Event) task).at);
                    fw.write(str);
                } else {
                    String str = String.format("T|%d|%s\n", task.isDone ? 1 : 0, task.description);
                    fw.write(str);
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    protected static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] strings = sc.nextLine().split("\\|");
                switch (strings[0]) {
                case "T":
                    tasks.add(new Todo(strings[2], strings[1].equals("1")));
                    break;
                case "D":
                    tasks.add(new Deadline(strings[2], strings[3], strings[1].equals("1")));
                    break;
                case "E":
                    tasks.add(new Event(strings[2], strings[3], strings[1].equals("1")));
                    break;
                default:
                    break;
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return tasks;
    }
}