import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class SaveToFile {
    String filePath;

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
                fw.write(t.toString() + "\n");
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
    public ArrayList<Tasks> load() throws DukeException {
        ArrayList<Tasks> taskList = new ArrayList<>();
        /*Scanner sc = new Scanner(filePath);
        while (sc.hasNext()) {
            String nextTask = sc.nextLine();
            String[] details = nextTask.split(" | ");

            switch (details[0]) {
            case "E":
                try {
                    taskList.add(new Event(details[2], details[3], Integer.parseInt(details[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            case "D":
                try {
                    taskList.add(new Deadline(details[2], details[3], Integer.parseInt(details[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            case "T":
                try {
                    taskList.add(new Todo(details[2], Integer.parseInt(details[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException();
                }
                break;

            default:
                throw new DukeException();
            }
        } */
        return taskList;
    }

}