import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    Ui ui;
    ExceptionHandler exceptionHandler;
    Storage storage;
    TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.exceptionHandler = new ExceptionHandler();
        this.storage = new Storage("C:\\Users\\NINGS\\OneDrive\\Documents\\duke\\data");
    }

    void run() {
        try {
            taskList = new TaskList(storage.readSaveFile(), storage);
        } catch (IOException e) {
            taskList = new TaskList(storage);
        }

        if (!taskList.hasTask()) {
            ui.welcome();
        } else {
            ui.welcomeBack();

            try {
                taskList.listAlltasks();
            } catch (DukeException e) {
                exceptionHandler.showDukeError(e);
            }

            Ui.showLine();
        }

        Scanner sc = new Scanner(System.in);
        String instruction = sc.nextLine();
        Parser parser = new Parser();

        while (!instruction.equals("bye")) {
            try {
                parser.parseInstruction(instruction, taskList);
            } catch (DateTimeParseException e) {
                exceptionHandler.showParseDateTimeError();
                Ui.showLine();
            } catch (VoidDukeCommand e) {
                exceptionHandler.showVoidDukeCommandError();
                Ui.showLine();
            } catch (IncorrectDukeCommand e) {
                exceptionHandler.showDukeCommandEvaluationError(e);       
                Ui.showLine();
            } catch (InvalidDukeCommand e) {
                exceptionHandler.showUnknownDukeCommandError();
                Ui.showLine();
            } catch (IOException e) {
                exceptionHandler.showDukeIOError();
                Ui.showLine();
            } catch (DukeException e) {
                exceptionHandler.showDukeError(e);   
                Ui.showLine();
            } 

            instruction = sc.nextLine();
        }

        sc.close();
        ui.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}