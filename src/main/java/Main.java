import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();

        while (!action.equals("bye")) {
            duke.evaluate(action);
            action = sc.nextLine();
        }

        duke.exit();
    }
}
