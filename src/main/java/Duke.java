import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        Bot bot = new Bot();

        bot.greeting();


        try {
            bot.retrieve();
        } catch (FileNotFoundException e) {
            FileWriterClass.makeDirectory(FileWriterClass.DATA_FOLDER_PATH);
        }

        Scanner scanner = new Scanner(System.in);


        while (true) {

            String command = scanner.nextLine();

            String[] words = command.split(" ", 2);


            switch (words[0]) {
            case "todo":

                try {
                    bot.add(command, Bot.TaskType.TODO);
                    bot.save();
                } catch (DukeException | IOException e) {
                    System.out.println(e.getMessage());
                }

                continue;
            case "deadline":
                try {
                    bot.add(command, Bot.TaskType.DEADLINE);
                    bot.save();
                } catch (DukeException | IOException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            case "event":
                try {
                    bot.add(command, Bot.TaskType.EVENT);
                    bot.save();
                } catch (DukeException | IOException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            case "done":
                bot.done(command);
                bot.save();
                continue;
            case "delete":
                bot.delete(command);
                bot.save();
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
