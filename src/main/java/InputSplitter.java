class InputSplitter {

    private String input;
    private Ui ui = new Ui();

    InputSplitter(String input){
        this.input = input;
    }

    String[] splitInput (String type){
        String[] output = new String[2];

        final String ERROR_INVALID_NUM = "OOPS!!! Please enter a valid number\n";
        final String ERROR_TODO = "OOPS!!! The description of a todo cannot be empty.\n";
        final String ERROR_DEADLINE = "OOPS!!! Incorrect description for deadline; " +
                "remember to use the /by keyword.\n";
        final String ERROR_EVENT = "OOPS!!! Incorrect description for event; " +
                "remember to use the /at keyword.\n";

        switch (type){

        case "todo":
            try {
                output[0] = input.split(" ", 2)[1];
            } catch (IndexOutOfBoundsException err) {
                ui.printToConsoleAndGui(ERROR_TODO);
            }
            break;

        case "deadline":
            try {
                output[0] = input.split(" /by ")[0].split(" ", 2)[1];
                output[1] = input.split(" /by ")[1];
            } catch (IndexOutOfBoundsException err) {
                ui.printToConsoleAndGui(ERROR_DEADLINE);
            }
            break;

        case "event":
            try {
                output[0] = input.split(" /at ")[0].split(" ", 2)[1];
                output[1] = input.split(" /at ")[1];
            } catch (IndexOutOfBoundsException err) {
                ui.printToConsoleAndGui(ERROR_EVENT);
            }
            break;

        default:

            assert type.equals("done") || type.equals("delete");

            try {
                output[0] = input.split(" ")[1];
            } catch (NumberFormatException err) {
                ui.printToConsoleAndGui(ERROR_INVALID_NUM);
            }
            break;

        }

        return output;
    }

}
