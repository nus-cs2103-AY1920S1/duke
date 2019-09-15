package Utilities;

import java.util.Scanner;

/**
 * Shows the interface
 */
public class Ui {

    private final String LINE_BORDER = "____________________________________________________________";

    public void showWelcome(){
        final String DUKE_LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(LINE_BORDER + "\n" + "Hello! I'm Duke" + "\n" +
                "What can I do for you?" + "\n" + LINE_BORDER);
    }
    public String showWelcomeFX(){
        final String DUKE_LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello from\n" + DUKE_LOGO + LINE_BORDER + "\n" + "Hello! I'm Duke" + "\n" +
                "What can I do for you?" + "\n" + LINE_BORDER;
    }

    public void showConclusion(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public String showConclusionFX(){
        return "Bye. Hope to see you again soon!";
    }

    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    public void showLine(){
        System.out.println(LINE_BORDER);
    }

    public void showError(String message){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
    }
    public String showErrorFX(String message){
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-()";
    }

    /**
     * prints error message if file is not available
     */
    public void showLoadingError(){
        System.out.println("File not available");
    }
    public String showLoadingErrorFX(){
        return "File not available";
    }

    /**
     * print method for done message
     * @param n
     * @param tasks
     */
    public void doneMessage(int n, TaskList tasks){
        System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(n));
    }
    public String doneMessageFX(int n, TaskList tasks){
        return "Nice! I've marked this task as done: \n" + tasks.taskPrint(n);
    }

    public void deleteMessage(int n, TaskList tasks){
        System.out.println("Noted. I've removed this task:"+ "\n" + tasks.taskPrint(n) +
                "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.");
    }
    public String deleteMessageFX(int n, TaskList tasks){
        return "Noted. I've removed this task:"+ "\n" + tasks.taskPrint(n) +
                "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.";
    }

    /**
     * prints all contents in list format
     * @param tasks
     */
    public void ListCommand(TaskList tasks){
        for (int i=1; i<=tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1).printer());
        }
    }
    public String ListCommandFX(TaskList tasks){
        StringBuilder s = new StringBuilder();
        for (int i=1; i<=tasks.size(); i++) {
            s.append(i).append(". ").append(tasks.get(i - 1).printer()).append("\n");
        }
        return s.toString();
    }

    /**
     * to print the find command results
     * @param tasks
     */
    public void FindCommand(TaskList tasks){
        if(tasks.isEmpty()){
            System.out.println("Sorry, we couldn't find any results!");
        }else{
            System.out.println("Here are the matching tasks in your list:");
            this.ListCommand(tasks);
        }
    }
    public String FindCommandFX(TaskList tasks){
        if(tasks.isEmpty()){
            return "Sorry, we couldn't find any results!";
        }else{
            return "Here are the matching tasks in your list:" + "\n" + this.ListCommandFX(tasks);
        }
    }



}
