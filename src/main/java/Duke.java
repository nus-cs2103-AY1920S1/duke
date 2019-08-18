import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        greetHello();

        String input;
        // Run input loop
        while (!(input = sc.nextLine()).equals("bye")) {
            switch (input){
                case "list":
                    readList(list);
                    break;
                default:
                    addToList(input, list);
                    break;
            }
        }

        greetBye();
    }

    public static void printOutput(String output) {
        String line = "    ____________________________________________________________\n";

        // Indent and process output line
        output = "      " + output.replaceAll("\n","\n      ") + '\n';

        System.out.println(line + output + line);
    }


    public static void greetHello() {
        printOutput("Hello I'm Duke\nWhat can I do for you?");
    }

    public static void greetBye() {
        printOutput("Bye. Hope to see you again soon!");
    }

    public static void addToList(String input, ArrayList<String> list) {
        list.add(input);
        printOutput("added: " + input);
    }

    public static void readList(ArrayList<String> list) {
        int count = 1;
        StringBuilder output = new StringBuilder();

        // List and print all items stored
        for (String item: list) {
            output.append(count++ + ". " + item + '\n');
        }

        // Remove terminal newline character
        output.deleteCharAt(output.toString().length()-1);
        
        printOutput(output.toString());
    }
}
