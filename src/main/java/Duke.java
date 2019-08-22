import java.util.Scanner;

public class Duke {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
                Bot b = new Bot();
                b.greet();
                System.out.println(b);

                        while(true) {
                        String command = sc.nextLine();
                        if (command.equals("bye")) {
                                b.exit();
                                System.out.println(b);
                                break;
                            } else {
                                b.echo(command);
                                System.out.println(b);
                            }
                    }

    }
}
