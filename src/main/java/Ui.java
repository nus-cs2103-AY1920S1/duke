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
 * interact with user.
 */
class Ui implements TaskObserver, UiObservable, 
    TaskFeedbackObserver, TagFeedbackObserver, 
    PairFeedbackObserver, QueryFeedbackObserver {
    private ControllerInterface controller; 
    private TaskModelInterface model;
    private Scanner sc;
    private int totalTasks;
    private List<UiObserver> observers;
    private String response; // for GUI, to get a reply

    public Ui(ControllerInterface controller, TaskModelInterface model) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver(this);
        this.sc = new Scanner(System.in);
        this.totalTasks = 0;
        this.observers = new ArrayList<>();
    }

    //L10 for GooeyBridge
    public void registerObserver(UiObserver u) {
        this.observers.add(u);
    }

    public void removeObserver(UiObserver u) { /* TODO */ }

    public String getReply() {
        return this.response;
    }        

    private void notifyObservers() {
        for (UiObserver u : this.observers) {
            u.update(this);
        }
    }



    public void tagFeedbackUpdate(String tag, String msg) {
        //StringBuilder sbOut = new StringBuilder();
        String out = String.format("Hi! %s has been %s! UwU%n", 
            tag, msg);
        //sbOut = sbOut.append(header);
        //sbOut = sbOut.append(tag);
        //sbOut = sbOut.append(footer);
        //printSection(sbOut.toString());
        printSection(out);
    }

    public void taskFeedbackUpdate(TaskInterface task, 
        String msg) {
        String out = String.format("Hi!%n%s%n"
            + "has been %s! UwU%n", task.toString(), msg);

        //StringBuilder sbOut = new StringBuilder();
        //sbOut = sbOut.append(header);
        //sbOut = sbOut.append(task.toString());
        //sbOut = sbOut.append(footer);
        //printSection(sbOut.toString());
        printSection(out);
    }


    public void pairFeedbackUpdate(String tag, TaskInterface task,
           String verb) {

        //StringBuilder sbOut = new StringBuilder();
        String output = 
            String.format("The following pair has been been %s:\n"
                + " %s - %s", 
                verb, tag.toString(), task.toString());
        printSection(output);
    }
    public <T,E> void queryFeedbackUpdate(T searchTerm, 
            Stream<E> stream, String msg) {
        System.out.println("UI.queryFeedbackUpdate<<<<");
        StringBuilder sbOut = new StringBuilder();
        String header = String.format("Hi! Hewes that %s seawch "
            + "tewm you wanted\nbased on the quewy fow:\n%s%n", 
            msg, searchTerm.toString());
        sbOut = sbOut.append(header);

        List<E> taskList = stream 
            .collect(Collectors.toCollection(ArrayList::new));

        if (taskList.size() < 1) {
            String err = String.format("Oops! Seawch term for:%n"
                + "%s%ndidnt wetuwn any wesults!", 
                searchTerm.toString());
            printSection(err);
            return;
        }

        Iterator<E> iter = taskList.listIterator();

        ArrayList<String> printxs = new ArrayList<>();
        //String headermsg = "Here are the tasks in your list:";

        int counter = 1;

        while (iter.hasNext()) {
            String taskLine = "" + counter + ". " 
                + iter.next() + "\n";
            sbOut.append(taskLine);

            ++counter;
        }

        printSection(sbOut.toString());
        return;
    }

    private void printGreeting() {
        StringBuilder sbOut = new StringBuilder();
        sbOut = Ui.printBanner(sbOut);
        String greeting1 = "hewwo! i'm OwO\n"
          //  + "Mistew Stawk's augmented weawity gwocewy wist\n"
           // + "OwO stands fow \"Owways With Owws\"\n"
           // + "its a wowk in pwogwess, wike me\n"
           // //+ "Mistew Stawk wuvd his acwonyms.\n"
            + "what can OwO do fow you today?";
        //Display.printSection(printxs);
        sbOut.append(greeting1);
        //Ui.printSection(greeting1);
        printText(sbOut);
    }
    public static String stringGreeting() {
        StringBuilder sbOut = new StringBuilder();
        sbOut = Ui.printBanner(sbOut);
        String greeting1 = "hewwo! i'm OwO\n"
          //  + "Mistew Stawk's augmented weawity gwocewy wist\n"
          //  + "OwO stands fow \"Owways With Owws\"\n"
          //  + "its a wowk in pwogwess, wike me\n"
          //  //+ "Mistew Stawk wuvd his acwonyms.\n"
            + "what can OwO do fow you today?";
        //Display.printSection(printxs);
        sbOut.append(greeting1);
        //Ui.printSection(greeting1);
        return sbOut.toString();
    }

    public void printExitMessage() {
        //String farewell = "Bye. Hope to see you "
        //     + "again soon!";
        String farewell = "NyOO >w< owo dont goo OwO wiww "
            + "miss youu!!";

        printSection(farewell);
    }

    private static StringBuilder printLineBreak(StringBuilder sb) {
        String line = "     "
            + "________________________________"
            + "____________________________";
        sb.append(line);
        sb.append("\n");
        return sb;
        //Ui.printText(line);
        //Modified in L10 to support piping to GUI
        //System.out.println(line);
    }

    private static StringBuilder printHeader(StringBuilder sb) {
        StringBuilder sbOut = sb;
        String header = "\n";// + Ui.printLineBreak();
        sbOut.append(header);
        sbOut = Ui.printLineBreak(sbOut);
        return sbOut;
        //System.out.println();
        //Ui.printLineBreak();
    }

    private static StringBuilder printFooter(StringBuilder sb) {
        StringBuilder sbOut = sb;
        sbOut = Ui.printLineBreak(sbOut);
        sbOut.append("\n");
        sbOut.append("\n");
        //Ui.printLineBreak();
        //Modified in L10 to support piping to GUI
        //System.out.println();
        //System.out.println();
        return sbOut;
    }


    private static List<String> stringToList(String text) {
        String[] textArr = text.split("\n");
        List<String> textList = 
            new ArrayList(Arrays.asList(textArr));
        return textList;
    }        

    private void printText(StringBuilder sb) {
        String output = sb.toString();
        //System.out.println(output);
        this.response = output;
        notifyObservers();
    }

    private void printList(List<String> printJobs) {
        /* TODO:  Delimit by \n */

        StringBuilder sbOut = new StringBuilder();;
        for (String printJob : printJobs) {
            sbOut.append("      ");
            sbOut.append(printJob);
            sbOut.append("\n");
            //System.out.print("      ");
            //System.out.print(printJob);
            //System.out.println();
        }
        printText(sbOut);
    }

    private static StringBuilder addToBuilder(List<String> printJobs, StringBuilder sb) {

        /* TODO:  Delimit by \n */

        StringBuilder sbOut = sb;
        for (String printJob : printJobs) {
            sbOut.append("      ");
            sbOut.append(printJob);
            sbOut.append("\n");
            //System.out.print("      ");
            //System.out.print(printJob);
            //System.out.println();
        }
        return sbOut;
    }

    private void printSection(List<String> printJobs) {
        StringBuilder sbOut = new StringBuilder();
        sbOut = Ui.printHeader(sbOut);
        sbOut = Ui.addToBuilder(printJobs, sbOut);
        sbOut = Ui.printFooter(sbOut);
        //Ui.printHeader();
        //Ui.printList(printJobs);
        //Ui.printFooter();
        printText(sbOut);
    }

    private void printSection(String job) {
        //ArrayList<String> printxs = new ArrayList<>();
        //printxs.add(job);
        List<String> printxs = Ui.stringToList(job);
        printSection(printxs);
    }

    /**
     * Return void, prints section to indicate a task is added.
     *  @param taskDetails Details of Task to print
     *  @param totalTasks total number of tasks in the tasklist
     */
    public void printAddTaskSection(String taskDetails,
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
        printSection(printxs);
    }        

    /**
     * Return void, prints section to indicate a task is done.
     *  @param taskDetails Details of Task to print
     *  @param totalTasks total number of tasks in the tasklist
     */
    public void printDoneTaskSection(String taskDetails) {
        ArrayList<String> printxs = new ArrayList<>();
        String headermsg = "Nyice ;;w;;  "
             + "OwO has mawked this task as donye";
        //String headermsg = "Nice! I've marked this task as done:";
        printxs.add(headermsg);
        printxs.add(taskDetails);
        printSection(printxs);

    }

    /**
     * Return void, prints section to indicate a task is delete.
     *  @param taskDetails Details of Task to print
     *  @param totalTasks total number of tasks in the tasklist
     */
    public void printDeleteTaskSection(String 
            taskDetails, int totalTasks) {
        String header = "nyoted. OwO has wemuvd this task:\n";
        String footer = "nyow you have "
            + totalTasks + " tasks in the wist";
        printSection(header + footer);
    }

    /**
     * Returns void, prints an error section to screen.
     *  @param message Error message to be printed
     */
    public void printErrorSection(String message) {
        printSection(message);
    }        

    /**
     * Returns void, prints a list of task given input.
     *  @param Stream of tasks to be printed
     */
    public <T> void 
        printAllTasks(Stream<T> taskStream) {

        List<T> taskList = taskStream
            .collect(Collectors.toCollection(ArrayList::new));
        Iterator<T> iter = taskList.listIterator();

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
        printSection(printxs);
    }


    /*
>>>>>>> branch-Level-9
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
    */

    /**
     * Returns void, prints a list of task given input.
     *  @param Stream of tasks to be printed
     */
    public void update(TaskModelInterface model) {
        /* TaskModel's most recent change here */
        /* model.getUpdate */
        /* display in section */
        this.totalTasks = model.getTotalTasks();
    }

    /**
     * Returns nothing, starts user session and maintains loop.
     */
    public void instance() {
        printGreeting();
        String command = this.sc.nextLine();

        while (! this.controller.isEndCommand(command)) {
            this.controller.whatsGoingOn(command);
            command = this.sc.nextLine();
        }
        printExitMessage();
    }

    /**
     * Returns nothing, starts user session for GUI 
     * and maintains loop.
     */
    public void instanceGui() {
        printGreeting();
        String command = this.sc.nextLine();

        while (! this.controller.isEndCommand(command)) {
            this.controller.whatsGoingOn(command);
            command = this.sc.nextLine();
        }
        printExitMessage();
    }

    /**
     * Returns void, prints welcome banner and logo.
     */
    public static StringBuilder printBanner(StringBuilder sb) {
        String logo = "        \u2606                     \u273f\n"
            + "                                    \u2606 \u273f\n"
            + "         \u273f                 \u273f \u2606\n"
            + "  \u2606                \u2606\n"
            + "          \u2727   \u2606      \u273f\n"
            + "                \u2727     \u2606\n"
            + "     ####                      ####\n"
            + "    #       #                   #       #\n"
            + "   #         #                 #         #\n"
            + "   #         #  \\\\  /\\  //  #         #\n"
            + "    #       #    \\\\/  \\//    #       #\n"
            + "     ####       \\_/\\_/      ####\n"
            + "  \u2727\n"
            + "                    \u2606            \u2606\n"
            + " \u273f         \u2606\n"
            + "        \u2606       \u2606       \u2606\n"
            + "\n"
            + "              \u2606       \u273f\n";

        //System.out.println("Hewwo fwom\n" + logo);
        StringBuilder sbOut = new StringBuilder();
        sbOut.append("Hewwo fwom\n" + logo);
        return sbOut;
    }

    /*
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
     */
}
