import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Duke {
    private DukeSaveLoad dukeSaveLoad;
    private TaskList tasks;

    public Duke() throws Exception {
        dukeSaveLoad = new DukeSaveLoad();
        tasks = dukeSaveLoad.attemptLoad();
    }

    private void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        printStream.print(DukeTextFormatter.makeFormattedText(DukeUi.GREET_HELLO));

        while (true) { //Interaction loop with user. This will persist until shutdown.
            String userInput = sc.nextLine();
            
            try {
                DukeReply dukeReply = UserInputProcessor.processUserInput(userInput, tasks);
            
                printStream.print(dukeReply.dukeReplyString);

                if(dukeReply.shouldSave) {
                    dukeSaveLoad.attemptSave(tasks);
                }

                if(dukeReply.shouldExitLoop) {
                    break;
                }
            } catch (DukeException e) {
                printStream.print(e.getMessage());
            }
        }

        sc.close();
    }

    public static void main (String [] args) {
        try{
            new Duke().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
