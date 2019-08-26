import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Deals with printing to output for user to see
 * Other classes should call on this class' methods to 
 * interact with user
 */
class Ui implements TaskObserver {
    private ControllerInterface controller; 
    private TaskModelInterface model;
    private Scanner sc;
    private int totalTasks;

    public Ui(ControllerInterface controller, TaskModelInterface model) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver(this);
        this.sc = new Scanner(System.in);
        this.totalTasks = 0;
    }

    private static void printGreeting() {
        Ui.printBanner();
        String greeting1 = "hewwo! i'm OwO\n"
            + "Mistew Stawk's augmented weawity gwocewy wist\n"
            + "OwO stands fow \"Owways With Owws\"\n"
            + "its a wowk in pwogwess, wike me\n"
        //    + "Mistew Stawk wuvd his acwonyms.\n"
            + "what can OwO do fow you today?";
        //ArrayList<String> printxs = new ArrayList<>();
        //printxs.add(greeting1);
        //printxs.add(greeting2);
            //Display.printSection(printxs);
            Ui.printSection(greeting1);
    }

    private static void printExitMessage() {
        //String farewell = "Bye. Hope to see you "
        //     + "again soon!";
        String farewell = "NyOO >w< owo dont goo OwO wiww "
            + "miss youu!!";

        Ui.printSection(farewell);
    }

    private static void printLineBreak() {
        String line = "     "
            + "________________________________"
            + "____________________________";
        System.out.println(line);
    }

    private static void printHeader() {
        System.out.println();
        Ui.printLineBreak();
    }

    private static void printFooter() {
        Ui.printLineBreak();
        System.out.println();
        System.out.println();
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
        Ui.printHeader();
        Ui.printList(printJobs);
        Ui.printFooter();
    }

    private static void printSection(String job) {
        //ArrayList<String> printxs = new ArrayList<>();
        //printxs.add(job);
        List<String> printxs = Ui.stringToList(job);
        Ui.printSection(printxs);
    }

    /**
     * Return void, prints section to indicate a task is added
     *  @param taskDetails Details of Task to print
     *  @param totalTasks total number of tasks in the tasklist
     */
    public static void printAddTaskSection(String taskDetails,
            int totalTasks) {
        ArrayList<String> printxs = new ArrayList<>();
        String headerMsg = "got it boss. "
            + "OwO has added this task:";
        String outputMsg = "  " + taskDetails;
        String footerMsg = "Nyow you have "
           + totalTasks + " tasks in the wist";

        printxs.add(headerMsg);
        printxs.add(outputMsg);
        printxs.add(footerMsg);
        Ui.printSection(printxs);
    }        

    /**
     * Return void, prints section to indicate a task is done
     *  @param taskDetails Details of Task to print
     *  @param totalTasks total number of tasks in the tasklist
     */
    public static void printDoneTaskSection(String taskDetails) {
        ArrayList<String> printxs = new ArrayList<>();
        String headermsg = "Nyice ;;w;;  "
            + "OwO has mawked this task as donye";
        //String headermsg = "Nice! I've marked this task as done:";
        printxs.add(headermsg);
        printxs.add(taskDetails);
        Ui.printSection(printxs);

    }

    /**
     * Return void, prints section to indicate a task is delete
     *  @param taskDetails Details of Task to print
     *  @param totalTasks total number of tasks in the tasklist
     */
    public static void printDeleteTaskSection(String 
            taskDetails, int totalTasks) {
        String header = "nyoted. OwO has wemuvd this task:\n";
        String footer = "nyow you have "
           + totalTasks + " tasks in the wist";
        Ui.printSection(header + footer);
    }

    /**
     * Returns void, prints an error section to screen
     *  @param message Error message to be printed
     */
    public static void printErrorSection(String message) {
        Ui.printSection(message);
    }        


    /**
     * Returns void, prints a list of task given input
     *  @param Stream of tasks to be printed
     */
    public static void 
        //printAllTasks(Iterator<TaskInterface> iter) {
        printAllTasks(Stream<TaskInterface> taskStream) {
        //convert the stream into a list

        List<TaskInterface> taskList = taskStream
            .collect(Collectors.toCollection(ArrayList::new));
        Iterator<TaskInterface> iter = taskList.listIterator();

        ArrayList<String> printxs = new ArrayList<>();
        //String headermsg = "Here are the tasks in your list:";
        String headermsg = "hewe awe the tasks in youw wist:";
        printxs.add(headermsg);

        int counter = 1;

        while (iter.hasNext()) {
            String taskLine = "" + counter + "." 
                + iter.next();
            printxs.add(taskLine);

            ++counter;
        }
        Ui.printSection(printxs);
    }

    /**
     * Returns void, prints a list of task given input
     *  @param Stream of tasks to be printed
     */
    public void update(TaskModelInterface model){
    /* TaskModel's most recent change here */
    /* model.getUpdate */
    /* display in section */
        this.totalTasks = model.getTotalTasks();
    }

    /**
     * Returns nothing, starts user session and maintains loop
     */
    public void instance() {
        Ui.printGreeting();
        String command = this.sc.nextLine();

        while (! this.controller.isEndCommand(command)) {
            this.controller.whatsGoingOn(command);
            command = this.sc.nextLine();
        }
        Ui.printExitMessage();
    }

    /**
     * Returns void, prints welcome banner and logo
     */
    public static void printBanner() {
        String logo = "        \u2606                     \u273f\n"
            + "                                    \u2606 \u273f\n"
            + "         \u273f                 \u273f \u2606\n"
            + "  \u2606                \u2606\n"
            + "          \u2727   \u2606      \u273f\n"
            + "                \u2727     \u2606\n"
            + "         #####                  #####\n"
            + "        #     #                #     #\n"
            + "       #       #              #       #\n"
            + "       #       #  \\\\  /\\  //  #       #\n"
            + "        #     #    \\\\/  \\//    #     #\n"
            + "         #####      \\_/\\_/      #####\n"
            + "  \u2727\n"
            + "                    \u2606            \u2606\n"
            + " \u273f         \u2606\n"
            + "        \u2606       \u2606       \u2606\n"
            + "\n"
            + "              \u2606       \u273f\n";

        System.out.println("Hewwo fwom\n" + logo);
    }
}
