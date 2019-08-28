import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

public class Storage {

    public final String USER_NAME = System.getProperty("user.name");
    private String filePath = "C:\\Users\\" + USER_NAME + "\\Documents\\GitHub\\duke\\data.dat"; 
    private DateParser dateParser;

    public Storage (DateParser dateParser) {
        this.dateParser = dateParser;
    }

	public TaskList readDataFile() {

        Scanner inStream;
        TaskList taskList = new TaskList(Duke.MAX_TASKS);

        try {
            inStream = new Scanner(new FileInputStream(filePath));

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();

                switch (line.charAt(0)) {
                case 'T':
                    taskList.add(new Todo(line.substring(2, line.length())));
                    break;
                case 'E':
                    String eLine = line.substring(2, line.length());
                    taskList.add(new Event(eLine.split("/")[0], dateParser.parse(eLine.split("/")[1])));
                    break;
                case 'D':
                    String dLine = line.substring(2, line.length());
                    taskList.add(new Deadline(dLine.split("/")[0], dateParser.parse(dLine.split("/")[1])));
                    break;
                default:
                    break;
                }

                if (line.charAt(1) == '1') {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }

            inStream.close();
            return taskList;
        }
        catch (FileNotFoundException e) {
            System.out.println("Data file not found, starting with empty task list");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return new TaskList(Duke.MAX_TASKS);
    }

    public void writeDataFile(TaskList taskList) {
        
        PrintWriter outStream;

        try {
            outStream = new PrintWriter(new FileOutputStream(filePath));

            for (Task t : taskList) {
                outStream.println(saveTask(t));
            }

            outStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String saveTask(Task t) {

        String doneChar = (t.isDone ? "1" : "0");
        String classChar;
        String timeStr;

        if (t instanceof Todo) {
            classChar = "T";
        } else if (t instanceof Event) {
            classChar = "E";
        } else if (t instanceof Deadline) {
            classChar = "D";
        } else {
            classChar = " ";
        }

        if (t instanceof Event) {
            timeStr = "/" + dateParser.format(((Event)t).getAt());
        } else if (t instanceof Deadline) {
            timeStr = "/" + dateParser.format(((Deadline)t).getBy());
        } else {
            timeStr = "";
        }

        return classChar + doneChar + t.getDescription() + timeStr;
    }
}