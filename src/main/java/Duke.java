/* Java beginner here. Please point out any any 'weird' or unconventional coding style! Thank you -Li Yuan*/

import java.util.Scanner;

class User {
    final String EXIT_COMMAND = "bye";

    private String currentInput = "";

    public User() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }

    public String getCurrentInput() {
        return this.currentInput;
    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }

    public void printCurrentInput() {
        System.out.println("    ____________________________________________________________\n" +
                "     " + this.getCurrentInput() + "\n" +
                "    ____________________________________________________________");
    }

    public void sayByeToUser() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }

    //Saw Shui Yao's post, thought was a good detail. hence equalsIgnoreCase.
    public boolean inputIsBye (){
        return this.currentInput.equalsIgnoreCase(EXIT_COMMAND);
    }

}

public class Duke {
    public static void main(String[] args) {
        User user = new User();
        Scanner input = new Scanner(System.in);

        while(!user.inputIsBye()) {
            user.setCurrentInput(input.nextLine());
            if (!user.inputIsBye()) {
                user.printCurrentInput();
            }
        }
        user.sayByeToUser();
    }
}
