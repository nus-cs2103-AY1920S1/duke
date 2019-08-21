import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        List<String> start = new ArrayList<>();
        List<String> lst = new ArrayList<>();

        start.add("Hello! I'm Duke");
        start.add("What can I do for you?");
    	printStart(start);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

        	String input = sc.nextLine();

            switch (input) {
                case ("bye"):
                    printEnd("Bye. Hope to see you again soon!");
                    break;

                case ("list"):
                    printNumberList(lst);
                    break;

                default:
                    printInput(input);
                    lst.add(input);
            }

        }

    }

    public static void printInput(String input) {
    	System.out.println("    ____________________________________________________________");
    	System.out.println(String.format("     added: %s",input));
    	System.out.println("    ____________________________________________________________");
    	System.out.println();

    }

    public static void printStart(List<String> start) {
        System.out.println("    ____________________________________________________________");
        for (String input : start) {
            System.out.println(String.format("     %s",input));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void printEnd(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("     %s",input));
        System.out.println("    ____________________________________________________________");
        System.out.println();

    }


    public static void printNumberList(List<String> lst) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("     %d. %s",i+1,lst.get(i)));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }


   
}
