import java.time.format.DateTimeParseException;
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
            } catch (DateTimeParseException e) {
                System.out.println("     Unable to parse Date & Time of task!\n");
                System.out.println("     Please follow the following format for Date & Time!");
                System.out.println("       - Date: 'dd/mm/yy' e.g. 10/02/19 (10 Feb 2019)");
                System.out.println("       - Time: 'hhmm' (24-hr format) e.g. 0730 (07:30AM)");

                System.out.println(Duke.HORIZONTAL_LINE);        
            } catch (IncorrectDukeCommand e) {
                System.out.println("     Looks like a Duke Command has failed. That's because...\n");
                System.out.println(e.getMessage());
                System.out.println(Duke.HORIZONTAL_LINE);        
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(Duke.HORIZONTAL_LINE);        
            } 

            instruction = sc.nextLine();
        }

        sc.close();
        duke.exit();
    }
}
