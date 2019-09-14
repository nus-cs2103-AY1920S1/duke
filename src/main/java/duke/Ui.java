package duke;

import duke.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui{
    String userInput;
    Scanner scanner = new Scanner(System.in);

    //////////////  read method   ////////////
     String read() {
         userInput = scanner.nextLine();
         return userInput;
    }

    ///////////// print method for list ///////////////////
    public void print_list(int printType, int i, ArrayList<Task> taskList){
        if(printType==1)
        System.out.println("Here are the tasks in your list:");
        if(printType==2)
            System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description);
        if(printType==3)
            System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " (" + taskList.get(i).get_TimeFrame() + ")");
    }

    ///////////// print method for delete //////////////////
    public void print_delete(int no_of_task, Task t){
             System.out.println("Noted. I've removed this task:");
             System.out.println("  [" + t.type + "][" + t.status + "] " + t.description + " (" + t.get_TimeFrame() + ")");
             System.out.println("Now you have " + no_of_task + " tasks in the list.");
    }


    ///////////////  print method for find  /////////////////
    public void print_find(int num, ArrayList<Task> taskList, int i, int n){
         if(n==1){
             System.out.println("Here are the matching tasks in your list: ");
         }
         else{
             System.out.println(num + ".[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " " + taskList.get(i).get_TimeFrame());
         }
    }

    ///////////// print method for To Do //////////////////
    public void print_toDo(String description, int no_of_task){
        System.out.println("Got it. I've added this task:");
        System.out.println("  [ ][ ]" + description);
        System.out.println("Now you have " + no_of_task + " tasks in the list.");
    }

    /////////////// print method for bye /////////////////
    void print_bye(){
        System.out.println("Bye. Hope to see you again.");
    }

    public void print_deadline(String sub, String timeFrame, int no_of_task){
        System.out.println("Got it. I've added this task:");
        System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
        System.out.println("Now you have " + no_of_task + " tasks in the list.");
    }

    public void print_event(String sub, String timeFrame, int no_of_task){
        System.out.println("Got it. I've added this task:");
        System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
        System.out.println("Now you have " + no_of_task + " tasks in the list.");
    }

    public void print_find(int num, Task task){
        System.out.println(num + ".[" + task.get_Type() + "][" + task.get_Status() + "] " + task.get_Description() + " " + task.get_TimeFrame());

    }

    public void print_false(){
        System.out.println("OOPS!! I'm sorry, but I don't know what that means.");
    }

    ////////////// for empty descriptions ////////////////
    void cannotBeEmpty(){
         System.out.println("OOPS!! The description cannot be empty.");
    }

}

