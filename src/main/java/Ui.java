import java.util.Scanner;

public class Ui {
    private String displayText;
    private Scanner scanner;
    public Ui(){
        scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    public void showBye(){
        displayText = "Bye. Hope to see you again soon!";
    }

    public void showLoadingError() {
        System.out.println("\\u2639 OOPS!!! I'm sorry, but I could not load your saved task list ");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        StringBuilder sb = new StringBuilder("\u2639 OOPS!!! I'm sorry, but ");
        sb.append(message);
        System.out.println(sb.toString());
    }

    public void showLine() {
        System.out.println(displayText);
        displayText = "";
    }

    public void setText(String str){
        displayText = str;
    }

    public void showTaskSaved(Task task, int size) {
        displayText = "Got it. I've added this task:\n\t" + task + "\nNow you have " + size + " tasks in the list.";
    }
}
