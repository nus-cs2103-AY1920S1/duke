import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents the class used to read data from the file and process it.
 */
public class Parser {

    public Parser() {

    }

    /**
     * Contains the methods to read the input and process it into the form required for the algorithm.
     *
     * @param s The information to be read and processed
     * @param t The list where the information is stored after being processed
     * @throws ParseException If date for event is not in the specified format i.e. MM/dd/yyyy HH:mm
     */
    public void readTask(String s, TaskList t) throws ParseException {
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");

        if (s.contains("[T]")) {
            if (s.contains("[" + "1" + "]")) {
                t.add(new ToDo(s.substring(7), true));
            } else {
                t.add(new ToDo(s.substring(7)));
            }
        } else if (s.contains("[E]")) {
            int start = s.indexOf('(');
            String e = s.substring(7, start - 1);
            String dl = s.substring(start + 5, start + 27);
            Date at = df.parse(dl);
            if (s.contains("[" + "1" + "]")) {
                t.add(new Event(e, at, true));
            } else {
                t.add(new Event(e, at));
            }
        } else {
            int sl = s.indexOf('(');
            String d = s.substring(7, sl - 1);
            int sec = s.indexOf(')');
            String by = s.substring(sl + 5, sec);
            if (s.contains("[" + "1" + "]")) {
                t.add(new Deadline(d, by, true));
            } else {
                t.add(new Deadline(d, by));
            }
        }
    }

    /**
     * Method to read and process user input.
     * @param ui the UI of the Duke object that interacts with the user
     * @param list the task list of the Duke object which stores the tasks
     * @param storage the object responsible for reading file data and saving data to file when user exits
     * @throws IOException if File not found or other input output exceptions
     * @throws ParseException if thee data for event type of tasks not in the specified format
     */
    public void readUserCommand(Ui ui, TaskList list, Storage storage) throws IOException, ParseException {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String command = scan.next();
            if (command.equals("bye")) {
                ui.exit();
                String writeDataToFile = "";
                for (Task t : list.taskList) {
                    writeDataToFile = writeDataToFile + t.toString() + "\n";
                }
                storage.writeFile(writeDataToFile);
                break;
            } else if (command.equals("list")) {
                list.getList();
            } else if (command.equals("done")) {
                int numberToBeMarked = scan.nextInt();
                list.markAsDone(numberToBeMarked - 1);
                String c = scan.nextLine();
            } else if (command.equals("event")) {
                String eventGiven = scan.nextLine();
                list.readEvent(eventGiven);
            } else if (command.equals("deadline")) {
                String deadlineGiven = scan.nextLine();
                list.readDeadline(deadlineGiven);
            } else if (command.equals("todo")) {
                String todoDetails = scan.nextLine();
                list.readTodo(todoDetails);
            } else if (command.equals("delete")) {
                int numberToBeDeleted = scan.nextInt();
                list.deleteTask(numberToBeDeleted);
            } else if (command.equals("find")) {
                String requiredWord = scan.next();
                list.find(requiredWord);
            } else {
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                String emptyLine = scan.nextLine();
            }
        }
    }
}
