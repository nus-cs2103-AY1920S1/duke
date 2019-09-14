/**
 * The class used to split user input
 */

class InputSplitter {

    private String input;
    private Ui ui = new Ui();

    InputSplitter(String input){
        this.input = input;
    }

    /**
     * Returns a String array to {@link InputParser} containing the split input.
     * @param type Output changes based on what tye of task in parsed in
     */

    String[] splitInput (String type){
        String[] output = new String[2];

        final String ERROR_INVALID_NUM = "OOPS!!! Please enter a valid number\n";
        final String ERROR_TODO = "OOPS!!! The description of a todo cannot be empty.\n";
        final String ERROR_DEADLINE = "OOPS!!! Incorrect description for deadline; " +
                "remember to use the /by keyword.\n";
        final String ERROR_EVENT = "OOPS!!! Incorrect description for event; " +
                "remember to use the /at keyword.\n";
        final String ERROR_FIND = "OOPS!!! Incorrect format for the 'find' command.\n";

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

        case "find":
            try {
                output[0] = input.split(" ", 2)[1];
            } catch (IndexOutOfBoundsException err) {
                ui.printToConsoleAndGui(ERROR_FIND);
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
