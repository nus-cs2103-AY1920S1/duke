package main;

import command.*;

import java.io.IOException;

public class Parser {

    public static Command parse(String input) throws IOException {

        if (input.equals("list")) {
            return new ListCommand();
        } else {
            String[] temp = input.split(" ");
            if (temp[0].equals("done")) {
                return new DoneCommand(temp);

            } else if (temp[0].equals("delete")) {
                return new DeleteCommand(temp);

            } else if (temp[0].equals("find")){
                return new FindCommand(temp);
            } else {
                return new AddCommand(input, temp);
            }
        }

    }
}
