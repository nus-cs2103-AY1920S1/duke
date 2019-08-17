import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

public class Duke {

    private static final String MESSAGE_PADDING  = "     ";
    private static final String MESSAGE_BOUNDARY = "    ____________________________________________________________";

    private List<String> myList;

    public Duke() {
        myList = new ArrayList<String>();
    }

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
        } else if (query.equals("list")) {
            return IntStream
                .range(0, myList.size())
                .mapToObj(index -> String.format("%d. %s", index + 1, myList.get(index)))
                .collect(Collectors.joining("\n" + MESSAGE_PADDING));
        } else {
            myList.add(query);
            return String.format("added: %s", query);
        }
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
