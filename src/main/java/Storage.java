import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;

public class Storage {
	
    public static final String USER_NAME = System.getProperty("user.name");
    private static String filePath = "C:\\Users\\" + USER_NAME + "\\Documents\\GitHub\\duke\\data.dat"; 

	public static ArrayList<Task> readDataFile() {

        Scanner inStream;
        ArrayList<Task> taskList = new ArrayList<Task>(Duke.MAX_TASKS);

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
                    taskList.add(new Event(eLine.split("/")[0], DateParser.parse(eLine.split("/")[1])));
                    break;
                case 'D':
                    String dLine = line.substring(2, line.length());
                    taskList.add(new Deadline(dLine.split("/")[0], DateParser.parse(dLine.split("/")[1])));
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

        return new ArrayList<Task>(Duke.MAX_TASKS);
    }

    public static void writeDataFile(ArrayList<Task> taskList) {
        
        PrintWriter outStream;

        try {
            outStream = new PrintWriter(new FileOutputStream(filePath));

            for (Task t : taskList) {
                outStream.println(t.save());
            }

            outStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}