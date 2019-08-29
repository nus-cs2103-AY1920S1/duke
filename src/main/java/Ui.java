import java.util.Scanner;

public class Ui {

    private Duke parent;

    public Ui(Duke parent) {
        this.parent = parent;
    }

    public void readInputs() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            try {
                parent.evaluate(input);
            } catch (DukeException e) {
                dukeOutput(e.getMessage());
            }
        }
        sc.close();
    }

    public void dukeOutput(String out) {
        String bound = "_______________________________________";
        String newOutput = out.replace("\n", "\n    ");
        System.out.println("    " + bound + "\n    "
                + newOutput + "\n    " + bound);
    }

}
