import java.util.Scanner;

public class Duke {

    private static final String MESSAGE_PADDING  = "     ";
    private static final String MESSAGE_BOUNDARY = "    ____________________________________________________________";

    private void printResponse(String response) {
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(response);
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("");
    }

    private boolean shouldContinueChat(String query) {
        return !query.equals("bye");
    }

    private String findResponse(String query, boolean continueChat) {
        if (!continueChat) {
            return "Bye. Hope to see you again soon!";
        }

        return query;
    }


    public void spin() {
        boolean continueChat = true;
        Scanner myscanner = new Scanner(System.in);  // Create a Scanner object

        printResponse("Hello! I'm Duke\n" + MESSAGE_PADDING + "What can I do for you?");

        do {
            //Get query from user
            String userQuery = myscanner.nextLine();

            //Find Response
            continueChat = shouldContinueChat(userQuery);
            String dukeResponse = findResponse(userQuery, continueChat);

            //Print Response
            printResponse(dukeResponse);

        } while (continueChat);
        myscanner.close();
    }


    public static void main(String[] args) {
        Duke myObj = new Duke();
        myObj.spin();
    }
}
