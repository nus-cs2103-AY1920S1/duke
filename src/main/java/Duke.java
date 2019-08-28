import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bot b = new Bot();

        b.greet();
        b.getResponse();

        while (true) {
            String command = sc.nextLine();//read user input
            if (command.equals("bye")) {
                b.exit();
                b.getResponse();
                break;
            } else if (command.equals("list")) {
                b.list();
                b.getResponse();
            } else if (command.length() > 4 && command.substring(0, 4).equals("done")) {
                if (command.charAt(4) != ' ') {
                    try {
                        b.add(command);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        b.done(Integer.valueOf(command.substring(5)));
                        b.getResponse();
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else if (command.length() > 6 && command.substring(0, 6).equals("delete")) {
                if (command.charAt(4) != ' ') {
                    try {
                        b.add(command);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        b.delete(Integer.valueOf(command.substring(7)));
                        b.getResponse();
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else {
                try {
                    b.add(command);
                    b.getResponse();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
