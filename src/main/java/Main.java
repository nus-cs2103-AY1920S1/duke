import duke.Duke;

public class Main {

    public static void main(String[] args) {
        try {
            new Duke("../../../data/duke.txt").run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
