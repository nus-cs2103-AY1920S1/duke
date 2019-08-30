import java.util.Scanner;

public class Ui{
    private Parser inputParser = new Parser();
    Scanner scanner = new Scanner(System.in);
    boolean isExit = false;

    public void respond(TaskList taskList, Storage storage, Ui ui) {


        this.printText("Hello! I'm Duke\n" +
                "What can I do for you?");

        while (!isExit && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Command current = null;

            try {
               current =  inputParser.parseCommand(input);
               current.runCommand(taskList, storage, ui);
            } catch (IllegalArgumentException e1) {
                System.out.println(e1);
            } catch (EmptyDescException e2) {
                System.out.println(e2);
            }

        }
    }

    public void showLoadingError(){
        System.out.println("Unable to load");
    }

    public void closeUi(){
        this.isExit = true;
        this.scanner.close();
    }


    public void printText(String text){
        String line = "\n____________________________________________________________\n";
        System.out.println(line + text + line);

    }
}