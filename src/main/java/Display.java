import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
        String greeting2 = "What can I do for you?";
        ArrayList<String> printxs = new ArrayList<>();
        printxs.add(greeting1);
        printxs.add(greeting2);
            Display.printSection(printxs);
    }

    private static void printExitMessage() {
        String farewell = "Bye. Hope to see you "
            + "again soon!";

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
        ArrayList<String> printxs = new ArrayList<>();
        printxs.add(job);
        Display.printSection(printxs);
    }

    public static void printAddTaskSection(String taskDetails) {
        String outputMsg = "added: " + taskDetails;
        Display.printSection(outputMsg);
    }        

    public static void 
        printAllTasks(Iterator<TaskInterface> iter) {

        ArrayList<String> printxs = new ArrayList<>();

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
                /* TODO controller.list */
                this.controller.listTasks();
            } else {
                this.controller.addTask(command);
                /* TODO controller add task */     
            }

            command = this.sc.nextLine();
        }
        Display.printExitMessage();
    }
}
