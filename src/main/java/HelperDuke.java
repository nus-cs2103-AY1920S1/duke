import java.util.Scanner;

public class HelperDuke {
    public static void main(String[] args) {
        //start a Duke
        Duke duke = new Duke();

        //introduction
        duke.startDuke();

        //input action
        Scanner s = new Scanner(System.in);
        String action = s.nextLine();


        while (!action.equalsIgnoreCase("bye")) {
            if(action.equalsIgnoreCase("list")) {

                duke.listAll();

            } else {
                //keep account of all the texts
                duke.add(action);

                //echo the text added
                duke.echo(action);
            }

            //ask for next action
            action = s.nextLine();
        }

        //ending
        duke.endDuke();
    }


}
