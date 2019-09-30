package com.tysng.duke.ui;

import com.tysng.duke.exception.CommandException;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.service.Parser;
import com.tysng.duke.service.command.Command;
import com.tysng.duke.service.command.ExitCommand;

import java.util.Scanner;

/**
 * This class controls the input and output of the Application. It scans for user input, passes to Duke for processing,
 * and prints the response to stdout.
 */
public class Cli {
    private DukeService dukeService;

    /**
     * Constructs the UI object with Duke service layer object.
     *
     * @param dukeService a Duke service layer object
     */
    public Cli(DukeService dukeService) {
        this.dukeService = dukeService;
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

        // generation of command is done here because calling bye should exit the programme
        while (sc.hasNextLine()) {
            Command command;
            try {
                command = Parser.newCommand(sc.nextLine());
            } catch (CommandException e) {
                Response.newException(e);
                continue;
            }

            this.dukeService.handle(command).print();
            if (command instanceof ExitCommand) {
                break;
            }
        }
        sc.close();
    }



}
