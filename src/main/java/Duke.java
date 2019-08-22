import java.util.Scanner;

public class Duke {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Bot b = new Bot();
        b.greet();

        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                b.exit();
                break;
            } else if (command.equals("list")){
                b.list();
            } else if (command.length() >= 4 && command.substring(0,4).equals("done")){
                b.done(Integer.valueOf(command.substring(5)));
            } else {
                try{b.add(command);} catch (DukeException e){System.out.println(e.getMessage());};
            }
        }

    }
}
