package duke.command;

import duke.repos.Repository;
import duke.responses.ResponseGen;
import duke.repos.TaskRepo;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommand extends Command {

    public AddCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
        assert !fullCommand.equals("");
        assert splitCommand.length != 0;
    }

    @Override
    public String execute(Repository<Task> taskRepo) throws ParseException, IOException {
        switch (splitCommand[0]) {
        case "t": {
            try {
                String[] stringBreaker = fullCommand.split("t", 2);
                Task output = new ToDos(stringBreaker[1], this.formatter);
                taskRepo.create(output);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        case "d": {
            try {
                String newString = fullCommand.substring(9);
                String[] stringBreaker = newString.split("/by", 2);
                Date date = formatter.parse(stringBreaker[1]);
                Task output = new Deadlines(stringBreaker[0], formatter, date);
                taskRepo.create(output);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
            break;
        }
        case "e": {
            try {
                String newString = fullCommand.substring(6);
                String[] stringBreaker = newString.split("/at", 2);
                Date date = formatter.parse(stringBreaker[1]);
                Task output = new Events(stringBreaker[0], formatter, date);
                taskRepo.create(output);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
            break;
        }
        }
        return ResponseGen.addResponse(taskRepo.getAll());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
