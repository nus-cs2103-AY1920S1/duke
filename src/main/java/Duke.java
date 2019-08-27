import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

        Bot bot = new Bot();

        bot.greeting();


        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();



        while (command.equals("bye") == false) {

            String[] words = command.split(" ",2);

            if (words[0].equals("todo")) {

                try {
                    bot.add(command, Bot.TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }



            } else if (words[0].equals("deadline")) {
                try {
                    bot.add(command, Bot.TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("event")) {
                try {
                    bot.add(command, Bot.TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("done")) {

                bot.done(command);

            } else if (command.equals("list")) {
                bot.list();

            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }

            command = scanner.nextLine();
        }

        bot.bye();

    }
}
