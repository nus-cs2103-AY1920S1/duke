import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

        Bot bot = new Bot();

        bot.greeting();


        Scanner scanner = new Scanner(System.in);


        while (true) {

            String command = scanner.nextLine();

            String[] words = command.split(" ", 2);


            switch (words[0]) {
            case "todo":
                try {
                    bot.add(command, Bot.TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

                continue;
            case "deadline":
                try {
                    bot.add(command, Bot.TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            case "event":
                try {
                    bot.add(command, Bot.TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            case "done":
                bot.done(command);
                continue;
            case "delete":
                bot.delete(command);
                continue;
            }

            switch (command) {
            case "list":
                bot.list();
                break;
            case "bye":
                bot.bye();
                return;
            default:
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }

        }


    }
}
