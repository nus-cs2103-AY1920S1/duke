import java.util.Scanner;

public class Duke {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Bot b = new Bot();
        b.greet();

        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                b.exit();
                break;
            } else if (command.equals("list")){
                b.list();
            } else {
                b.add(command);
            }
        }

    }
}
