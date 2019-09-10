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

    public enum Action {
        ADD,
        REMOVE,
        DONE
    }

    Duke() {
        loadFile();
    }

    /**
     * Used to start Duke application via the CLI
     */

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String input = "";

        loadFile();
        ui.sayGreeting();

        while (!input.equals("bye")) {
            input = scan.nextLine();
            inputParser.determineAction(input);
        }

        ui.sayGoodbye();
    }

    /**
     * Used to start Duke application via the CLI
     */

    String getResponse(String input) throws IOException {

            if (!input.equals("bye")) {
                inputParser.determineAction(input);
                String response = ui.getGuidedUserInterfaceMsg();
                ui.setGuidedUserInterfaceMsg("");
                return response;
            }

            return ui.goodByeMsg;
    }

    private static void loadFile(){
        try {
            FileReading.checkFileExists(taskList);
        }  catch (IOException e) {
            System.out.println("Oops. something went wrong with the duke.txt file");
        }
    }
}
