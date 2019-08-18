import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    static final ArrayList<String> listOfInputs = new ArrayList<>(100);
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            if(userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                int counter = 0;
                for(String item : listOfInputs) {
                    counter++;
                    System.out.println(counter + ". " + item);
                }
            } else {
                System.out.println("added: " + userInput);
                listOfInputs.add(userInput);
            }
        }

    }
}
