package duke;

import java.util.Scanner;

// This class deals with interactions with the user.
class Ui{
    public Ui(){

    }

    public void showWelcome(){
        String UI_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(UI_GREETING);
    }

    public void showLine(){
        String UI_HORIZONTAL_LINE = "\t____________________________________________________________";
        System.out.println(UI_HORIZONTAL_LINE);
    }

    public void showBye(){
        String UI_GOODBYE = "Bye. Hope to see you again soon!";
        System.out.println(UI_GOODBYE);
    }

    public void showLoadingError(){
        System.out.println("Error in loading tasks into Duke.");
    }

    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        return input;
    }
}
