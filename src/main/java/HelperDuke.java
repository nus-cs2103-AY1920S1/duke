import java.util.Scanner;

public class HelperDuke {
    public static void main(String[] args) {
        //start a Duke
        Duke duke = new Duke();

        //introduction
        duke.startDuke();

        //input action
        Scanner s = new Scanner(System.in);
        String action = s.nextLine().toLowerCase();

        while (!action.equals("bye")) {
            if(action.equals("list")) {
                duke.listAll();
            } else if (action.contains("done")) {
                //mark the specific task
                int index = Integer.parseInt(action.substring(5,6));
                duke.markAsDone(index);
            } else {
                //keep account of all the texts
                duke.add(action);

                //echo the text added
                duke.echo(action);
            }

            //ask for next action
            action = s.nextLine().toLowerCase();
        }

        //ending
        duke.endDuke();
    }


}
