import java.util.Scanner;

public class Listener {

    private Adder adder;

    public void start(Adder adder) {

        Scanner sc = new Scanner(System.in);
        this.adder = adder;

        System.out.print(Formatter.WELCOME);
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.GOODBYE + "\n" + Formatter.LINE);
                break;
            } else {
                switch (command) {
                    case "list":
                        this.adder.showList();
                        break;
                    default:
                        adder.add(command);
                        System.out.print(Formatter.LINE + Formatter.INDENT + "added: "
                                + command + "\n" + Formatter.LINE);
                        break;
                }
            }
        }
    }
}
