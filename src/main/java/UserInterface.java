import java.util.Scanner;
public class UserInterface {
    Scanner sc;

    public UserInterface(){
        this.sc = new Scanner(System.in);
    }

    private void print(String message){
        System.out.println(">>" + message + "\n");
    }

    protected void printWelcome(){
        String logo = "\n" +
                " ______       ___    _ .--.   .--.      .-''-.   \n" +
                "|    _ `''. .'   |  | ||  | _/  /     .'_ _   \\  \n" +
                "| _ | ) _  \\|   .'  | || (`' ) /     / ( ` )   ' \n" +
                "|( ''_'  ) |.'  '_  | ||(_ ()_)     . (_ o _)  | \n" +
                "| . (_) `. |'   ( \\.-.|| (_,_)   __ |  (_,_)___| \n" +
                "|(_    ._) '' (`. _` /||  |\\ \\  |  |'  \\   .---. \n" +
                "|  (_.\\.' / | (_ (_) _)|  | \\ `'   / \\  `-'    / \n" +
                "|       .'   \\ /  . \\ /|  |  \\    /   \\       /  \n" +
                "'-----'`      ``-'`-'' `--'   `'-'     `'-..-'   \n" +
                "(◕ᴗ◕✿)\n";
        System.out.println("Hewwo from" + logo);
    }

    protected void printAdd(Task task, int listSize){
        StringBuilder outputMessage = new StringBuilder("Task added:\n");
        outputMessage.append("    " + task);
        outputMessage.append("\n  There are " + listSize + " tasks in the list.");
        this.print(outputMessage.toString());
    }

    protected void printLoadSave(){
        this.print("Save file loaded successfully");
    }

    protected void printNewFile(){
        this.print("New save file created");
    }

    protected void printDone(Task task){
        this.print("The following task has been marked as done:\n    " + task);
    }

    protected void printDelete(Task task, int listSize){
        StringBuilder outputMessage = new StringBuilder("Following task removed:\n    " + task);
        outputMessage.append("\n  " + listSize + " tasks left in the list");
        this.print(outputMessage.toString());
    }

    protected void printError(String message){
        this.print("Error: " + message);
    }

    protected void printList(String list){
        this.print("List:" + list);
    }

    protected String readLine(){
        return sc.nextLine();
    }

    protected void printExit(){
        this.print("Goodbye. Hope to see you again UwU");
    }
}

