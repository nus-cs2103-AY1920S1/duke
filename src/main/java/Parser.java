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
    Scanner scan = new Scanner(System.in);
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
     *
     * @param ui the user interface interacting with the user
     * @param list the list where tasks are stored
     * @param storage the object responsible for writing data to file
     * @throws IOException if file not found or other input output exceptions
     * @throws ParseException if data in event class not according to format
     */
    public void readUserCommand(Ui ui, TaskList list, Storage storage) throws IOException, ParseException {
        while(ui.shouldContinue()){
            String command = scan.next();
            if (command.equals("bye")) {
                ui.Exit();
                String s = "";
                for (Task t : list.taskList) {
                    s = s + t.toString() + "\n";
                }
                storage.writeFile(s);
            } else if (command.equals("list")) {
                list.getList();
            } else if (command.equals("done")) {
                int num = scan.nextInt();
                list.markAsDone(num - 1);
            } else if (command.equals("event")) {
                String b = scan.nextLine();
                list.readEvent(b);
            } else if (command.equals("deadline")) {
                String det = scan.nextLine();
                list.readDeadline(det);
            } else if (command.equals("todo")) {
                String todoDetails = scan.nextLine();
                list.readTodo(todoDetails);
            } else if (command.equals("delete")) {
                int number = scan.nextInt();
                String empty = scan.nextLine();
                list.deleteTask(number);
            } else if (command.equals("find")) {
                String required = scan.next();
                String empty = scan.nextLine();
                list.find(required);
            } else {
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                String empty = scan.nextLine();
            }
        }
    }
}
