package duke;

/**
 * This class helps to pause 0.5 seconds before exiting Duke.
 */
public class NewThread extends Thread {
    /**
     * This run method pause the thread for 0.5 seconds and exit the program with exit code 0.
     */
    public void run() {
        try {
            Thread.sleep(500);
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}