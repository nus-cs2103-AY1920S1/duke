public class Duke {
    private UserInterface ui;

    private Duke() {
        ui = new UserInterface();
    }

    private void run() {
        ui.showWelcomeMessage();
        while (true) {
            String inputLine = ui.readLine();
            ui.showLine();
            if (inputLine.equals("bye")) {
                ui.exitProgram();
                break;
            } else {
                ui.showToUser(inputLine);
            }
            ui.showLine();
        }
    }

    public static void main (String[]args){
        Duke duke = new Duke();
        duke.run();
    }
}
