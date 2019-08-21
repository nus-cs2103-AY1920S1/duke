import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        lst.add("Hello! I'm Duke");
        lst.add("What can I do for you?");
    	printInput(lst);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

        	String input = sc.next();

        	if (input.equals("bye")) {
        		printInput("Bye. Hope to see you again soon!");
        		break;
        	} else {
        		printInput(input);
        	}

        }

    }

    public static void printInput(String input) {
    	System.out.println("    ____________________________________________________________");
    	System.out.println(String.format("     %s",input));
    	System.out.println("    ____________________________________________________________");
    	System.out.println();

    }

    public static void printInput(List<String> lst) {
        System.out.println("    ____________________________________________________________");
        for (String input : lst) {
            System.out.println(String.format("     %s",input));
        }

  
        System.out.println("    ____________________________________________________________");
        System.out.println();
    } 
}
