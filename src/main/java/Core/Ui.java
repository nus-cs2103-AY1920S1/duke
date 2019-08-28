package duke.core;

import duke.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
        Scanner scanner;

    public Ui(){
        Scanner scanner = new Scanner(System.in);
        this.scanner = scanner;
    }

    public void printHello(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public String readCommand(){
        String input = scanner.nextLine();
        return input;
    }

    public void printRecord(ArrayList<Task> textEntered){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < textEntered.size(); i++){
            System.out.print((i + 1) + "." + textEntered.get(i).toString() + "\n");
        }
        System.out.println();
    }

    public void printBye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void printError(String excepMsg){
        System.out.println(excepMsg + "\n");
    }

    public void printAdd(Task t , int sizeOfTask){
        System.out.printf("Got it. I've added this task: \n  %s\nNow you have %d tasks in the list.%n", t.toString(), sizeOfTask);
        System.out.println();
    }

    public void printDone(Task t){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + t.toString() + "\n");
    }

    public void printDelete(Task t , int sizeOfTask){
        System.out.println("Noted. I've removed this task: \n" + "  " + t.toString() + "\n"
                + "Now you have " + sizeOfTask + " tasks in the list." + "\n");
    }

    public void printFind(ArrayList<Task> inputList) {
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < inputList.size(); i++){
            System.out.print((i + 1) + "." + inputList.get(i).toString() + "\n");
        }
        System.out.println();
    }

}
