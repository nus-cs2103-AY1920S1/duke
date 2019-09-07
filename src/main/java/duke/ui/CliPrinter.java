package duke.ui;

public class CliPrinter extends Printer {

    @Override
    public void printDuke(String text) {
        System.out.println(text);
    }

    @Override
    public void printUser(String text) {
        // do nothing
    }

}
