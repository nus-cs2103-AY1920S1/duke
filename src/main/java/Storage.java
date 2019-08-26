import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath = "";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> outputList = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String savedTask = s.nextLine();
                String[] savedTaskSplit = savedTask.split("\\|");
                if (savedTaskSplit[0].equals("T")) {
                    String description = savedTaskSplit[2];
                    Task todoTask = new Todo(description);
                    if (savedTaskSplit[1].equals("1")) {
                        todoTask.markAsDone();
                    }
                    outputList.add(todoTask);
                } else if (savedTaskSplit[0].equals("E")) {
                    String description = savedTaskSplit[2];
                    String at = savedTaskSplit[3];
                    Task eventTask = new Event(description, at);
                    if (savedTaskSplit[1].equals("1")) {
                        eventTask.markAsDone();
                    }
                    outputList.add(eventTask);
                } else {
                    String description = savedTaskSplit[2];
                    String by = savedTaskSplit[3];
                    Task deadlineTask = new Deadline(description, by);
                    if (savedTaskSplit[1].equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    outputList.add(deadlineTask);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } finally {
            return outputList;
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void updateChanges(ArrayList<Task> list) {

        StringBuilder sb = new StringBuilder();

        try {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i).toTextFileString());
                } else {
                    sb.append(list.get(i).toTextFileString());
                    sb.append("\n");
                }
            }
            String content = sb.toString();
            writeToFile(content);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
