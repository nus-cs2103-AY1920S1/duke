
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {

    private Ui user;
    private ArrayList<Task> items;

    /**
     * Constructor method for Parser.
     * @param user object handling all messages
     */
    public Parser(Ui user) {
        this.user = user;
        this.items = new ArrayList<>();
    }

    //return Ui obj
    public Ui getUser() {
        return this.user;
    }

    //return list of tasks
    public ArrayList<Task> getList() {
        return this.items;
    }

    /**
     * Deleting of task from ArrayList.
     * @param num index of task in list
     * @return String delete message
     * @throws IndexOutOfBoundsException index of task not found in list
     *
     */
    public String deleteTask(int num) throws IndexOutOfBoundsException {
        Task curr = items.get(num - 1);
        items.remove(num - 1);
        String reply = user.deleteMessage(curr, items.size());
        return reply;
    }

    /**
     * Converting String to datetime for storage.
     * @param input String from user input containing datetime for deadline
     * @return Date containing dd/MM/yyyy HH:mm
     * @throws ParseException when input does not matched date format
     */
    public static Date convertStringToDeadline(String input) throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        return result;
    }

    /**
     * Converting String to datetime for storage.
     * @param input String from user input from date to start time for event
     * @return Date containing dd/MM/yyy HH:mm
     * @throws ParseException input does not matched date format
     */
    public static Date convertStringToEventStart(String input) throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        return result;
    }

    /**
     * Converting String to datetime for storage.
     * @param input String from user input containing end time for event
     * @return Date containing HH:mm
     * @throws ParseException input does not matched date format
     */
    public static Date convertStringToEventEnd(String input) throws ParseException {
        Date result = new SimpleDateFormat("HH:mm").parse(input);
        return result;
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
                message = user.todoError();
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
                message = user.deadlineError();
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
                message = user.eventError();
            }
        } else {
            message = user.invalidInput();
        }
        return message;
    }


    /* run method for cmd version
    public void run() throws FileNotFoundException {
        //Start of program
        user.welcome();
        user.greeting();

        StringBuilder sb = new StringBuilder();

        //Loading stored task into current list
        Scanner pastScan = new Scanner(new FileReader("/Users/teojunhong/JavaProject/2103T/duke/savedList.txt"));
        loadExisting(pastScan);

        //Scanner obj for input
        Scanner sc = new Scanner(System.in);

        //Loop till user input 'bye'
        String input = "";
        if (sc.hasNextLine()) {
            input = sc.nextLine();
        }
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
    */


}
