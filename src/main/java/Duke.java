package com.leeyiyuan;


import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.format.CommandParseException;
import com.leeyiyuan.command.format.Parser;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        try {
            Storage storage = new Storage("/home/leeyiyuan/Projects/duke/data/duke.txt");
            TaskList tasks = new TaskList(storage.load());
            Parser parser = new Parser();

            System.out.println("Hello! I'm Duke\nWhat can I do for you?");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                Command command = parser.parse(scanner.nextLine());
                command.execute(tasks, storage);
            }
        } catch (CommandParseException e) {
            System.out.println("Caught exception: " + e.toString());
        } catch (CommandExecuteException e) {
            System.out.println("Caught exception: " + e.toString());
        } catch (StorageException e) {
            System.out.println("Caught exception: " + e.toString());
        }
    }
}
