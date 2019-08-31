import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public String readCommand(){
        return sc.nextLine();
    }

    public void showLoadingError(){
        System.out.println("File not found");
    }

    public void showWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showTasks(TaskList tasks){
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void showGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showDukeError(DukeException ex){
        System.out.println(ex);
    }

    public void showDoneTask(Task doneTask){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + doneTask);
    }

    public void showAddTask(Task newTask, int taskListSize){
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + taskListSize +
                " tasks in the list.");
    }

    public void showIOException(IOException e){
        System.out.println("Something went wrong: " + e.getMessage());
    }

    public void showDeleteTask(Task toRemove, int taskListSize){
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toRemove);
        System.out.println("Now you have " + taskListSize +
                " tasks in the list.");
    }

}
