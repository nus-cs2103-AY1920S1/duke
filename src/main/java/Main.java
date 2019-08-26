import duke.Duke;

public class Main {

    /**
     * Initialises Duke program.
     * @param args arguments passed in in command line.
     */
    public static void main(String[] args) {
        try {
            new Duke("../../data/duke.txt").run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
