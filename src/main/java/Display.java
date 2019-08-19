import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

class Display implements TaskObserver {
    private ControllerInterface controller; 
    private TaskModelInterface model;
    private Scanner sc;
    private int totalTasks;

    public Display(ControllerInterface controller, TaskModelInterface model) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver(this);
        this.sc = new Scanner(System.in);
        this.totalTasks = 0;
    }

    private static void printGreeting() {
        String greeting1 = "Hewwo! I'm OwO";
        String greeting2 = "What can I do fow you?";
        ArrayList<String> printxs = new ArrayList<>();
        printxs.add(greeting1);
        printxs.add(greeting2);
            Display.printSection(printxs);
    }

    private static void printExitMessage() {
//        String farewell = "Bye. Hope to see you "
//           + "again soon!";
        String farewell = "NyOO >w< owo dont goo iww miss youu!!";

        Display.printSection(farewell);
    }

    private static void printLineBreak() {
        String line = "     "
            + "________________________________"
            + "____________________________";
        System.out.println(line);
    }

    private static void printHeader() {
        System.out.println();
        Display.printLineBreak();
    }

    private static void printFooter() {
        Display.printLineBreak();
        System.out.println();
        System.out.println();
    }

    private static boolean isEndCommand(String cmd) {
        String[] cmdlist = cmd.split(" ");
        return cmdlist[0].toUpperCase().equals("BYE");
    }

    private static List<String> stringToList(String text) {
        String[] textArr = text.split("\n");
        List<String> textList = 
            new ArrayList(Arrays.asList(textArr));
        return textList;
    }        

    private static void printList(List<String> printJobs) {
        /* TODO:  Delimit by \n */

        for (String printJob : printJobs) {
            System.out.print("      ");
            System.out.print(printJob);
            System.out.println();
        }
    }

    private static void printSection(List<String> printJobs){
        Display.printHeader();
        Display.printList(printJobs);
        Display.printFooter();
    }

    private static void printSection(String job) {
//        ArrayList<String> printxs = new ArrayList<>();
//        printxs.add(job);
        List<String> printxs = Display.stringToList(job);
        Display.printSection(printxs);
    }

    public static void printAddTaskSection(String taskDetails,
            int totalTasks) {
        ArrayList<String> printxs = new ArrayList<>();
        String headerMsg = "Got it. I've added this task:";
        String outputMsg = "  " + taskDetails;
        String footerMsg = "Nyow you have "
           + totalTasks + " tasks in the wist.";

        printxs.add(headerMsg);
        printxs.add(outputMsg);
        printxs.add(footerMsg);
        Display.printSection(printxs);
    }        

    public static void printDoneTaskSection(String taskDetails) {
        ArrayList<String> printxs = new ArrayList<>();
        String headermsg = "Nyice ;;w;;  "
            + "I've mawked this task as donye";
        //String headermsg = "Nice! I've marked this task as done:";
        printxs.add(headermsg);
        printxs.add(taskDetails);
        Display.printSection(printxs);

    }

    public static void printErrorSection(String message) {
        Display.printSection(message);
    }        

    public static void 
        printAllTasks(Iterator<TaskInterface> iter) {

        ArrayList<String> printxs = new ArrayList<>();
//        String headermsg = "Here are the tasks in your list:";
        String headermsg = "Hewe awe the tasks in youw wist:";
        printxs.add(headermsg);

        int counter = 1;

        while (iter.hasNext()) {
            String taskLine = "" + counter + "." 
                + iter.next();
            printxs.add(taskLine);

            ++counter;
        }
        Display.printSection(printxs);
    }

    public void update(TaskModelInterface model){
    /* TaskModel's most recent change here */
    /* model.getUpdate */
    /* display in section */
        this.totalTasks = model.getTotalTasks();
    }

    public void instance() {
        Display.printGreeting();
        String command = this.sc.nextLine();

        while (! isEndCommand(command)) {

            String[] commandlist = command.split(" ");
            if (commandlist[0].toUpperCase().equals("LIST")) {
                this.controller.listTasks();
            } else if 
                (commandlist[0].toUpperCase().equals("DONE")) {
                this.controller.doneTask(command);
            } else {
                this.controller.addTask(command);
            }

            command = this.sc.nextLine();
        }
        Display.printExitMessage();
    }
}
