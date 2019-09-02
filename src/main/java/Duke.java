import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        Ui ui = new Ui();
        Bot bot = new Bot();



        ui.greeting();

        try {
            bot.retrieve();
        } catch (FileNotFoundException e) {
            Storage.makeDirectory(Storage.DATA_FOLDER_PATH);
        }

        Scanner scanner = new Scanner(System.in);


        while (true) {

            String command = scanner.nextLine();

            TaskType taskType = Parser.returnsTaskType(command);

            if (taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT) {

                bot.add(command, taskType);
                bot.save();

            } else {
                switch (taskType) {
                case DONE:
                    bot.done(command);
                    bot.save();
                    continue;
                case DELETE:
                    bot.delete(command);
                    bot.save();
                    continue;

                case LIST:
                    bot.list();
                    break;
                case BYE:
                    ui.bye();
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");


            }

            /*
            switch (taskType) {
            case TODO, EVENT:
                try {
                    bot.add(command, TaskType.TODO);
                    bot.save();
                } catch (DukeException | IOException e) {
                    System.out.println(e.getMessage());
                }

                continue;
            case DEADLINE:
                try {
                    bot.add(command, TaskType.DEADLINE);
                    bot.save();
                } catch (DukeException | IOException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            case "event":
                try {
                    bot.add(command, TaskType.EVENT);
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
                ui.bye();
                return;
            default:
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }

        }*/


    }
}
