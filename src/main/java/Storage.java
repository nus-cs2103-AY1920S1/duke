import java.io.*;
import java.util.ArrayList;

public class Storage {

    String filePath;
    ArrayList<Task> taskArrayList;

    Storage(String filePath) {
        this.filePath = filePath;
        taskArrayList = new ArrayList<>();
    }

    /**
     * Returns an ArrayList<Task> that will be taken in by a TaskList object
     */
    public ArrayList<Task> load() {
        readData();
        return taskArrayList;
    }

    public boolean listPresent() {
        if (taskArrayList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void readData() {
        try {
            FileReader fileReader = new FileReader("/Users/lawnce/Desktop/duke/data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    taskArrayList.add(createTask(line));
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeData() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/lawnce/Desktop/duke/data/duke.txt"));
            for (Task task : taskArrayList) {
                bufferedWriter.write(task.getData());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Task createTask(String text) {
        String[] splitWords = text.split("\\|");
        if (splitWords[0].equals("T")) {
            String todoText = splitWords[2].substring(1); //substring to remove space
            Todo todo = new Todo(todoText);
            if (splitWords[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        } else if (splitWords[0].equals("D")) {
            String deadlineText = splitWords[2].substring(1); //substring to remove space
            String deadlineTime = splitWords[3].substring(1); //substring to remove space
            Deadline deadline = new Deadline(deadlineText, deadlineTime);
            if (splitWords[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        } else { //event scenario
            String eventText = splitWords[2].substring(1); //substring to remove space
            String eventTime = splitWords[3].substring(1); //substring to remove space
            Event event = new Event(eventText, eventTime);
            if (splitWords[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        }
    }
}
