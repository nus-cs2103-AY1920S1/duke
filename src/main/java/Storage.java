import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

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

        while(scanner.hasNext()) {
            scanner.next();
            if(!scanner.hasNext()) {
                break;
            }
            String[] inputArr = scanner.nextLine().trim().split(" ", 3);
            char taskType = inputArr[0].charAt(1);
            boolean isTaskComplete = (inputArr[1].equals("[\u2713]")) ? true : false;
            if(taskType == 'T') {
                tasklist.add(new Task(inputArr[2], "todo", isTaskComplete));
            } else if(taskType == 'D') {
                String deadlineDetails = inputArr[2].split("\\(")[0];
                String deadline = inputArr[2].split("\\(")[1].split(" ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm)");
                LocalDateTime deadlineByDateTime = LocalDateTime.parse(deadline, formatter);
                tasklist.add(new Task(deadlineDetails, "deadline", deadlineByDateTime, isTaskComplete));
            } else {
                String eventDetails = inputArr[2].split("\\(")[0];
                String eventDateTime = inputArr[2].split("\\(")[1].split(" ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm)");
                LocalDateTime eventDateTimeByDateTime = LocalDateTime.parse(eventDateTime, formatter);
                tasklist.add(new Task(eventDetails, "event", eventDateTimeByDateTime, isTaskComplete));
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
