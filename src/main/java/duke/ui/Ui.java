package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private final static String greet = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private final static String goodbye = "Bye. Hope to see you again!";
    private final static String niceAdded = "Nice! I've marked this task as done:";
    private final static String gotIt = "Got it. I've added this task:";
    private final static String deleted = "Noted. I've removed this task:";
    private final static String tasks = "Here are your tasks in your list:";
    private BufferedReader userInput;

     public Ui(){
        this.userInput = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printNumTasks(){
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
    }

    public void printLogoAndGreet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
    }

    public void printGoodbye(){
        System.out.println(goodbye);
    }

    public void printNice(Task doneTask){
        System.out.println(niceAdded);
        System.out.println(doneTask);
    }

    public void printGotIt(Task newTask){
        System.out.println(gotIt);
        System.out.println(" " + newTask.toString());
    }

    public void printDeleted(Task deletedTask){
        System.out.println(deleted);
        System.out.println(deletedTask);
    }

    public String readLine() {
        try {
            return userInput.readLine();
        } catch (Exception e){
            showReadingError();
            return null;
        }
    }

    private void showReadingError(){
        System.err.println("Error reading user input");
    }

    private void showCloseInputError(){
        System.err.println("Error close user input stream");
    }

    public void printTasks(TaskList tasklist){
        System.out.println(tasks);
        System.out.println(tasklist.toString());
    }

    public void closeInput(){
        try {
            userInput.close();
        } catch (IOException e) {
            showCloseInputError();
        }
    }

    public void showLoadingError(){
        System.err.println("Error loading from specified file path");
    }

    public void showWritingError(){
        System.err.println("Error writing to specified file path");
    }

    public void printWritingChanges(){
        System.out.println("Writing new changes to disk...");
    }

    public void printDoneWriting(){
        System.out.println("Writing done!");
    }

    public void printTaskError(String errorMessage){
        System.err.println(errorMessage);
    }
}
