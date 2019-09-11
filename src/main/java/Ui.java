import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    final private static String horizontalLine = "    ____________________________________________________________";

    Scanner sc = new Scanner(System.in);

    public boolean hasInputs() {
        return sc.hasNextLine();
    }

    public String readCommand(){
        return sc.nextLine();
    }


    public void printWelcomeMessage() {
        List<String> start = new ArrayList<>();
        start.add("Hello! I'm Duke");
        start.add("What can I do for you?");
        this.printInput(start);
    }


    public void printInput(Task input, TaskList taskList) {
        System.out.println(horizontalLine);

        System.out.println("     Got it. I've added this task: ");
        System.out.println(String.format("       %s",input));
        System.out.println(String.format("     Now you have %d tasks in the list.", taskList.getSize()));
        System.out.println(horizontalLine);
        System.out.println();

    }

    public void printDeletion(Task input, TaskList taskList) {
        System.out.println(horizontalLine);

        System.out.println("     Noted. I've removed this task: ");
        System.out.println(String.format("       %s",input));
        System.out.println(String.format("     Now you have %d tasks in the list.",taskList.getSize()));
        System.out.println(horizontalLine);
        System.out.println();

    }

    public void printInput(List<String> start) {
        System.out.println(horizontalLine);
        for (String input : start) {
            System.out.println(String.format("     %s",input));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    public void printOneLine(String input) {
        System.out.println(horizontalLine);
        System.out.println(String.format("     %s",input));
        System.out.println(horizontalLine);
        System.out.println();

    }

    public void printNumberList(TaskList taskList) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            //may run into error for our taskList index
            //System.out.println(String.format("     %d.%s",i+1, lst.get(i)));
            System.out.println(String.format("     %d.%s",i+1, taskList.getTaskAt(i+1)));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }



    public void printByeMessage() {
        this.printOneLine("Bye. Hope to see you again soon!");
    }

    public void printErrorMessage(Exception e) {
        this.printOneLine(e.getMessage());
    }

    public static void checkValidLength(String[] tokens) throws IllegalArgumentException {
        List<String> group1 = List.of("todo", "deadline", "event");
        List<String> group2 = List.of("done", "delete");
        if (tokens.length == 1 && group1.contains(tokens[0])) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! The description of a %s cannot be empty.",tokens[0]));
        } else if (tokens.length == 1 && group2.contains(tokens[0])) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! %s command requires integer.",tokens[0]));
        }
    }
}

