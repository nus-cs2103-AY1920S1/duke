/* Java beginner here. Please point out any any 'weird' or unconventional coding style! Thank you -Li Yuan*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    final String EXIT_COMMAND = "bye";
    final String LIST_COMMAND = "list";

    private String currentInput = "";
    private List<String> userInputs = new ArrayList<String>();


    public User() {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
    }

    public String getCurrentInput() {
        return this.currentInput;
    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }

    public void addCurrentInput() {
        this.userInputs.add(this.currentInput);
        System.out.println("    ____________________________________________________________\n"
                + "     added " + this.getCurrentInput() + "\n"
                + "    ____________________________________________________________\n");
    }

    public void sayByeToUser() {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }

    //Saw Shui Yao's post, thought was a good detail. hence equalsIgnoreCase.
    public boolean inputIsBye (){
        return this.currentInput.equalsIgnoreCase(EXIT_COMMAND);
    }

    public boolean inputIsList(){
        return this.currentInput.equalsIgnoreCase(LIST_COMMAND);
    }

    public void printUserInputs() { //catch empty list?
        int count = 1;
        System.out.println("    ____________________________________________________________\r");
        for (String temp : userInputs) {
            System.out.println("    " + count + ". " + temp + "\r");
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }
}

public class Duke {
    public static void main(String[] args) {
        User user = new User();
        Scanner input = new Scanner(System.in);

        do {
            user.setCurrentInput(input.nextLine());

            if (user.inputIsBye()) {
                //Do nothing and wait to exit program if input is 'bye'
            } else if (user.inputIsList()) {
                user.printUserInputs();
            } else { //If not 'list' or 'bye'
                user.addCurrentInput();
            }
        } while (!user.inputIsBye());
        user.sayByeToUser();
    }
}