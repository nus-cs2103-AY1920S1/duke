import java.util.ArrayList;
import java.util.Arrays;
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


    public void printInput(Task input, List<Task> lst) {
        System.out.println(horizontalLine);

        System.out.println("     Got it. I've added this task: ");
        System.out.println(String.format("       %s",input));
        System.out.println(String.format("     Now you have %d tasks in the list.",lst.size()));
        System.out.println(horizontalLine);
        System.out.println();

    }

    public void printDeletion(Task input, List<Task> lst) {
        System.out.println(horizontalLine);

        System.out.println("     Noted. I've removed this task: ");
        System.out.println(String.format("       %s",input));
        System.out.println(String.format("     Now you have %d tasks in the list.",lst.size()));
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

    //may remove soon
    public void printOneLine(String input) {
        System.out.println(horizontalLine);
        System.out.println(String.format("     %s",input));
        System.out.println(horizontalLine);
        System.out.println();

    }

    public void printOneLine(Command command) {
        System.out.println(horizontalLine);
        System.out.println(String.format("     %s",command));
        System.out.println(horizontalLine);
        System.out.println();

    }


    public void printNumberList(List<Task> lst) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("     %d.%s",i+1, lst.get(i)));
        }

        System.out.println(horizontalLine);
        System.out.println();
    }




    public void checkValidLength(String[] tokens) throws IllegalArgumentException {
        if (tokens.length == 1) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! The description of a %s cannot be empty.",tokens[0]));
        }
    }


}
