package com.tysng.duke.ui;

import com.tysng.duke.exception.CommandException;
import com.tysng.duke.service.Duke;

import java.util.Scanner;

public class Ui {
    private Duke duke;

    public Ui(Duke duke) {
        this.duke = duke;
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Response.newGreetings().print();

        // start interaction
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine() ) {
            Command command;
            try {
                command = Command.NewCommand(sc.nextLine());

            } catch (CommandException e) {
                Response.newException(e);
                continue;
            }
            this.duke.handle(command).print();
            if (command.getType() == Command.CommandType.BYE) {
                break;
            }
        }
        sc.close();
    }
}
