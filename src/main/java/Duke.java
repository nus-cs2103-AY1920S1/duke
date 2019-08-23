/* should have read all the task before starting. Case should be used over ifelse in dowhile loop,
    as there is no priority in deciphering the user input. Alot of warnings of public members. will finalise them at v0.6.(or do it early?)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            } else if (user.inputIsDone()) {
                user.setTaskDone();
            } else { //If not 'list' or 'bye'
                user.addCurrentInput();
            }
        } while (!user.inputIsBye());
        user.sayByeToUser();
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    //cant see symbols in intellji. isDone ? "\u2713" : "\u2718"

    public void markIsDone() {
        this.isDone = true;
    }

    public void setDescription(String userInput) {
        this.description = userInput;
    }

    public String getDescription() {
        return this.description;
    }
}

class User {
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private final String DONE_COMMAND = "done";

    private String currentInput = "";
    private ArrayList<String> userInputs = new ArrayList<String>();
    private ArrayList<Task> userTasks = new ArrayList<Task>();

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
        userTasks.add(new Task(this.currentInput));
        System.out.println("    ____________________________________________________________\n"
                + "     added " + this.getCurrentInput() + "\n"
                + "    ____________________________________________________________\n");
    }

    public boolean inputIsBye (){
        return this.currentInput.equalsIgnoreCase(EXIT_COMMAND);
    }

    public boolean inputIsList(){
        return this.currentInput.equalsIgnoreCase(LIST_COMMAND);
    }

    public boolean inputIsDone() {
        String firstWord;
        //If one more than 1 input, check if first word is "done"
        if (this.currentInput.contains(" ")){
            firstWord = this.currentInput.substring(0, this.currentInput.indexOf(" "));
            return firstWord.equalsIgnoreCase(DONE_COMMAND);
        } else { //implied: 'done' inputs without numbers are treated as tasks/ignored
            return false;
        }
    }

    public void setTaskDone() {
        String temp;
        //get 2nd number
        temp = this.currentInput.substring(this.currentInput.indexOf(" "));
        //remove space infront of number, causing errors
        temp = temp.replaceAll("\\s+","");

        int taskNumber = Integer.parseInt(temp);
        int count = 1;
        //i dont know how to access specific element of Arraylist with custom class without looping through every element. Do tell.
        for (Task task : userTasks) {
            if (count == taskNumber) {
                task.markIsDone();
                System.out.println("    ____________________________________________________________\r"
                        + "     Nice! I've marked this task as done: \r" + "       [" + task.getStatusIcon()
                        + "] " + task.getDescription() + "\r"
                        + ("    ____________________________________________________________\n"));
            }
            count++;
        }
    }

    public void printUserInputs() { //catch empty list?
        int count = 1;
        System.out.println("    ____________________________________________________________\r");
        for (Task temp : userTasks) {
            System.out.println("    " + count + ".[" + temp.getStatusIcon() + "] "
                    + temp.getDescription() + "\r");
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void sayByeToUser() {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}
