import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        String instruction = sc.nextLine();

        while (!instruction.equals("bye")) {
            try {
                duke.evaluate(instruction);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(Duke.HORIZONTAL_LINE);        

            } 

            instruction = sc.nextLine();
        }

        duke.exit();
    }
}
