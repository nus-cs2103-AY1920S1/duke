import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private Parser parse;
    private Ui user;
    private ArrayList<Task> items;
    private Scanner pastScan;
    private File relative;

    /**
     * Constructor for Storage.
     * @param p Parser obj which handle all the processing of input
     * @throws FileNotFoundException in the event the textfile is not located
     */
    public Storage(Parser p) throws FileNotFoundException {
        parse = p;
        user = p.getUser();
        items = p.getList();
        relative = new File("savedList.txt");
        if(!relative.exists()) {
            try {
                relative.createNewFile();
            } catch (IOException e) {
                System.out.println("File cannot be created");
            }

        }
        pastScan = new Scanner(new FileReader(relative));
    }

    /**
     * Load past tasks to current ArrayList.
     */
    public void loadExisting() {
        StringBuilder sb = new StringBuilder();
        while (pastScan.hasNextLine()) {
            String input = pastScan.nextLine();
            if (input.toLowerCase().contains("[t]")) {
                //adding an item
                try {
                    Todo newTask = new Todo(input.substring(7, input.length() - 1));
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    user.todoError();
                }

            } else if (input.toLowerCase().contains("[d]")) {
                try {
                    int date = input.indexOf("(by");
                    Date deadLineDate = parse.convertStringToDeadline(input.substring(date + 4));
                    Deadline newTask = new Deadline(input.substring(7, date), deadLineDate);
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException | ParseException e) {
                    user.deadlineError();
                }

            } else if (input.toLowerCase().contains("[e]")) {
                try {
                    int time = input.indexOf("(at");
                    int timeRange = input.indexOf("-");
                    Date eventDate = parse.convertStringToEventStart(input.substring(time + 4, timeRange));
                    Date eventEnd = parse.convertStringToEventEnd(input.substring(timeRange + 1));
                    //split input into [event] [description] [timing]
                    Event newTask = new Event(input.substring(7, time), eventDate, eventEnd);
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException | ParseException e) {
                    user.eventError();
                }
            }
        }
    }

    /**
     * Store current tasks into text file.
     * @param inputs Arraylist containing current tasks
     */
    public void storeCurrent(ArrayList<Task> inputs) {
        try {
            FileWriter fw = new FileWriter(relative);
            PrintWriter pw = new PrintWriter(fw);
            for (Task input : inputs) {
                int status = 0;
                if (input.isDone) {
                    status = 1;
                }
                pw.println(input.toString() + "" + status);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
