import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("./data/duketasks.txt");
        duke.start();
    }
}
