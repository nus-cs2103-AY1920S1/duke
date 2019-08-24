import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {

    private static String border = "-------------------------------------";
    private static ArrayList<Task> items;
    private Ui user;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        user = new Ui();
        items = new ArrayList<>();
    }

    /**
     * Main method for Duke.
      * @param args user input
     */
    public static void main(String[] args) {
        Duke execute = new Duke();

        try {
            execute.run();
        } catch (FileNotFoundException e) {
            System.out.println("Stored file not found!!!");
        }
    }

    /**
     * Runnable method containing main process.
     *
     * @throws FileNotFoundException retrieving file that stored existing tasks
     */
    public void run() throws FileNotFoundException {
        //Start of program
        user.greeting();

        StringBuilder sb = new StringBuilder();

        //Loading stored task into current list
        Scanner pastScan = new Scanner(new FileReader("/Users/teojunhong/JavaProject/2103T/duke/savedList.txt"));
        loadExisting(pastScan);

        //Scanner obj for input
        Scanner sc = new Scanner(System.in);

        //Loop till user input 'bye'
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {

            //adding items to arraylist for listing purpose
            if (input.toLowerCase().contains("done")) {
                try {
                    //mark task as done, change cross to tick
                    int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                    Task curr = items.get(itemNum - 1);
                    curr.markAsDone();
                    //forming the message
                    user.doneMessage(curr);
                    sb.setLength(0);
                    input = sc.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    user.indexError();
                    input = sc.nextLine();
                } catch (NumberFormatException e) {
                    user.emptyError();
                    input = sc.nextLine();
                }
            } else if (input.toLowerCase().contains("delete")) {
                try {
                    //delete task
                    int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                    deleteTask(itemNum);
                    input = sc.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    user.indexError();
                    input = sc.nextLine();
                } catch (NumberFormatException e) {
                    user.emptyError();
                    input = sc.nextLine();
                }
            } else if (input.toLowerCase().equals("list")) {
                //listing all task in current list
                user.listTask(items);
                input = sc.nextLine();
            } else if (input.toLowerCase().contains("find")) {
                String searchTerm = input.substring(5);
                user.findTask(items, searchTerm);
                input = sc.nextLine();
            } else {
                    //generate task and store into list
                    generateTask(input);
                    input = sc.nextLine();
                }
            }

        //store current tasks into local file for future reference
        storeCurrent(items);

        //Concluding Message
        user.conclusion();
    }

    /**
     * Generation of task - Todo, Event, Deadline and store in ArrayList.
     * @param input String input from user input
     * @return output message for different task
     */
    public String generateTask(String input) {
        StringBuilder sb = new StringBuilder();
        String message = "";
        if (input.toLowerCase().contains("todo")) {
            //adding an item
            try {
                Todo newTask = new Todo(input.substring(5));
                items.add(newTask);
                message = user.generateMessage(newTask, items.size());
                System.out.println(message);
            } catch (StringIndexOutOfBoundsException e) {
                sb.append(border + "\n");
                sb.append("Todo must have valid description\n");
                sb.append(border + "\n");
                message = sb.toString();
                System.out.println(message);
                sb.setLength(0);
            }
        } else if (input.toLowerCase().contains("deadline")) {
            try {
                int date = input.indexOf("/by");
                //split input into [deadline] [description] [date]
                Date deadLineDate = convertStringToDeadline(input.substring(date + 3));
                Deadline newTask = new Deadline(input.substring(9, date), deadLineDate);
                items.add(newTask);
                message = user.generateMessage(newTask, items.size());
                System.out.println(message);
            } catch (StringIndexOutOfBoundsException | ParseException e) {
                sb.append(border + "\n");
                sb.append("Invalid Deadline's arguments \n");
                sb.append(border + "\n");
                message = sb.toString();
                System.out.println(message);
                sb.setLength(0);
            }

        } else if (input.toLowerCase().contains("event")) {
            try {
                int time = input.indexOf("/at");
                int timeRange = input.indexOf("-");
                //split input into [event] [description] [timing]
                Date eventDate = convertStringToEventStart(input.substring(time + 3, timeRange));
                Date eventEnd = convertStringToEventEnd(input.substring(timeRange + 1));
                Event newTask = new Event(input.substring(6, time), eventDate, eventEnd);
                items.add(newTask);
                message = user.generateMessage(newTask, items.size());
                System.out.println(message);
            } catch (StringIndexOutOfBoundsException | ParseException e) {
                sb.append(border + "\n");
                sb.append("Invalid Event's arguments \n");
                sb.append(border + "\n");
                message = sb.toString();
                System.out.println(message);
                sb.setLength(0);
            }
        } else {
            sb.append(border + "\n");
            sb.append("Unable to understand. Invalid Input. \n");
            sb.append(border + "\n");
            message = sb.toString();
            System.out.println(message);
            sb.setLength(0);
        }
        return message;
    }


    /**
     * Load past tasks to current ArrayList.
     * @param stored Scanner obj scanning local text file containing past tasks
     */
    private static void loadExisting(Scanner stored) {
        StringBuilder sb = new StringBuilder();
        while (stored.hasNextLine()) {
            String input = stored.nextLine();
            if (input.toLowerCase().contains("[t]")) {
                //adding an item
                try {
                    Todo newTask = new Todo(input.substring(7, input.length() - 1));
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Todo must have valid description\n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }

            } else if (input.toLowerCase().contains("[d]")) {
                try {
                    int date = input.indexOf("(by");
                    Date deadLineDate = convertStringToDeadline(input.substring(date + 4));
                    Deadline newTask = new Deadline(input.substring(7, date), deadLineDate);
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException | ParseException e) {
                    sb.append(border + "\n");
                    sb.append("Invalid Deadline's arguments \n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }

            } else if (input.toLowerCase().contains("[e]")) {
                try {
                    int time = input.indexOf("(at");
                    int timeRange = input.indexOf("-");
                    Date eventDate = convertStringToEventStart(input.substring(time + 4, timeRange));
                    Date eventEnd = convertStringToEventEnd(input.substring(timeRange + 1));
                    //split input into [event] [description] [timing]
                    Event newTask = new Event(input.substring(7, time), eventDate, eventEnd);
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException | ParseException e) {
                    sb.append(border + "\n");
                    sb.append("Invalid Event's arguments \n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }

    /**
     * Store current tasks into text file.
     * @param inputs Arraylist containing current tasks
     */
    private static void storeCurrent(ArrayList<Task> inputs) {
        try {
            File file = new File("/Users/teojunhong/JavaProject/2103T/duke/savedList.txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for (Task input : inputs) {
                int status = 0;
                if (input.isDone) {
                    status = 1;
                }
                pw.println(input + " " + status);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deleting of task from ArrayList.
     * @param num index of task in list
     * @return output message notifying user of deletion
     * @throws IndexOutOfBoundsException index of task not found in list
     */
    public String deleteTask(int num) throws IndexOutOfBoundsException {
        Task curr = items.get(num - 1);
        items.remove(num - 1);
        String delete = user.deleteMessage(curr, items.size());
        return delete;
    }

    /**
     * Converting String to datetime for storage.
     * @param input String from user input containing datetime for deadline
     * @return Date containing dd/MM/yyyy HH:mm
     * @throws ParseException when input does not matched date format
     */
    private static Date convertStringToDeadline(String input) throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        return result;
    }

    /**
     * Converting String to datetime for storage.
     * @param input String from user input from date to start time for event
     * @return Date containing dd/MM/yyy HH:mm
     * @throws ParseException input does not matched date format
     */
    private static Date convertStringToEventStart(String input) throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        return result;
    }

    /**
     * Converting String to datetime for storage.
     * @param input String from user input containing end time for event
     * @return Date containing HH:mm
     * @throws ParseException input does not matched date format
     */
    private static Date convertStringToEventEnd(String input) throws ParseException {
        Date result = new SimpleDateFormat("HH:mm").parse(input);
        return result;
    }

}

