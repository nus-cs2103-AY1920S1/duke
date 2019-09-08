
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for Duke.
 */

public class Duke {

    private static Ui ui = new Ui();
    private static ArrayList<Task> taskList = new ArrayList<>(100);
    private static InputParser inputParser = new InputParser(taskList);

    Duke (){
        try {
            FileReading.checkFileExists(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    String getResponse(String input) throws IOException {
        if (!input.equals("bye")) {
            inputParser.determineAction(input);
            String response= ui.getGuidedUserInterfaceMsg();
            ui.setGuidedUserInterfaceMsg("");
            return response;
        }

        return ui.goodByeMsg;
    }

    public enum Action {
        ADD,
        REMOVE,
        DONE
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String input = "";

        try {
            FileReading.checkFileExists(taskList);
        }  catch (IOException e) {
            System.out.println("Oops. something went wrong with the duke.txt file");
        }

        ui.sayGreeting();

        while (!input.equals("bye")) {
            input = scan.nextLine();
            inputParser.determineAction(input);
        }
        ui.sayGoodbye();
    }
}
