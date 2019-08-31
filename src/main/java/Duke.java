import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Represents the personal assistant and contains the main method.
 */
public class Duke {

    private TaskList list;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Creates a duke object which stores data in the specified path .
     * @param s the absolute path of the file where the data is stored.
     */
    public Duke(String s) {
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(s);
    }

    /**
     * Contains the flow of the program execution.
     * @throws ParseException if date of event class not in specified format.
     * @throws IOException if file not found or other input output exceptions
     */
    public void run() throws ParseException, IOException {
        Scanner scan = new Scanner(System.in);
        this.ui.greet();
        File f = new File("/Users/sairo/OneDrive/Desktop/Duke/Data/Duke.txt");
        Scanner sca = new Scanner(f);
        while (sca.hasNext()) {
            String dat = sca.nextLine();
            this.parser.readTask(dat,this.list);
        }
        while (scan.hasNextLine()) {
            String command = scan.next();
            if (command.equals("bye")) {
                this.ui.exit();
                String s = "";
                for (Task t : this.list.taskList) {
                    s = s + t.toString() + "\n";
                }
                this.storage.writeFile(s);
                break;
            } else if (command.equals("list")) {
                this.list.getList();
            } else if (command.equals("done")) {
                int num = scan.nextInt();
                this.list.markAsDone(num - 1);
                String c = scan.nextLine();
            } else if (command.equals("event")) {
                String b = scan.nextLine();
                this.list.readEvent(b);
            } else if (command.equals("deadline")) {
                String det = scan.nextLine();
                this.list.readDeadline(det);
            } else if (command.equals("todo")) {
                String todoDetails = scan.nextLine();
                this.list.readTodo(todoDetails);
            } else if (command.equals("delete")) {
                int number = scan.nextInt();
                this.list.deleteTask(number);
            } else if (command.equals("find")) {
                String required = scan.next();
                this.list.find(required);
            } else {
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                String empt = scan.nextLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        new Duke("/Users/sairo/OneDrive/Desktop/Duke/Data/Duke.txt").run();
    }
}
