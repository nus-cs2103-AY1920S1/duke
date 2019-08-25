import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws DukeException, ClassNotFoundException, IOException {
        DukeManager dukeManager = new DukeManager();
        dukeManager.initializeDuke();
    }
}
