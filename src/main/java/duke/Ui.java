package duke;

import duke.Task;
import java.lang.StringBuilder;
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
    public String print_list(int printType, int i, ArrayList<Task> taskList){
         String output = "";
        if(printType==1)
            output = "Here are the tasks in your list:";
        if(printType==2)
            output = (i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description ;
        if(printType==3)
            output = (i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " (" + taskList.get(i).get_TimeFrame() + ")" ;

        return output;
    }

    ///////////// print method for delete //////////////////
    public String print_delete(int no_of_task, Task t){
             StringBuilder builder = new StringBuilder("Noted. I've removed this task:");
             builder.append("Noted. I've removed this task:\n");
             builder.append("  [" + t.type + "][" + t.status + "] " + t.description + " (" + t.get_TimeFrame() + ")\n");
             builder.append("Now you have " + no_of_task + " tasks in the list.");

             return builder.toString();
    }

    ///////////// print method for To Do //////////////////
    public String print_toDo(String description, int no_of_task){
        StringBuilder builder = new StringBuilder("Got it. I've added this task:\n");
        builder.append("  [ ][ ]" + description + "\n");
        builder.append("Now you have " + no_of_task + " tasks in the list.");

        return builder.toString();
    }

    /////////////// print method for bye /////////////////
    public String print_bye(){
         //System.out.println("Bye. Hope to see you again.");
         return "Bye. Hope to see you again." ;
    }

    public String print_deadline(String sub, String timeFrame, int no_of_task){
        StringBuilder builder = new StringBuilder("Got it. I've added this task:\n");
        builder.append("  [ ][ ] " + sub + " (" + timeFrame + ")\n");
        builder.append("Now you have " + no_of_task + " tasks in the list.");

        return builder.toString();
    }

    public String print_event(String sub, String timeFrame, int no_of_task){
         StringBuilder builder = new StringBuilder("Got it. I've added this task:\n");
         builder.append("  [ ][ ] " + sub + " (" + timeFrame + ")\n");
         builder.append("Now you have " + no_of_task + " tasks in the list.");

        return builder.toString();
    }

    public String print_find(int num, Task task){
         String output = num + ".[" + task.get_Type() + "][" + task.get_Status() + "] " + task.get_Description() + " " + task.get_TimeFrame() ;

        return output;
    }

    public String print_false(){
         String output = "OOPS!! I'm sorry, but I don't know what that means." ;

         return output;
    }

    public String print_done(TaskList tasks, int i){
        StringBuilder builder = new StringBuilder("Nice! I've marked this task as done:\n");
        builder.append("  [" + "\u2713" + "] " + tasks.get_TaskList().get(i - 1).get_Description());
        builder.append("New status: " + tasks.get_TaskList().get(i - 1).get_Status());

        return builder.toString();
    }

    ////////////// for empty descriptions ////////////////
    public String cannotBeEmpty(){
         String output = "OOPS!! The description cannot be empty." ;
         return output;
    }

    public String print_CreateFlashCard(String name){
         return "A Flashcard called " + name + " has been created." ;
    }

    public String print_CreateCard(String name) { return "A card under the Flashcard name *" + name + "* has been created." ;}


}

