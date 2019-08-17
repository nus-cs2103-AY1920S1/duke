import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Display implements TaskObserver {
    private ControllerInterface controller; 
    private TaskModelInterface model;
    private Scanner sc;

    public Display(ControllerInterface controller, TaskModelInterface model) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver(this);
        this.sc = new Scanner(System.in);
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

    public void update(TaskModelInterface model){
        /* TaskModel's most recent change here */
        /* model.getUpdate */
        /* display in section */

    }

    public void instance() {
        Display.printGreeting();
        String command = this.sc.nextLine();

        while (! isEndCommand(command)) {

            String[] commandlist = command.split(" ");
            if (commandlist[0].toUpperCase().equals("LIST")) {
                /* TODO controller.list */
            } else {
                /* TODO controller add task */     
            }

            String output = "echo: " + command;
            ArrayList<String> printxs = new ArrayList<>();
            printxs.add(output);
            Display.printSection(printxs);


            command = this.sc.nextLine();
        }
        Display.printExitMessage();
    }
}
