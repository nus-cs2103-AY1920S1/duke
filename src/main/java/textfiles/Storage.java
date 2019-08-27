package textfiles;

public class Storage {
    public void ioErrorMessage() {
        printLine();
        System.out.println("     Sorry there is no text file to read or write data.");
        printLine();
        System.out.println();
    }

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
