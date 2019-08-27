import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


/*
 TO DO:
 ADD EXCEPTION SUPPORT
 */
public class Duke {
    // define arraylist to store tasks
    private ArrayList<Task> taskArr = new ArrayList<>();
    //private static final String FILEPATH = "C:\\Users\\Seb\\duke\\storage\\duke.txt";
    //private WriteFile data = new WriteFile(FILEPATH,false);
    public Duke() {
        /**
         *  prints Intro message in between 2 solid lines
         *  @return none
         */
        printLine();
        System.out.println("Hello, I'm Duke\nWhat can I do for you?");
        printLine();
    }
    private void printLine() {
        /**
         *  helper function, prints formatted solid line
         *  @return none
         */
        System.out.println("____________________________________________________________");
    }
    private String list() {
        /**
         *  returns all tasks in readable, formatted string
         *  @return string of all tasks with new line and spaces as required
         */
        String s = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskArr.size(); i++) {
            // printInt to put number for printing
            int printInt = i + 1;
            Task currTask = taskArr.get(i);
            s += currTask.printTask() + "\n";
        }
        return s;
    }
    private void todo(String taskInfo) {
        // TODO add DukeException for this
        /**
         *  creates new To Do, add to tasklist
         *  prints confirmation message
         *  and prints formatted to do string
         *  then total num of current tasks
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        if (taskInfo.equals("")) {
            // error handling
            System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        ToDo newToDo = new ToDo(taskInfo,"T","");
        taskArr.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.print(" " + newToDo.printTask() + "\n");
        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
    }
    private void deadline(String taskInfo) {
        // TODO add DukeException for this
        /**
         *  creates new Deadline, add to tasklist
         *  prints confirmation message
         *  and prints formatted deadline string
         *  then total num of current tasks
         *  @param String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        int sep = taskInfo.indexOf('/');
        // use sep to split string
        String actualTask = taskInfo.substring(0,sep);
        sep += 4; // put sep at space after /by
        String time = taskInfo.substring(sep);
        Deadline newTing = new Deadline(actualTask,"D",time);
        taskArr.add(newTing);
        System.out.println("Got it. I've added this task:");
        System.out.print(" " + newTing.printTask() + "\n");
        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
    }
    private void event(String taskInfo) {
        // TODO add DukeException for this
        /**
         *  creates new event, add to tasklist
         *  prints confirmation message
         *  and prints formatted event string
         *  then total num of current tasks
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if taskInfo is empty
         */
        int sep = taskInfo.indexOf('/');
        // use sep to split string
        String actualTask = taskInfo.substring(0,sep);
        sep += 4; // put sep at space after /by
        String time = taskInfo.substring(sep);
        Event newTing = new Event(actualTask,"E",time);
        taskArr.add(newTing);
        System.out.println("Got it. I've added this task:");
        System.out.print(" " + newTing.printTask() + "\n");
        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
    }
    private void done(int t) {
        /**
         *  helper function, marks tasks as done
         *  then prints confirmation,
         *  then prints the done task in formatted string
         *  (formatted string from task itself)
         *  @return none
         */
        Task doneTask = taskArr.get(t-1);
        doneTask.markDone();
        System.out.print("Nice! I've marked this task as done:\n");
        System.out.println(doneTask.printTask());

    }
    private void delete(int taskNum) {
        /**
         *  helper function, deletes task
         *  based on number provided
         *  prints confirmation and formatted task
         *  as per specified within task printTask method
         *  then prints number of remaining tasks
         *
         *  @return none
         */
        Task doneTask = taskArr.get(taskNum-1);

        System.out.print("Noted. I've removed this task: \n");
        System.out.println(" " + doneTask.printTask());
        taskArr.remove(taskNum-1);
        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
    }
    private void run() {
        // TODO add DukeException for this
        /**
         *  main run method
         *  scans input and turns it
         *  into task: event, deadline or to do
         *  every print statement wrapped with
         *  2 solid lines
         *  @params String that describes task
         *  @return none
         *  @throws DukeException if command unknown
         */
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                // list all tasks
                printLine();
                System.out.print(list());
                printLine();
            } else if (input.equals("todo")) {
                // basic
                String info = sc.nextLine();
                printLine();
                todo(info);
                printLine();
            } else if (input.equals("deadline")) {
                String info = sc.nextLine();
                printLine();
                deadline(info);
                printLine();
            } else if (input.equals("event")) {   // list command
                String info = sc.nextLine();
                printLine();
                event(info);
                printLine();
            } else if (input.equals("done")) {   // list command
                int taskNum = sc.nextInt();
                printLine();
                done(taskNum);
                printLine();
            } else if (input.equals("delete")) {
                int taskNum = sc.nextInt();
                printLine();
                delete(taskNum);
                printLine();
            } else {
                // handle all other cases
                printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
            input = sc.next();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}

/*
String line = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(FILEPATH);
            // always need to wrap file reader in buffer reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                store(line);
            }
            // close file
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("I can't see");
        } catch(IOException ex) {
            System.out.println("That sign will stop me, cos I can't read!");
        }
        private void store(String task) {
        // converts tasks in string from storage to taskarr
        Scanner stringSc = new Scanner(task);

        String type = stringSc.next();
        stringSc.next();
        int isDone = stringSc.nextInt();
        stringSc.next();
        String info = stringSc.next();

        if (type == "D") {
            // take up empty input
            stringSc.next();
            String by = stringSc.next();
            Deadline newTask = new Deadline(info,"D",by);
            taskArr.add(newTask);
        } else if (type == "E") {
            stringSc.next();
            String by = stringSc.next();
            Event newTask = new Event(info,"E",by);
            taskArr.add(newTask);
        } else {
            ToDo newTask = new ToDo(info, "T","");
            taskArr.add(newTask);
        }
    }
    private void save() {
        data.setAppend(true);
        String mainTxt = "";
        for (Task t: taskArr) {

        }
    }
 */