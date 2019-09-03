package com.tysng.duke.ui;

import com.tysng.duke.exception.CommandException;
import com.tysng.duke.service.Duke;

import java.util.Scanner;

/**
 * This class controls the input and output of the Application. It scans for user input, passes to Duke for processing,
 * and prints the response to stdout.
 */
public class Ui {
    private Duke duke;

    /**
     * Constructs the UI object with Duke service layer object.
     *
     * @param duke a Duke service layer object
     */
    public Ui(Duke duke) {
        this.duke = duke;
    }

    /**
     * Starts users interaction.
     */
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

        while (sc.hasNextLine()) {
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
