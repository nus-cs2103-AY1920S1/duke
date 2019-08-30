import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public  void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(new Border());
        System.out.println("    Hello! I'm \n" + logo + "\n    What can I do for you?");
        System.out.println(new Border() + "\n");
    }

    public void showLine(){
        String output = "    ";
        for (int n = 0; n < 60; n++) {
            output += "_";
        }
        System.out.println(output);
    }


    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String error_msg) {
        System.out.println(error_msg);
    }

     /*   try {
            Task_List schedule = new Task_List(new Storage("data/duke.txt").load());
            Parser parser = new Parser(schedule);
            Scanner sc = new Scanner(System.in);
            System.out.println(new Border());
            System.out.println("    Hello! I'm \n" + logo + "\n    What can I do for you?");
            System.out.println(new Border() + "\n");
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                parser.parse(input);
                input = sc.nextLine();
            }
            parser.parse(input);
            System.out.println((new Border()) + "\n     Bye. Hope to see you again soon! \n" + (new Border()));

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }*/
}
