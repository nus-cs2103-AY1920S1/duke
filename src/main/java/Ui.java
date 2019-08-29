import java.util.Scanner;

public class Ui {
    private Scanner reader;
    public Ui() {
        reader = new Scanner(System.in);
    }
    public void showLoadingError() {
        System.err.println("File not found");
    }
    public String getNextLine() {
        return reader.nextLine();
    }
    public String getNext() {
        return reader.next();
    }

    public void introduction() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );
    }

    public void showLine() {
        StringBuilder lineBuilder = new StringBuilder("     ");
        for(int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        System.out.println(lineBuilder.toString());
    }
    public void sayGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

}
