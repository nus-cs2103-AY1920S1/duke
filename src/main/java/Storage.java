import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/* THINGS THAT NEED TO BE ADDED:
    1. load function that loads ArrayList in
    2.
 */

public class Storage {
    private PrintWriter writer;
    private String filename;

    public Storage(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        this.filename = filename;
//        createTodoFile();
    }

    public void createTodoFile() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(filename, "UTF-8");
    }

    // basically takes file and returns tasks
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasklist = new ArrayList<>();
        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        while(scanner.hasNextLine()) {
            scanner.next();
            String[] inputArr = scanner.nextLine().trim().split(" ", 3);
            char taskType = inputArr[0].charAt(1);
            boolean isTaskComplete = (inputArr[1] == "[\u2713]") ? true : false;
            if(taskType == 'T') {
                tasklist.add(new Task(inputArr[2], "todo"));
            } else if(taskType == 'D') {
                String deadline = inputArr[2].split(" /by ")[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime deadlineByDateTime = LocalDateTime.parse(deadline, formatter);
                tasklist.add(new Task(inputArr[2].split(" /by ")[0], "deadline", deadlineByDateTime));
            } else {
                String eventDateTime = inputArr[2].split(" /at ")[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime eventDateTimeByDateTime = LocalDateTime.parse(eventDateTime, formatter);
                tasklist.add(new Task(inputArr[2].split(" /at ")[0], "event", eventDateTimeByDateTime));
            }
        }
        return tasklist;
    }

    public void updateTodoFile(String todoString) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(filename, "UTF-8");
        writer.printf(todoString);
        writer.close();
    }
}
